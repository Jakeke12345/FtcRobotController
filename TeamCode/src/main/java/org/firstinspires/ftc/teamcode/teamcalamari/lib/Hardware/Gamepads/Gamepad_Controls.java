package org.firstinspires.ftc.teamcode.teamcalamari.lib.Hardware.Gamepads;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Gamepad_Controls {
    private Gamepad gamepad;
    private Gampad_Buttons buttons;
    private Gamepad_Proportional_Inputs pinput;
    private inversity sign;
    byte b;
    public Gamepad_Controls(Gamepad gamepad, Gampad_Buttons buttons){
        this.buttons = buttons;
        this.gamepad = gamepad;
    }
    public Gamepad_Controls(Gamepad gamepad, Gamepad_Proportional_Inputs pinput, inversity sign){
        this.pinput = pinput;
        this.gamepad = gamepad;
        this.sign = sign;
        if (sign == inversity.POSITIVE){
            b = 1;
        } else if (sign == inversity.NEGATIVE) {
            b = -1;
        } else {
            throw new IllegalArgumentException("If you got this message, you are a magician.  You also messed up the inversity enum");
        }
    }
    public Gamepad_Controls(Gamepad gamepad, Gamepad_Proportional_Inputs pinput){
        this.pinput = pinput;
        this.gamepad = gamepad;
        b = 1;
    }
    public boolean getGamepadButtons(){
        switch (buttons){
            case BUTTON_A:
                return gamepad.a;
            case BUTTON_B:
                return gamepad.b;
            case BUTTON_X:
                return gamepad.x;
            case BUTTON_Y:
                return gamepad.y;
            case LEFT_JOYSTICK_BUTTON:
                return gamepad.left_stick_button;
            case RIGHT_JOYSTICK_BUTTON:
                return gamepad.right_stick_button;
            case RIGHT_BUMPER:
                return gamepad.right_bumper;
            case LEFT_BUMPER:
                return gamepad.left_bumper;
            case DPAD_UP:
                return gamepad.dpad_up;
            case DPAD_DOWN:
                return gamepad.dpad_down;
            case DPAD_LEFT:
                return gamepad.dpad_left;
            case DPAD_RIGHT:
                return gamepad.dpad_right;
            case DPAD_WRONG:
                throw new IllegalArgumentException("lol u stupid");
            case START_BUTTON:
                return gamepad.start;
            case BACK_BUTTON:
                return gamepad.back;
            case LOGO_BUTTON:
                return gamepad.guide;
            default:
                return false;
        }
    }
    public double getGamepadProportionalInputs(){
        switch (pinput){
            case RIGHT_JOYSTICK_X:
                return gamepad.right_stick_x * b;
            case RIGHT_JOYSTICK_Y:
                return gamepad.right_stick_y * b;
            case LEFT_JOYSTICK_X:
                return gamepad.left_stick_x * b;
            case LEFT_JOYSTICK_Y:
                return gamepad.left_stick_y * b;
            case RIGHT_TRIGGER:
                return gamepad.right_trigger * b;
            case LEFT_TRIGGER:
                return gamepad.left_trigger * b;
            default:
                return 0.0;
        }
    }
    public enum inversity {
        POSITIVE,
        NEGATIVE
    }
}
