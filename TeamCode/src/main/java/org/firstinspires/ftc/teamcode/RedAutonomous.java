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

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Shockwave: RED: Autonomous 2016 VelVort", group = "Shockwave")
// @Autonomous(...) is the other common choice
public class RedAutonomous extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor flickerMotor;
    private DcMotor launchMotor;
    private Servo forkliftServoL;
    private Servo forkliftServoR;

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
        ArrayList<DcMotor> driveWheels = new ArrayList<DcMotor>();
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        flickerMotor.setDirection(DcMotor.Direction.REVERSE);
        launchMotor.setDirection(DcMotor.Direction.REVERSE);
        launchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        forkliftServoL.setDirection(Servo.Direction.FORWARD);
        forkliftServoR.setDirection(Servo.Direction.REVERSE);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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
        frontLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0.5);
        backLeftMotor.setPower(0.5);
        backRightMotor.setPower(0.5);
        launchMotor.setPower(0.75);

        //sleep  = delay at start of match based on alliance partner's needs
        sleep(10000);

        //drive forward
        driveInches(30, driveWheels);
        sleep(3000);

        //launch ball
        launch();

        //load second ball
        flickerMotor.setPower(1);
        sleep(3000);
        flickerMotor.setPower(0);

        //launch second ball
        launch();
        sleep(3000);
/*
                        //if no beacons, dislodge ball //LEGACY
        //drive forward
        motorInches(15, driveWheels);
        sleep(2000);

        //drive forward
        motorInches(8,driveWheels);
        sleep(2000);
*/
        //if beacons, drive towards wall depends on color

        //if red

            //go left

            //go forward to first beacon (color sensor for line)

            //go left into beacon (color sensor for beacons)

            //go right away from beacon

            //go forward towards second beacon (color sensor for line)

            //go left into beacon

        //end


        /*                                   //legacy autonomous code
        frontLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0.5);
        motorInches(30, driveWheels);
        sleep(2000);
        motorInches(30, frontLeftMotor, 1120 / 4);
        flickerMotor.setPower(-0.25);
        sleep(1000);
        flickerMotor.setPower(0);
        sleep(1000);
        launchMotor.setPower(1);
        sleep(1300);
        launchMotor.setPower(0);
        flickerMotor.setPower(0.25);
        sleep(1000);
        flickerMotor.setPower(0);
        motorInches(40, driveWheels);
        for (int i = 0; i < 2; i++) {
            motorInches(46, frontLeftMotor);
            sleep(1500);
            motorInches(36, frontLeftMotor);
            sleep(1500);
        }
        motorInches(46, driveWheels);
        setCollectivePower(0, driveWheels);

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running: " + runtime.toString());
            telemetry.update();
        }
        */
    }

    void driveInches(int inches, DcMotor motor) {
        motor.setTargetPosition((int) (motor.getTargetPosition() + (inches / (4 * Math.PI)) * 1120));
    }

    void driveInches(int inches, ArrayList<DcMotor> motors) {
        for (DcMotor motor : motors) {
            motor.setTargetPosition((int) (motor.getTargetPosition() + (inches / (4 * Math.PI)) * 1120));
        }
    }

    void setCollectivePower(float power, ArrayList<DcMotor> motors) {
        for (DcMotor motor : motors) {
            motor.setPower(power);
        }
    }

    void launch() {
        launchMotor.setTargetPosition(launchMotor.getTargetPosition() + 3360);
        sleep(2500);
    }
}
