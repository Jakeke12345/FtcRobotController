package org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.Pathing;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AutoPath {

    public List<AutoStep> points;
    public double ppp;

    public AutoPath(double ppp, AutoStep... points){
        this.ppp = ppp;

        this.points = interpolate(points);
    }

    /** Catmull-Rom spline interpolation. Considered Bezier curve and cubic splines.
        * Bezier curves do not guarantee passing through every point.
        * Cubic splines assume an independent and a dependant variable so this is probably the wrong application for that
     **/
    private List<AutoStep> interpolate(AutoStep[] controlPoints) {
        List<AutoStep> curvePoints = new ArrayList<>();
        List<Position> controlPointsPos = Arrays.stream(controlPoints)
                .map(AutoStep::getPosition).collect(Collectors.toList());
        int n = controlPoints.length;

        if (n < 2) {
            throw new IllegalArgumentException("At least two control points are required.");
        }

        for (int i = 0; i < n - 1; i++) {
            Position p0 = i == 0 ? controlPointsPos.get(0) : controlPointsPos.get(i - 1);
            Position p1 = controlPointsPos.get(i);
            Position p2 = controlPointsPos.get(i + 1);
            Position p3 = (i + 2 < n) ? controlPointsPos.get(i + 2) : p2;

            // Currently generates 20 points per point (ppp). Is a double for int division reasons
            for (int t = 0; t < ppp; t++) {
                if(t==0){
                    curvePoints.add(controlPoints[i]);
                } else {
                    curvePoints.add(new AutoStep(interpolatePoint(p0, p1, p2, p3, t/ppp)));
                }
            }
        }
        curvePoints.add(controlPoints[n - 1]); // Add the last point
        return curvePoints;
    }

    private static Position interpolatePoint(Position p0, Position p1, Position p2, Position p3, double t) {
        double t2 = t * t;
        double t3 = t2 * t;

        double x = 0.5 * ((2 * p1.getX()) +
                (-p0.getX() + p2.getX()) * t +
                (2 * p0.getX() - 5 * p1.getX() + 4 * p2.getX() - p3.getX()) * t2 +
                (-p0.getX() + 3 * p1.getX() - 3 * p2.getX() + p3.getX()) * t3);

        double y = 0.5 * ((2 * p1.getY()) +
                (-p0.getY() + p2.getY()) * t +
                (2 * p0.getY() - 5 * p1.getY() + 4 * p2.getY() - p3.getY()) * t2 +
                (-p0.getY() + 3 * p1.getY() - 3 * p2.getY() + p3.getY()) * t3);

        return new Position(DistanceUnit.INCH, x, y);
    }
    public int getPoints(){
        return 0;
    }
    public int getTotalPoints(){
        return points.size();
    }
}
