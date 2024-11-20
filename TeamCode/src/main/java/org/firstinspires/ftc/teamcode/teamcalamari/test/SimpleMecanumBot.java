package org.firstinspires.ftc.teamcode.teamcalamari.test;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.Odometry.OC3_deadwheel;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Assemblies.Robots.MecanumBot;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Devices.GenericIMU;

/**
 * The robotclass for the CADrivetrain
 */
public class SimpleMecanumBot extends MecanumBot {

    public GenericIMU imu;

    public OC3_deadwheel deadWheel;

    public SimpleMecanumBot(HardwareMap hw){
        super(new MecanumConfig(){{
            hardwareMap=hw;

            backLeftMotor="backleft";
            backRightMotor="backright";
            frontLeftMotor="frontleft";
            frontRightMotor="frontright";
        }});

        imu = new GenericIMU(GenericIMU.IMUType.BHI260, hardwareMap, "imu");
        deadWheel = new OC3_deadwheel(wheelMotors.get(0), wheelMotors.get(1), wheelMotors.get(2), 0, 0, 0);
    }

    @Override
    public void init() {
        super.init();

        imu.initialize(new GenericIMU.IMUConfig(){{
            logoFacing = GenericIMU.IMUDirection.UP;
            usbFacing = GenericIMU.IMUDirection.BACKWARD;
        }});

        deadWheel.startTracking();
    }
    @Override
    public void stop() {
        super.stop();
        imu.close();
    }
}
