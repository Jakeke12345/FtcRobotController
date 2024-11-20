package org.firstinspires.ftc.teamcode.teamcalamari.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Assemblies.Robots.MecanumBot;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Devices.Motor;

@Autonomous(name="spinEachWheelTest", group = "test")
public class SpinEachWheelTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        MecanumBot bot = new SimpleMecanumBot(hardwareMap);
        bot.init();

        waitForStart();

        for (Motor m : bot.wheelMotors()){
            m.setPower(1);
            sleep(1000);
            m.setPower(0);
        }

        requestOpModeStop();
    }
}
