package org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.Odometry;

import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Angle;

/**
 * This is a class that can be used to determine the robot's position (if data was entered accurately)
 */
public abstract class Odometry {
    //unsupported exceptions DON'T EXIST HERE!!!!!!!!!!!!!
    public Ticks ticks;
    public abstract void startTracking();
    /*public abstract Position getPosition();*/
    public abstract Angle getAngle();
    public abstract void endAndReset();
    /**
     * This method is used to determine some of the positioning in the library
     * @param ticksPerRev how many ticks per
     * @param wheelDiameter
     * @param gearRatio
     */
    public void setTicks(double ticksPerRev, double wheelDiameter, double gearRatio){
        this.ticks = new Ticks(ticksPerRev, wheelDiameter, gearRatio);
    }
}
