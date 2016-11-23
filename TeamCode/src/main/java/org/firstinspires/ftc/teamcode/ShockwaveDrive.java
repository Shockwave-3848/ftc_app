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

import static android.os.SystemClock.sleep;

@TeleOp(name="Shockwave: TeleOp", group="Shockwave")
//@Disabled
public class ShockwaveDrive extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;
    private DcMotor elevatorMotor = null;
    private DcMotor launchMotor = null;
    private DcMotor liftMotor = null;
    private DcMotor leftFlicker = null;
    private DcMotor rightFlicker = null;
    public Servo forkliftServo = null;

    private float leftPower = 0;
    private float rightPower = 0;
    int launchVar = 0;

    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        elevatorMotor = hardwareMap.dcMotor.get("elevatorMotor");
        launchMotor = hardwareMap.dcMotor.get("launchMotor");
        liftMotor = hardwareMap.dcMotor.get("liftMotor");
        forkliftServo = hardwareMap.servo.get("forkliftServo");
        leftFlicker = hardwareMap.dcMotor.get("leftFlicker");
        rightFlicker = hardwareMap.dcMotor.get("rightFlicker");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        elevatorMotor.setDirection(DcMotor.Direction.FORWARD);
        launchMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFlicker.setDirection(DcMotor.Direction.REVERSE); //TODO Test to see which flicker needs to be reversed, left or right.  I made right reversed for now, but that is temperary until testing
        launchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Status", "Initialized");
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
        /* drive motor setting */
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
        /* end drive motor setting */

        /* elevator motor setting */ /*
        if(gamepad2.y) { //TODO: Find positions for the motor
            //elevatorMotor.setTargetPosition(POSITION);
            telemetry.addData("Status", "Running: ", "Elevator Up");
        }else if(gamepad2.a) {
            //elevatorMotor.setTargetPosition(POSITION);
            telemetry.addData("Status", "Running: ", "Elevator Down");
        }else if(gamepad2.right_bumper) {
            //elevatorMotor.setTargetPosition(POSITION);
            telemetry.addData("Status", "Running: ", "Elevator Stow");
        } */
          //////////OLD CODE//////////
        if(gamepad1.dpad_up) {
            elevatorMotor.setPower(0.25);
            telemetry.addData("Status", "Running: ", "Elevator Up");
        }else if(gamepad1.dpad_down){
            elevatorMotor.setPower(-0.25);
            telemetry.addData("Status", "Running: ", "Elevator Down");
        }else{
            telemetry.addData("Status", "Running: ", "Elevator Stopped");
            elevatorMotor.setPower(0);
        }

        /* end elevator motor setting */

        /* less than 80% half-speed mapping*/
        if(gamepad1.left_stick_y < 0.8){
            leftPower = gamepad1.left_stick_y/2;
        }else{
            leftPower = gamepad1.left_stick_y;
        }
        if(gamepad1.right_stick_y < 0.8){
            rightPower = gamepad1.right_stick_y/2;
        }else{
            rightPower = gamepad1.right_stick_y;
        }
        /* end less than 80% half-speed mapping */

        /* launch motor setting*/
        if(gamepad2.right_trigger > 0.10) {
            launchVar += 100;
            launchMotor.setPower(1);
            launchMotor.setTargetPosition(launchVar);
            sleep(300);
        }
        telemetry.addData("launchMotor", launchMotor.getTargetPosition());
        /* end launch motor setting */
        /* lift motor setting */
        if(gamepad2.dpad_up) {
            liftMotor.setPower(0.5);
            telemetry.addData("Status", "Running: ", "Lift Up");
        }else if(gamepad2.dpad_down){ //TODO: Make autonomous positions using encoders
            liftMotor.setPower(-0.25);
            telemetry.addData("Status", "Running: ", "Lift Down");
        }else{
            liftMotor.setPower(0);
            telemetry.addData("Status", "Running: ", "Lift Stopped");
        }
        /* end lift motor setting */

        /* flicker motor setting */ //TODO get this to work
        /*if(gamepad2.left_bumper) {
            leftFlicker.setPower(1);
            rightFlicker.setPower(1);
        } else if(gamepad2.left_trigger) {
            leftFlicker.setPower(0);
            rightFlicker.setPower(0);
        }*/
        /* end flicker motor setting */
    }

    @Override
    public void stop() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}
