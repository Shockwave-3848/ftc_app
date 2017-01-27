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

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Shockwave: TeleOp 2016 VelVort", group = "Shockwave")
//@Disabled
public class ShockwaveDrive extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor flickerMotor;
    private DcMotor launchMotor;
    private DcMotor liftMotor;
    private Servo forkliftServoL;
    private Servo forkliftServoR;
    //Private variables
    private float frontLeftPower = 0;
    private float frontRightPower = 0;
    private float backLeftPower = 0;
    private float backRightPower = 0;
    //boolean flicker = false;
    private float mecanumDriveSpeed = (float) 0.5;
    int launchPos = 0;

    @Override
    public void init() {
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        flickerMotor = hardwareMap.dcMotor.get("flickerMotor");
        launchMotor = hardwareMap.dcMotor.get("launchMotor");
        liftMotor = hardwareMap.dcMotor.get("liftMotor");
        forkliftServoL = hardwareMap.servo.get("forkliftServoL");
        forkliftServoR = hardwareMap.servo.get("forkliftServoR");
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        flickerMotor.setDirection(DcMotor.Direction.REVERSE);
        launchMotor.setDirection(DcMotor.Direction.REVERSE);
        forkliftServoL.setDirection(Servo.Direction.FORWARD);
        forkliftServoR.setDirection(Servo.Direction.REVERSE);
        forkliftServoL.setPosition(80);
        forkliftServoR.setPosition(80);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());
        telemetry.addData("Elevator", "Position: ", +flickerMotor.getCurrentPosition());

        /* elevator motor setting */
        if (gamepad2.y) {
            flickerMotor.setPower(1);
            telemetry.addData("Status", "Running: ", "Flicker In");
        } else if (gamepad2.a) {
            flickerMotor.setPower(-1);
            telemetry.addData("Status", "Running: ", "Flicker Out");
        } else {
            telemetry.addData("Status", "Running: ", "Flicker Stopped");
            flickerMotor.setPower(0);
        }
        /* end elevator motor setting */

        /* drive mapping */
        frontLeftPower = gamepad1.left_stick_y;
        backLeftPower = gamepad1.left_stick_y;
        frontRightPower = gamepad1.right_stick_y;
        backRightPower = gamepad1.right_stick_y;

        if (gamepad1.right_trigger > 0.2) {
            frontLeftPower = gamepad1.left_stick_y / 4;
            backLeftPower = gamepad1.left_stick_y / 4;
            frontRightPower = gamepad1.right_stick_y / 4;
            backRightPower = gamepad1.right_stick_y / 4;
        }

        if (gamepad1.dpad_up) {
            frontLeftPower = (float) 0.15;
            frontRightPower = (float) 0.15;
            backLeftPower = (float) 0.15;
            backRightPower = (float) 0.15;
        }
        if (gamepad1.dpad_down) {
            frontLeftPower = (float) -0.15;
            frontRightPower = (float) -0.15;
            backLeftPower = (float) -0.15;
            backRightPower = (float) -0.15;
        }
        if (gamepad1.dpad_right) {
            frontLeftPower = (float) 0.15;
            frontRightPower = (float) -0.15;
            backLeftPower = (float) -0.15;
            backRightPower = (float) 0.15;
        }
        if (gamepad1.dpad_left) {
            frontLeftPower = (float) -0.15;
            frontRightPower = (float) 0.15;
            backLeftPower = (float) 0.15;
            backRightPower = (float) -0.15;
        }
        /* end drive mapping */

        /* launch motor setting*/
        if (gamepad2.right_trigger > 0.1) {
            launchMotor.setPower(0.75);
        } else {
            launchMotor.setPower(0);
        }
        launchMotor.setTargetPosition(launchPos);
        /* end launch motor setting */

        /* lift motor setting */
        if (gamepad2.dpad_up) {
            liftMotor.setPower(1);
            telemetry.addData("Status", "Running: ", "Lift Up");
        } else if (gamepad2.dpad_down) { //TODO: Make autonomous positions using encoders
            liftMotor.setPower(-0.25);
            telemetry.addData("Status", "Running: ", "Lift Down");
        } else {
            liftMotor.setPower(0);
            telemetry.addData("Status", "Running: ", "Lift Stopped");
        }
        /* end lift motor setting */

        /* flicker motor setting */
        /*
        if (gamepad2.left_bumper) {
            flicker = !flicker;
            sleep(300);
        }
        if (flicker) {
            leftFlicker.setPower(0.2);
            rightFlicker.setPower(0.2);
        } else {
            leftFlicker.setPower(0);
            rightFlicker.setPower(0);
        }
        */
        /* end flicker motor setting */

        /* forklift release */
        if (gamepad1.left_bumper) {
            forkliftServoL.setPosition(0);
            forkliftServoR.setPosition(0);
        }

        /* end forklift release */

        /* mecanum drive */
        /*
        if(gamepad1.dpad_up){
            frontLeftPower = mecanumDriveSpeed;
            frontRightPower = mecanumDriveSpeed;
            backLeftPower = mecanumDriveSpeed;
            backRightPower = mecanumDriveSpeed;
        }
        if(gamepad1.dpad_right){
            frontLeftPower = mecanumDriveSpeed;
            frontRightPower = -mecanumDriveSpeed;
            backLeftPower = -mecanumDriveSpeed;
            backRightPower = mecanumDriveSpeed;
        }
        if(gamepad1.dpad_left){
            frontLeftPower = -mecanumDriveSpeed;
            frontRightPower = mecanumDriveSpeed;
            backLeftPower = mecanumDriveSpeed;
            backRightPower = -mecanumDriveSpeed;
        }
        if(gamepad1.dpad_down){
            frontLeftPower = -mecanumDriveSpeed;
            frontRightPower = -mecanumDriveSpeed;
            backLeftPower = -mecanumDriveSpeed;
            backRightPower = -mecanumDriveSpeed;
        } legacy mecanum */

        if (gamepad1.left_stick_x == -1 && gamepad1.right_stick_x == -1) { //go right
            frontLeftPower = mecanumDriveSpeed;
            frontRightPower = -mecanumDriveSpeed;
            backLeftPower = -mecanumDriveSpeed;
            backRightPower = mecanumDriveSpeed;
        }

        if (gamepad1.left_stick_x == 1 && gamepad1.right_stick_x == 1) { //go left
            frontLeftPower = -mecanumDriveSpeed;
            frontRightPower = mecanumDriveSpeed;
            backLeftPower = mecanumDriveSpeed;
            backRightPower = -mecanumDriveSpeed;
        }
        /* end mecanum drive */

        /* drive motor setting */
        backLeftMotor.setPower(backLeftPower);
        backRightMotor.setPower(backRightPower);
        frontLeftMotor.setPower(frontLeftPower);
        frontRightMotor.setPower(frontRightPower);
        /* end drive motor setting */

        if (gamepad1.right_trigger > 0.5) {
            mecanumDriveSpeed = (float) 0.3;
        } else {
            mecanumDriveSpeed = (float) 0.5;
        }
        telemetry.update();
    }

    @Override
    public void stop() {
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
    }
}
