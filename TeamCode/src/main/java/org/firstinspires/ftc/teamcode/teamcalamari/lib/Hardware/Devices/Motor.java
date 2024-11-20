package org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Devices;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

public class Motor implements DcMotor {

    DcMotor dcMotor;
    public Motor(DcMotor dcMotor){
        this.dcMotor = dcMotor;
    }

    @Override
    public MotorConfigurationType getMotorType() {
        return dcMotor.getMotorType();
    }

    @Override
    public void setMotorType(MotorConfigurationType motorType) {
        dcMotor.setMotorType(motorType);
    }

    @Override
    public DcMotorController getController() {
        return dcMotor.getController();
    }

    @Override
    public int getPortNumber() {
        return dcMotor.getPortNumber();
    }

    @Override
    public void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior) {
        dcMotor.setZeroPowerBehavior(zeroPowerBehavior);
    }

    @Override
    public ZeroPowerBehavior getZeroPowerBehavior() {
        return dcMotor.getZeroPowerBehavior();
    }

    @Override
    public void setPowerFloat() {
        dcMotor.setPowerFloat();
    }

    @Override
    public boolean getPowerFloat() {
        return dcMotor.getPowerFloat();
    }

    @Override
    public void setTargetPosition(int position) {
        dcMotor.setTargetPosition(position);
    }

    @Override
    public int getTargetPosition() {
        return dcMotor.getTargetPosition();
    }

    @Override
    public boolean isBusy() {
        return dcMotor.isBusy();
    }

    @Override
    public int getCurrentPosition() {
        return dcMotor.getCurrentPosition();
    }

    @Override
    public void setMode(RunMode mode) {
        dcMotor.setMode(mode);
    }

    @Override
    public RunMode getMode() {
        return dcMotor.getMode();
    }

    @Override
    public void setDirection(Direction direction) {
        dcMotor.setDirection(direction);
    }

    @Override
    public Direction getDirection() {
        return dcMotor.getDirection();
    }

    @Override
    public void setPower(double power) {
        dcMotor.setPower(power);
    }

    @Override
    public double getPower() {
        return dcMotor.getPower();
    }

    @Override
    public Manufacturer getManufacturer() {
        return dcMotor.getManufacturer();
    }

    @Override
    public String getDeviceName() {
        return dcMotor.getDeviceName();
    }

    @Override
    public String getConnectionInfo() {
        return dcMotor.getConnectionInfo();
    }

    @Override
    public int getVersion() {
        return dcMotor.getVersion();
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {
        dcMotor.resetDeviceConfigurationForOpMode();
    }

    @Override
    public void close() {
        dcMotor.close();
    }
    public String getUselessText(){
        return "If you are actually reading this, you must know how much time and effort goes into writing code like this. It is very very very very painful. That's why all programmers are chads 😎";
    }
}
