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

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Shockwave: Autonomous 2016 VelVort", group = "Shockwave")
// @Autonomous(...) is the other common choice
public class Autonomous extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor elevatorMotor;
    private DcMotor launchMotor;
    private DcMotor liftMotor;
    private DcMotor leftFlicker;
    private DcMotor rightFlicker;
    private Servo forkliftServoL;
    private Servo forkliftServoR;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        elevatorMotor = hardwareMap.dcMotor.get("elevatorMotor");
        launchMotor = hardwareMap.dcMotor.get("launchMotor");
        liftMotor = hardwareMap.dcMotor.get("liftMotor");
        forkliftServoL = hardwareMap.servo.get("forkliftServoL");
        forkliftServoR = hardwareMap.servo.get("forkliftServoR");
        leftFlicker = hardwareMap.dcMotor.get("leftFlicker");
        rightFlicker = hardwareMap.dcMotor.get("rightFlicker");
        ArrayList<DcMotor> driveWheels = new ArrayList<DcMotor>();
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        elevatorMotor.setDirection(DcMotor.Direction.REVERSE);
        launchMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFlicker.setDirection(DcMotor.Direction.REVERSE);
        forkliftServoL.setDirection(Servo.Direction.REVERSE);
        forkliftServoR.setDirection(Servo.Direction.FORWARD);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        forkliftServoL.setPosition(80);
        forkliftServoR.setPosition(80);
        driveWheels.add(leftMotor);
        driveWheels.add(rightMotor);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        runtime.reset();
        leftMotor.setPower(0.5);
        rightMotor.setPower(0.5);
        driveInches(30, driveWheels);
        sleep(2000);
        driveInches(30, leftMotor, 1120/4);
        elevatorMotor.setPower(-0.25);
        sleep(1000);
        elevatorMotor.setPower(0);
        sleep(1000);
        launchMotor.setPower(1);
        sleep(1300);
        launchMotor.setPower(0);
        elevatorMotor.setPower(0.25);
        sleep(1000);
        elevatorMotor.setPower(0);
        driveInches(40, driveWheels);
        for (int i = 0; i < 2; i++) {
            driveInches(46, leftMotor);
            sleep(1500);
            driveInches(36, leftMotor);
            sleep(1500);
        }
        driveInches(46, driveWheels);
        setCollectivePower(0, driveWheels);

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running: " + runtime.toString());
            telemetry.update();
        }
    }

    void driveInches(int inches, DcMotor motor) {
        motor.setTargetPosition((int) (inches / (4 * Math.PI)) * 1440);
    }

    void driveInches(int inches, ArrayList<DcMotor> motors) {
        for (DcMotor motor : motors) {
            motor.setTargetPosition((int) (inches / (4 * Math.PI)) * 1120);
        }
    }

    void driveInches(int inches, DcMotor motor, int add) {
        motor.setTargetPosition(add + (int) (inches / (4 * Math.PI)) * 1120);
    }

    void setCollectivePower(float power, ArrayList<DcMotor> motors) {
        for (DcMotor motor : motors) {
            motor.setPower(power);
        }
    }
}
