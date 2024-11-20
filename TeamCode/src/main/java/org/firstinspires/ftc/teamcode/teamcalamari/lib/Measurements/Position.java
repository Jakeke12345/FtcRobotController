package org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Position {
    private final double x;
    private final double y;
    public Position(DistanceUnit unit, double x, double y){
        this.x = unit.toInches(x);
        this.y = unit.toInches(y);
    }
    public static Position subtractPos(Position firstPos, Position secondPos){
        return new Position(DistanceUnit.INCH, firstPos.getX() - secondPos.getX(), firstPos.getY() - secondPos.getY());
    }
    public static Position addPos(DistanceUnit distanceUnit, Position firstPos, Position secondPos){
        return new Position(distanceUnit, firstPos.getX() + secondPos.getX(), firstPos.getY() + secondPos.getY());
    }
    public double getX(DistanceUnit unit){
        return unit.fromInches(x);
    }
    public double getY(DistanceUnit unit){
        return unit.fromInches(y);
    }

    public double getX(){return getX(DistanceUnit.INCH);}
    public double getY(){return getY(DistanceUnit.INCH);}
    @Override
    public String toString(){
        return (String.format("%f, %f", getX(),getY()));
    }
}