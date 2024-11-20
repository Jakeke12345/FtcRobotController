package org.firstinspires.ftc.teamcode.teamcalamari.lib.DriveModes;

import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Gamepads.Gamepad_Controls;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Assemblies.Robots.MecanumBot;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.MotionControllers.Controller;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Angle;

public class DriveLincolnMode extends Controller {
    public DriveLincolnMode(Gamepad_Controls gamepad1, Gamepad_Controls gamepad2, Gamepad_Controls gamepad3, MecanumBot mechDrive){
        super(mechDrive, gamepad1, gamepad2, gamepad3);
    }
    @Override
    public void driveUpdate(Angle doAngle) {
        if (!getDriverOriented()) {
            double wheel1Power = -controls[0].getGamepadProportionalInputs() + controls[1].getGamepadProportionalInputs() + (controls[2].getGamepadProportionalInputs() / 2);
            double wheel2Power = controls[0].getGamepadProportionalInputs() + controls[1].getGamepadProportionalInputs() + (controls[2].getGamepadProportionalInputs() / 2);
            double wheel3Power = controls[0].getGamepadProportionalInputs() - controls[1].getGamepadProportionalInputs() + (controls[2].getGamepadProportionalInputs() / 2);
            double wheel4Power = -controls[0].getGamepadProportionalInputs() - controls[1].getGamepadProportionalInputs() + (controls[2].getGamepadProportionalInputs() / 2);
            drivetrain.manualDriving(wheel1Power, wheel2Power, wheel3Power, wheel4Power);
        } else {
            Angle rotateAngle = Angle.subtract(initalHead, doAngle);
            double x = (controls[0].getGamepadProportionalInputs() * Math.cos(rotateAngle.getAngleRadians()) - (controls[1].getGamepadProportionalInputs() * Math.sin(rotateAngle.getAngleRadians())));
            double y = (controls[0].getGamepadProportionalInputs() * Math.sin(rotateAngle.getAngleRadians()) + (controls[1].getGamepadProportionalInputs() * Math.cos(rotateAngle.getAngleRadians())));
            double wheel1Power = -x + y + controls[2].getGamepadProportionalInputs();
            double wheel2Power = x + y + controls[2].getGamepadProportionalInputs();
            double wheel3Power = x - y + controls[2].getGamepadProportionalInputs();
            double wheel4Power = -x - y + controls[2].getGamepadProportionalInputs();
            drivetrain.manualDriving(wheel1Power, wheel2Power, wheel3Power, wheel4Power);
        }
    }
}
