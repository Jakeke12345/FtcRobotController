package org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.Pathing;

import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Position;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Angle;

/**
 * This class creates an "Autostep" when the constructor is called
 * NOTE: use this class wisely, else u might summon Hampter...
 */
public class AutoStep {
    protected Position position;
    protected Angle rotation;
    protected Runnable runnable;

    /**
     * @param position; the desired position of the robot
     * @param rotation; the desired angle of rotation for the robot
     */
    public AutoStep(Position position, Angle rotation, Runnable runnable) {
        this.position = position;
        this.rotation = rotation;
        this.runnable = runnable;
    }
    /**
     * @param position; the desired position of the robot
     * @param rotation; the desired angle of rotation for the robot
     */
    public AutoStep(Position position, Angle rotation){
        this.position = position;
        this.rotation = rotation;
    }
    /**
     * @param position; the desired position of the robot
     */
    public AutoStep(Position position, Runnable runnable) {
        this.position = position;
        this.runnable = runnable;
    }
    /**
     * @param rotation; the desired angle of rotation for the robot
     */
    public AutoStep(Angle rotation, Runnable runnable) {
        this.rotation = rotation;
        this.runnable = runnable;
    }
    /**
     * @param position; the desired position of the robot
     */
    public AutoStep(Position position) {
        this.position = position;
    }
    /**
     * @param rotation; the desired angle of rotation for the robot
     */
    public AutoStep(Angle rotation) {
        this.rotation = rotation;
    }
    public AutoStep(Runnable runnable) {
        this.runnable = runnable;
    }
    /**
     * CAUTION: RUNNING THIS WILL SUMMON HAMPTER
     */
    public AutoStep(){
        throw new RuntimeException("YOU SUMMONED THE HAMPTER! LOCK YOUR DOORS AND WINDOWS! MUHAHAHAHAHAHAHAHAHAHAHAHA!", new NullPointerException());
    }
    public Position getPosition(){
        return position;
    }
}
