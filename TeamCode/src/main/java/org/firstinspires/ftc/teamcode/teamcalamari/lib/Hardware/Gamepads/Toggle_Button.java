package org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Gamepads;

import com.qualcomm.robotcore.hardware.Servo;

public class Toggle_Button {
    private boolean isInit = false;

    public Runnable enable_function;
    public Runnable disable_function;

    private boolean enabling = true;
    private boolean last_toggle = false;

    private double closedPos;
    private double openPos;

    public Servo servo;

    public void Initialize(Runnable enable_function, Runnable disable_function, Servo servo, double closedPos, double openPos) {
        if(!isInit) {
            this.enable_function = enable_function;
            this.disable_function = disable_function;
            this.servo = servo;

            this.closedPos = closedPos;
            this.openPos = openPos;
        }
        isInit = true;
    }

    public void update(boolean toggle) {
        if(!isInit) return;

        if(toggle && !last_toggle) {
            if(enabling) {
                enable_function.run();
            } else {
                disable_function.run();
            }

            enabling = !enabling;
        }

        last_toggle = toggle;
    }

    public void openGate() {
        servo.setPosition(openPos);
    }

    public void closeGate() {
        servo.setPosition(closedPos);
    }
}