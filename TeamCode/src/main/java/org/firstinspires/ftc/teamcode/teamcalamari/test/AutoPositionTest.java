package org.firstinspires.ftc.teamcode.teamcalamari.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Assemblies.Robots.MecanumBot;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Position;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.MotionControllers.P_Loop;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.Pathing.AutoStep;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.Pathing.AutoPath;

@Autonomous(name = "Auto_Position_Test", group="Auto") //This is the name that comes up on the phone
public class AutoPositionTest extends LinearOpMode { //Extends a program we use for auto called "LinearOpMode"

    @Override
    public void runOpMode() {
        SimpleMecanumBot robot = new SimpleMecanumBot(hardwareMap);
        robot.init();
        robot.setSpeed(0.5);

        AutoPath epicPath = new AutoPath(20,
                new AutoStep(new Position(DistanceUnit.INCH, 0, 0)),
                new AutoStep(new Position(DistanceUnit.INCH, 10, 0)),
                new AutoStep(new Position(DistanceUnit.INCH, 10, 10)),
                new AutoStep(new Position(DistanceUnit.INCH, 0, 10)),
                new AutoStep(new Position(DistanceUnit.INCH, 0, 0))
        );

        P_Loop p_loop = new P_Loop(robot, robot.deadWheel, epicPath, ()->{
            telemetry.addData("Pos", robot.deadWheel.lastPosition);
            telemetry.update();
        });

        waitForStart();

        p_loop.navigate(this, 0.5);
    }
}
