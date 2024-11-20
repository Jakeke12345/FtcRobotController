package org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * This class simply creates an angle. this is the class used throughout all of our library.
 */
public class Angle {
    private double angle;
    private AngleUnit angleUnit;
    public Angle(double angle, AngleUnit angleUnit){
        this.angleUnit = angleUnit;
        this.angle = normalize(angle);
    }

    public Angle(double angle){
        this(angle, AngleUnit.DEGREES);
    }

    /**
     * @return; will return the angle stored in the object u call this on. NOTE: will return this in degrees, NOT radians
     */
    public double getAngleDegrees(){
        conversionToDegrees();
        return angle;
    }
    /**
     * @return; will return the angle stored in the object u call this on. NOTE: will return this in radians, NOT degrees
     */
    public double getAngleRadians(){
        conversionToRadians();
        return angle;
    }
    /**
     * This converts the double stored in the angle to Radians
     */
    private void conversionToRadians() {
        if (angleUnit.equals(AngleUnit.DEGREES)) {
            angle = angleUnit.toRadians(angle);
            angleUnit = AngleUnit.RADIANS;
        }
    }
    /**
     * This converts the double stored in the angle to Degrees
     */
    private void conversionToDegrees(){
        if (angleUnit.equals(AngleUnit.RADIANS)){
            angle = angleUnit.toDegrees(angle);
            angleUnit = AngleUnit.DEGREES;
        }
    }

    /**
     * Running this will automatically shift the angle into the legal bounds.
     * @param angle the angle to be normalized
     * @return will return the fixed angle
     */
    private double normalize(double angle){
        switch (this.angleUnit)
        {
            default:
            case RADIANS:               return normalizeRadians(angle);
            case DEGREES:               return normalizeDegrees(angle);
        }
    }
    /**
     * this is the calculations used in the 'normalize' method.
     * the angle will be shifted to between -180 to 180 (if out of bounds; otherwise nothing changes)
     * @param degrees the angle to be normalized
     * @return will return the angle in double form (degrees)
     */
    private static double normalizeDegrees(double degrees)
    {
        while (degrees >= 180) degrees -= 360.0;
        while (degrees < -180) degrees += 360.0;
        return degrees;
    }

    /**
     * this is the calculations used in the 'normalize' method.
     * @param radians the angle will be shifted to between -π to π (if out of bounds; otherwise nothing changes)
     * @return will return the angle in double form (radians)
     */
    private static double normalizeRadians(double radians)
    {
        while (radians >= (Math.PI)) radians -= (Math.PI * 2);
        while (radians < -(Math.PI)) radians += (Math.PI * 2);
        return radians;
    }

    /**
     * this method is a more simple way to add angles than trying to get the double form and add that
     * Because of the commutative law of addition, the order of the angles does not matter
     * @param angle1 i'm listening to the Piano Guys rn
     * @param angle2 They're very talented. go check them out! (no, they didn't sponsor us 😭)
     * @return will return the added angles
     */
    public static Angle add(Angle angle1, Angle angle2) {
        if (angle1.angleUnit.equals(angle2.angleUnit)) {
            return new Angle(angle1.angle + angle2.angle, angle1.angleUnit);
        } else {
            return new Angle(angle1.getAngleDegrees() + angle2.getAngleDegrees(), AngleUnit.DEGREES);
        }
    }
    /**
     * This method is a simple way to subtract angles.
     * NOTE: THE ORDER OF THE ANGLES DOES MATTER! subtraction is NOT commutative!
     * @param angle1 the angle subtracted from
     * @param angle2 the angle being subtracted
     * @return the subtracted angle
     */
    public static Angle subtract(Angle angle1, Angle angle2) {
        if (angle1.angleUnit.equals(angle2.angleUnit)) {
            return new Angle(angle1.angle - angle2.angle, angle1.angleUnit);
        } else {
            return new Angle(angle1.getAngleDegrees() - angle2.getAngleDegrees(), AngleUnit.DEGREES);
        }
    }

    /**
     * this is an overwritten method that determines if two angles are equal by comparing the doubles stored in them
     * @param obj the angle being compared
     * @return a boolean that is true/false based on whether the two angles are equal
     */
    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (!(obj instanceof Angle)){
            return false;
        }
        //the reason that this has a margin of error of negligible magnitude is due to a very sight discrepancy
        //in Java's code that may result in a tiny difference from normal calculated numbers.
        //this can happen sometimes when calculating doubles.
        if (Math.abs(((Angle)obj).getAngleDegrees() - getAngleDegrees()) < 0.000001) {
            return true;
        }
        return false;
    }

    /**
     * this is an overwritten method that returns the angle (in degrees) stored in the double of said angle
     * NOTE: this method will return a string, used getAngleDegrees or getAngleRadians if you want to do calculations
     * @return the string form of the angle (in degrees)
     */
    @Override
    public String toString(){
        return Double.toString(getAngleDegrees());
    }

    public static Angle negateDegrees(Angle angle){
        return new Angle(angle.getAngleDegrees() * -1, AngleUnit.DEGREES);
    }

    public static Angle negateRadians(Angle angle){
        return new Angle(angle.getAngleRadians() * -1, AngleUnit.RADIANS);
    }
}