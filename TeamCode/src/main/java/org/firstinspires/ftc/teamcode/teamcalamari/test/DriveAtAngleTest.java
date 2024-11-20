package org.firstinspires.ftc.teamcode.teamcalamari.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Assemblies.Robots.MecanumBot;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Angle;

@Autonomous(name="driveAtAngleTest", group = "test")
public class DriveAtAngleTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        MecanumBot bot = new SimpleMecanumBot(hardwareMap);
        bot.init();

        waitForStart();

        bot.driveAtAngle(new Angle(0));
        sleep(3000);

        requestOpModeStop();
    }
}
