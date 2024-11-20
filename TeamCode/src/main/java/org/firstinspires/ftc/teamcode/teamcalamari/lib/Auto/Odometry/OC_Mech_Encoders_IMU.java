package org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.Odometry;

import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Position;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Devices.Motor;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Assemblies.Robots.MecanumBot;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Angle;

import java.util.List;

public class OC_Mech_Encoders_IMU extends Odometry {
    private final List<Motor> motors;
    private final IMU imu;
    public OC_Mech_Encoders_IMU(MecanumBot mechDrive, IMU imu){
        this.motors = mechDrive.wheelMotors();
        this.imu = imu;
    }

    @Override
    public void startTracking() {
        this.motors.forEach(M->{
            M.setMode(Motor.RunMode.STOP_AND_RESET_ENCODER);
            M.setMode(Motor.RunMode.RUN_USING_ENCODER);
        });
        imu.getRobotYawPitchRollAngles();
    }
    public Position getPosition() {
        int[] motorPos = new int[4];
        motorPos[0] = motors.get(0).getCurrentPosition();
        motorPos[1] = motors.get(1).getCurrentPosition();
        motorPos[2] = motors.get(2).getCurrentPosition();
        motorPos[3] = motors.get(3).getCurrentPosition();
        double x = (motorPos[3] - motorPos[1]) / 2.0 / ticks.ticksPerInch(true);
        double y = (motorPos[2] - motorPos[0]) / 2.0 / ticks.ticksPerInch(true);
        double rotatedX = (x * Math.cos(Math.PI / 4)) - (y * Math.sin(Math.PI / 4));
        double rotatedY = (x * Math.sin(Math.PI / 4)) + (y * Math.cos(Math.PI / 4));
        Vector vector = new Vector(Angle.add(getAngle(), new Angle(Math.atan2(rotatedY, rotatedX), AngleUnit.RADIANS)), Math.sqrt(Math.pow(rotatedY, 2) + Math.pow(rotatedX, 2)), DistanceUnit.INCH);
        return Vector.calculatedPosition(vector, new Position(DistanceUnit.INCH, 0, 0));
    }
    @Override
    public Angle getAngle() {
        return new Angle((imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).secondAngle),AngleUnit.DEGREES);
    }

    @Override
    public void endAndReset() {

    }
}
