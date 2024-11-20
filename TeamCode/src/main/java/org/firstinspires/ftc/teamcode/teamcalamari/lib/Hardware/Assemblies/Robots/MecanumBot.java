package org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Assemblies.Robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Angle;

public class MecanumBot extends Drivetrain {

    public MecanumBot(MecanumConfig config) {
        super(config.hardwareMap, config.backLeftMotor, config.frontLeftMotor, config.frontRightMotor, config.backRightMotor);
    }

    /**
     * @param angle
     */
    @Override
    public void driveAtAngle(Angle angle) {
        Angle rotateAngle = new Angle(angle.getAngleDegrees() + 45, AngleUnit.DEGREES);
        double x = Math.cos(rotateAngle.getAngleRadians());
        double y = Math.sin(rotateAngle.getAngleRadians());
        wheelMotors.get(0).setPower(x * speed);
        wheelMotors.get(1).setPower(y * speed);
        wheelMotors.get(2).setPower(-x * speed);
        wheelMotors.get(3).setPower(-y * speed);
    }

    /**
     * @param direction
     */
    @Override
    public void turnDirection(rotateDirection direction) {
        if (direction.equals(rotateDirection.CW)) {
            wheelMotors.get(0).setPower(speed);
            wheelMotors.get(1).setPower(speed);
            wheelMotors.get(2).setPower(speed);
            wheelMotors.get(3).setPower(speed);
        } else if (direction.equals(rotateDirection.CCW)) {
            wheelMotors.get(0).setPower(-speed);
            wheelMotors.get(1).setPower(-speed);
            wheelMotors.get(2).setPower(-speed);
            wheelMotors.get(3).setPower(-speed);
        }
    }

    public static class MecanumConfig{
        public HardwareMap hardwareMap;

        public String frontLeftMotor;
        public String frontRightMotor;
        public String backLeftMotor;
        public String backRightMotor;
    }
}