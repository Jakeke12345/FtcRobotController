package org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.Odometry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Position;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Devices.Motor;
import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Angle;

public class OC3_deadwheel extends Odometry {
    private final Motor right;
    private final Motor left;
    private final Motor back;
    private final double Ly;
    private final double Ry;
    private final double Bx;
    public double delta_r;
    public double delta_l;
    public double delta_b;
    public double delta_x;
    public double delta_y;
    public Angle delta_theta = new Angle(0.0, AngleUnit.DEGREES);
    public double[] lastEncoderPositions = new double[3];
    public double rotatedX;
    public double rotatedY;
    public Angle lastAngle = new Angle(0, AngleUnit.DEGREES);
    public Position lastPosition = new Position(DistanceUnit.INCH, 0, 0);
    public OC3_deadwheel(Motor right, Motor left, Motor back, double Ly, double Ry, double Bx){
        this.right = right;
        this.left = left;
        this.back = back;
        this.Ly = -Ly;
        this.Ry = Ry;
        this.Bx = Bx;
    }

    @Override
    public void startTracking(){
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public Position getPosition() {
        // Set up variables for algo
        double inchesPerTick = 1; // /ticks.ticksPerInch(false);
        Motor right = this.right;
        Motor left = this.left;
        Motor back = this.back;

        double last_r = lastEncoderPositions[0];
        double last_l = lastEncoderPositions[1];
        double last_b = lastEncoderPositions[2];

        // Algo
        delta_r = (right.getCurrentPosition()-last_r);
        delta_l = (left.getCurrentPosition()-last_l);
        delta_b = (back.getCurrentPosition()-last_b);

        lastEncoderPositions[0] += delta_r;
        lastEncoderPositions[1] += delta_l;
        lastEncoderPositions[2] += delta_b;

        delta_x = (delta_r*Ly+delta_l*Ry)/(Ly-Ry)*inchesPerTick;
        delta_theta = Angle.negateRadians(new Angle((delta_r+delta_l)/(Ly-Ry)*inchesPerTick, AngleUnit.RADIANS));
        delta_y = (delta_b-Bx*delta_theta.getAngleRadians())*inchesPerTick;

        lastAngle = Angle.add(lastAngle, delta_theta);
        //lines 79 & 80 are the problem
        //System.out.println(" " + lastPosition.getX() + ", " + delta_x + ", " + Math.cos (Angle.add(Angle.negateRadians(lastAngle), new Angle(90, AngleUnit.DEGREES)).getAngleRadians()) + ", " + delta_y + ", " + Math.sin(Angle.add(Angle.negateRadians(lastAngle), new Angle(90, AngleUnit.RADIANS)).getAngleRadians()));
        rotatedX = lastPosition.getX()+delta_x*Math.cos (Angle.add(Angle.negateRadians(lastAngle), new Angle(90, AngleUnit.DEGREES)).getAngleRadians())-delta_y*Math.sin(Angle.add(Angle.negateRadians(lastAngle), new Angle(90, AngleUnit.DEGREES)).getAngleRadians());
        rotatedY = lastPosition.getY()+delta_y*Math.cos (Angle.add(Angle.negateRadians(lastAngle), new Angle(90, AngleUnit.DEGREES)).getAngleRadians())+delta_x*Math.sin(Angle.add(Angle.negateRadians(lastAngle), new Angle(90, AngleUnit.DEGREES)).getAngleRadians());

        lastPosition = new Position(DistanceUnit.INCH, rotatedX, rotatedY);
        return lastPosition;
    }
    @Override
    public Angle getAngle() {
        return new Angle((imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).secondAngle),AngleUnit.DEGREES);
    }

    @Override
    public void endAndReset() {

    }
}
