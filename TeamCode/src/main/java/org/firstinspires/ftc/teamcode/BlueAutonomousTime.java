/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "ShockwaveTime: BLUE: Autonomous 2016 VelVort", group = "Shockwave")
// @Autonomous(...) is the other common choice
public class BlueAutonomousTime extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor flickerMotor;
    private DcMotor launchMotor;
    private Servo forkliftServoL;
    private Servo forkliftServoR;
    private final boolean LEFT = true;
    private final boolean RIGHT = false;
    private ColorSensor floorSensor;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        flickerMotor = hardwareMap.dcMotor.get("flickerMotor");
        launchMotor = hardwareMap.dcMotor.get("launchMotor");
        forkliftServoL = hardwareMap.servo.get("forkliftServoL");
        forkliftServoR = hardwareMap.servo.get("forkliftServoR");
        floorSensor = hardwareMap.colorSensor.get("floorColor");
        ArrayList<DcMotor> driveWheels = new ArrayList<DcMotor>();
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        flickerMotor.setDirection(DcMotor.Direction.REVERSE);
        launchMotor.setDirection(DcMotor.Direction.REVERSE);
        launchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        forkliftServoL.setDirection(Servo.Direction.FORWARD);
        forkliftServoR.setDirection(Servo.Direction.REVERSE);
        forkliftServoL.setPosition(80);
        forkliftServoR.setPosition(80);
        driveWheels.add(frontLeftMotor);
        driveWheels.add(frontRightMotor);
        driveWheels.add(backLeftMotor);
        driveWheels.add(backRightMotor);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        runtime.reset();                                                         //START CODE!!!

        //drive forward
        //driveInches(30, driveWheels);
        drive(1200, driveWheels);
        sleep(500);
        powerReset();
        slideTimeRight(4000, driveWheels);
        //slideTimeRight(2500);
        //go forward to first beacon (color sensor for line)
        backLeftMotor.setPower(0.25);
        backRightMotor.setPower(-0.25);
        frontLeftMotor.setPower(0.25);
        frontRightMotor.setPower(-0.25);
        sleep(3000);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        sleep(500);
        driveBack(400, driveWheels);
        while(floorSensor.alpha() > 0){
            backLeftMotor.setPower(-0.09);
            backRightMotor.setPower(-0.09);
            frontLeftMotor.setPower(-0.09);
            frontRightMotor.setPower(-0.09);
            telemetry.addData("Alpha", floorSensor.alpha());
            telemetry.addData("Red", floorSensor.red());
            telemetry.addData("Green", floorSensor.green());
            telemetry.addData("Blue", floorSensor.blue());
        }
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0.5);
        backRightMotor.setPower(-0.5);
        frontLeftMotor.setPower(-0.5);
        frontRightMotor.setPower(0.5);
        sleep(2000);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        //slide towards wall/

        //TODO Get the adafuit reading and hit button

        //drive to beacon 2/

        //TODO Get the adafruit reading and hit button 2

        //end

        sleep(15000);
    }

    void drive(int time, ArrayList<DcMotor> motors) {
        backLeftMotor.setPower(0.5);
        backRightMotor.setPower(0.5);
        frontLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0.5);
        sleep(time);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
    }

    void driveBack(int time, ArrayList<DcMotor> motors) {
        backLeftMotor.setPower(-0.25);
        backRightMotor.setPower(-0.25);
        frontLeftMotor.setPower(-0.25);
        frontRightMotor.setPower(-0.25);
        sleep(time);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
    }

    void motorInches(int inches, DcMotor motor) {
        motor.setTargetPosition((int) (motor.getTargetPosition() + (inches / (4 * Math.PI)) * 1120));
        sleep(inches * 100);
    }

    void driveInches(int inches, ArrayList<DcMotor> motors) {
        for (DcMotor motor : motors) {
            motor.setPower(0.5);
        }
        sleep(inches * 100);
        for (DcMotor motor : motors){
            motor.setPower(0);
        }
    }
    void driveInchesBackwards(int inches, ArrayList<DcMotor> motors) {
        for (DcMotor motor : motors) {
            motor.setTargetPosition((int) (motor.getTargetPosition() - (inches / (4 * Math.PI)) * 1120));
        }
        sleep(inches * 100);
    }
    void setCollectivePower(float power, ArrayList<DcMotor> motors) {
        for (DcMotor motor : motors) {
            motor.setPower(power);
        }
    }

    void launch() {
        launchMotor.setTargetPosition(launchMotor.getTargetPosition() + 3360);
        sleep(1500);
    }

    void encoderReset(ArrayList<DcMotor> motors) {
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }

    //if boolean "left" is true, it will slide left, otherwise, slide right
    void slideInches(boolean direction, int inches) {
        if (direction) {
            backLeftMotor.setTargetPosition((int) (backLeftMotor.getTargetPosition() + (inches / (4 * Math.PI)) * 1120));
            backRightMotor.setTargetPosition((int) (backRightMotor.getTargetPosition() - (inches / (4 * Math.PI)) * 1120));
            frontLeftMotor.setTargetPosition((int) (frontLeftMotor.getTargetPosition() - (inches / (4 * Math.PI)) * 1120));
            frontRightMotor.setTargetPosition((int) (frontRightMotor.getTargetPosition() + (inches / (4 * Math.PI)) * 1120));
        } else {
            backLeftMotor.setTargetPosition((int) (backLeftMotor.getTargetPosition() - (inches / (4 * Math.PI)) * 1120));
            backRightMotor.setTargetPosition((int) (backRightMotor.getTargetPosition() + (inches / (4 * Math.PI)) * 1120));
            frontLeftMotor.setTargetPosition((int) (frontLeftMotor.getTargetPosition() + (inches / (4 * Math.PI)) * 1120));
            frontRightMotor.setTargetPosition((int) (frontRightMotor.getTargetPosition() - (inches / (4 * Math.PI)) * 1120));
        }
        sleep(inches * 100);
    }

    void slideTimeLeft(int time) {
        backLeftMotor.setPower(0.5);
        backRightMotor.setPower(-0.5);
        frontLeftMotor.setPower(-0.5);
        frontRightMotor.setPower(0.5);
        sleep(time);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
    }

    void slideTimeRight(int time, ArrayList<DcMotor> motors) {
        backLeftMotor.setPower(-0.5);
        backRightMotor.setPower(0.5);
        frontLeftMotor.setPower(0.5);
        frontRightMotor.setPower(-0.5);
        sleep(time);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
    }

    void powerReset() {
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        sleep(10);
    }
}
