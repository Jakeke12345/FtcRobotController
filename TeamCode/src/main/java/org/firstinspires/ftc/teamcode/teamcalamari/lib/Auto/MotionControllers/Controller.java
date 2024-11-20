package org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.MotionControllers;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Gamepads.Gamepad_Controls;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Assemblies.Robots.Drivetrain;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Angle;

public abstract class Controller {

    public abstract void driveUpdate(Angle head);
    public void driveUpdate(){
        driveUpdate(new Angle(-0, AngleUnit.RADIANS));
    }
    public Drivetrain drivetrain;
    public boolean driverOriented = false;
    public Gamepad_Controls[] controls;
    public Angle initalHead;
    public void toggleDriverOriented(Angle intialHead){
        driverOriented = !driverOriented;
        this.initalHead = intialHead;
    }
    public boolean getDriverOriented(){
        return driverOriented;
    }

    public Controller(Drivetrain drivetrain, Gamepad_Controls... controls) {
        this.drivetrain = drivetrain;
        this.controls = controls;
    }
}
