package org.firstinspires.ftc.teamcode.teamcalamari.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teamcalamari.lib.DriveModes.DriveLincolnMode;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Gamepads.Gamepad_Controls;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Gamepads.Gamepad_Proportional_Inputs;

/**
 * This class is the TeleOp program used for Meet 0
 */
@TeleOp(name="CADrivetrain_teleop_test")
public class SimpleTeleOpDLM extends OpMode {

    DriveLincolnMode driveController;
    SimpleMecanumBot robot;

    @Override
    public void init() {
        robot = new SimpleMecanumBot(hardwareMap);
        robot.init();

        driveController = new DriveLincolnMode(new Gamepad_Controls(gamepad1, Gamepad_Proportional_Inputs.LEFT_JOYSTICK_Y),
                new Gamepad_Controls(gamepad1, Gamepad_Proportional_Inputs.LEFT_JOYSTICK_X, Gamepad_Controls.inversity.POSITIVE),
                new Gamepad_Controls(gamepad1, Gamepad_Proportional_Inputs.RIGHT_JOYSTICK_X), robot);

        telemetry.addLine("Initialized");
        telemetry.addData("Speed", robot.getSpeed());
        telemetry.update();
    }
    /**
     * the void loop is what is continuously updating while the program is running
     */
    @Override
    public void loop() {
        driveController.driveUpdate();
    }
}