//PP_Loop if i'm being honest
package org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.MotionControllers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.Odometry.OC3_deadwheel;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Position;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Assemblies.Robots.Drivetrain;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Angle;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.Pathing.AutoStep;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.Pathing.AutoPath;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is used by the AutonomousPath and AutoPosition class
 */
public class P_Loop {
    public List<Position> psteps;
    private Drivetrain drivetrain;
    public Position distanceToPosition;
    public Runnable telemetryData;
    OC3_deadwheel mechEncoders;

    /**
     * @param drivetrain the drivetrain whose position is being calculated
     * @param mechEncoders the encoders on the motors/odometry pods
     * @param autoPath the vectors the robot runs
     */
    public P_Loop(Drivetrain drivetrain, OC3_deadwheel mechEncoders, AutoPath autoPath, Runnable telemetryData){
        this.drivetrain = drivetrain;
        this.psteps = autoPath.points.stream().map(AutoStep::getPosition).collect(Collectors.toList());
        this.mechEncoders = mechEncoders;
        this.telemetryData = telemetryData;
    }

    public void navigate(LinearOpMode opMode, double marginOfError){
        distanceToPosition = new Position(DistanceUnit.INCH, marginOfError + 1, marginOfError + 1);
        for (int i = 0; i < psteps.size(); i++){
            distanceToPosition = Position.subtractPos(psteps.get(i), mechEncoders.getPosition());
            while (opMode.opModeIsActive() && (Math.abs(distanceToPosition.getX()) > marginOfError || Math.abs(distanceToPosition.getY()) > marginOfError)){
                telemetryData.run();
                distanceToPosition = Position.subtractPos(psteps.get(i), mechEncoders.getPosition());
                drivetrain.driveAtAngle(Angle.add(new Angle(Math.atan2(distanceToPosition.getY(), distanceToPosition.getX()), AngleUnit.RADIANS), new Angle(0, AngleUnit.DEGREES)));
            }
        }
    }
}
