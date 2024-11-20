package org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Devices;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class GenericIMU implements com.qualcomm.robotcore.hardware.IMU {
    private final com.qualcomm.robotcore.hardware.IMU imu;
    public final IMUType type;
    public enum IMUType{
        BHI260, BNO055;

        public Class<?> imuClass(){
            switch (this){
                case BNO055:
                    return BNO055IMU.class;
                case BHI260:
                    return BHI260IMU.class;
                default:
                    return null;
            }
        }
    }

    public enum IMUDirection {
        UP,
        DOWN,
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT;

        public RevHubOrientationOnRobot.LogoFacingDirection asLogo(){
            return RevHubOrientationOnRobot.LogoFacingDirection.values()[this.ordinal()];
        }
        public RevHubOrientationOnRobot.UsbFacingDirection asUsb(){
            return RevHubOrientationOnRobot.UsbFacingDirection.values()[this.ordinal()];
        }
    }

    public static class IMUConfig{
        public IMUDirection logoFacing;
        public IMUDirection usbFacing;
    }

    public GenericIMU(IMUType type, HardwareMap hw, String imuLabel){
        this.type = type;
        this.imu = (com.qualcomm.robotcore.hardware.IMU) hw.get(type.imuClass(), imuLabel);
    }

    // Default IMU init
    @Override
    public boolean initialize(Parameters parameters) {
        return imu.initialize(parameters);
    }

    // Better + default
    public boolean initialize(IMUConfig parameters) {
        return initialize(new com.qualcomm.robotcore.hardware.IMU.Parameters(new RevHubOrientationOnRobot(parameters.logoFacing.asLogo(), parameters.usbFacing.asUsb())));
    }
    public boolean initialize() {
        return initialize(new IMUConfig(){{
            logoFacing = IMUDirection.RIGHT;
            usbFacing = IMUDirection.UP;
        }});
    }


    @Override
    public void resetYaw() {
        imu.resetYaw();
    }

    @Override
    public YawPitchRollAngles getRobotYawPitchRollAngles() {
        return imu.getRobotYawPitchRollAngles();
    }

    @Override
    public Orientation getRobotOrientation(AxesReference reference, AxesOrder order, AngleUnit angleUnit) {
        return imu.getRobotOrientation(reference, order, angleUnit);
    }

    @Override
    public Quaternion getRobotOrientationAsQuaternion() {
        return imu.getRobotOrientationAsQuaternion();
    }

    @Override
    public AngularVelocity getRobotAngularVelocity(AngleUnit angleUnit) {
        return imu.getRobotAngularVelocity(angleUnit);
    }

    @Override
    public Manufacturer getManufacturer() {
        return imu.getManufacturer();
    }

    @Override
    public String getDeviceName() {
        return imu.getDeviceName();
    }

    @Override
    public String getConnectionInfo() {
        return imu.getConnectionInfo();
    }

    @Override
    public int getVersion() {
        return imu.getVersion();
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {
        imu.resetDeviceConfigurationForOpMode();
    }

    @Override
    public void close() {
        imu.close();
    }
}
