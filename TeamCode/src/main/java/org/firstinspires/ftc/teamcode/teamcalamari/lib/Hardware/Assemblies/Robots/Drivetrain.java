package org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Assemblies.Robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Assemblies.Assembly;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Devices.Motor;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Angle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class implements THE_TEMPLATE_OF_ALL_TEMPLATES, and is the template used for all drivetrains
 */
public abstract class Drivetrain implements Assembly {
    protected HardwareMap hardwareMap;
    protected List<String> motorNames;
    protected List<Motor> wheelMotors;
    protected double speed;

    /**
     * This enum is for the two possible rotations of motors in the drivetrain
     */
    public enum rotateDirection {
        CCW,
        CW
    }

    /**
     * This is the constructor for the class
     * @param hardwareMap the hardware map
     * @param motorNames the String names of the motors to be entered into the config
     */
    public Drivetrain(HardwareMap hardwareMap, String... motorNames) {
        this.hardwareMap = hardwareMap;
        this.motorNames = Arrays.asList(motorNames);
        this.wheelMotors = new ArrayList<Motor>();
    }

    @Override
    public void init() {
        for (String motorName : motorNames) {
            Motor motor = new Motor(hardwareMap.dcMotor.get(motorName));
            wheelMotors.add(motor);
            motor.setMode(Motor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(Motor.RunMode.RUN_USING_ENCODER);
        }
    }

    @Override
    public void stop() {
        for (Motor motorList : wheelMotors) {
            motorList.setPower(0);
        }
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double velocity) {
        if (velocity >= 0 && velocity <= 1) {
            speed = velocity;
        } else {
            throw new IllegalArgumentException("You are not playing Sonic...Your speed is out of legal limits");
        }
    }

    public List<Motor> wheelMotors() {
        return wheelMotors;
    }

    public abstract void driveAtAngle(Angle angle);

    public abstract void turnDirection(rotateDirection direction);

    public void manualDriving(double... wheelPowers) {
        if (wheelMotors.size() > wheelPowers.length) {
            throw new IllegalArgumentException("You need to power all your motors!");
        } else if (wheelMotors.size() < wheelPowers.length) {
            throw new IllegalArgumentException("Where does the extra power go??? I NEED TO KNOW!!!! TELL ME NOW OR HAMPTER WILL FIND YOU!!!");
        }
        double maxWheelPower = 0;
        for (int i = 0; i < wheelPowers.length; i++) {
            if (wheelPowers[i] > 1 || wheelPowers[i] < -1) {
                if (maxWheelPower < Math.abs(wheelPowers[i])) {
                    maxWheelPower = Math.abs(wheelPowers[i]);
                }
            }
        }
        if (maxWheelPower != 0) {
            for (int i = 0; i < wheelPowers.length; i++) {
                wheelPowers[i] /= maxWheelPower;
                if (wheelPowers[i] > 1 || wheelPowers[i] < -1) {
                    throw new RuntimeException("Congrats. You managed the impossible. You should really check your wheelPowers, since they somehow went out of bounds");
                }
            }
        }
        for (int i = 0; i < wheelPowers.length; i++){
            wheelMotors.get(i).setPower((wheelPowers[i]) * speed);
        }
    }
}