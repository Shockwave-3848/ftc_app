package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Morgan on 2/7/2017.
 */
@Autonomous(name = "Shockwave: Red Beacon Autonomous", group = "Shockwave")
public class ODSAutonomous extends LinearOpMode {
    //init motors
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    private Servo forkliftServoL;
    private Servo forkliftServoR;

    private boolean beaconOnePress = false;
    private boolean beaconTwoPress = false;

    @Override
    public void runOpMode() throws InterruptedException {
        //set motors
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        forkliftServoL = hardwareMap.servo.get("forkliftServoL");
        forkliftServoR = hardwareMap.servo.get("forkliftServoR");
        forkliftServoL.setDirection(Servo.Direction.FORWARD);
        forkliftServoR.setDirection(Servo.Direction.REVERSE);
        forkliftServoL.setPosition(1);
        forkliftServoR.setPosition(1);

        //reverse motors
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);

        //set ods
        OpticalDistanceSensor ods = hardwareMap.opticalDistanceSensor.get("ods");

        //set color sensor
        ColorSensor colorSensor = hardwareMap.colorSensor.get("colorSensor");

        //wait for coach to press start
        waitForStart();

        //drive forward from wall
        driveForward(0.5, 1500);
        sleep(750);

        //turn to be parallel with the wall
        frontLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0.5);
        backRightMotor.setPower(0);
        sleep(850);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        sleep(500);

        //drive forard toward beacon
        //driveForward(0.5, 500);
        //sleep(500);

        //slide
        frontLeftMotor.setPower(-0.5);
        frontRightMotor.setPower(0.5);
        backLeftMotor.setPower(0.5);
        backRightMotor.setPower(-0.5);
        sleep(500);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        //press first beacon
        /*
        while (beaconOnePress != true){
            if ((colorSensor.red() - 100) > colorSensor.blue()){
                sleep(500);
                frontLeftMotor.setPower(-0.5);
                frontRightMotor.setPower(0.5);
                backLeftMotor.setPower(0.5);
                backRightMotor.setPower(-0.5);
                sleep(1650);
                frontLeftMotor.setPower(0.5);
                frontRightMotor.setPower(-0.5);
                backLeftMotor.setPower(-0.5);
                backRightMotor.setPower(0.5);
                sleep(800);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                beaconOnePress = true;
            } else {
                //move forward
                frontLeftMotor.setPower(0.3);
                frontRightMotor.setPower(0.3);
                backLeftMotor.setPower(0.3);
                backRightMotor.setPower(0.3);
                sleep(250);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
            }
            telemetry.addData("Red", colorSensor.red());
            telemetry.addData("Blue", colorSensor.blue());
            telemetry.update();
            sleep(1000);
        }
        */

        for(int i = 0; i < 5; i++){
            if ((colorSensor.red() - 100) > colorSensor.blue()){
                sleep(500);
                frontLeftMotor.setPower(-0.5);
                frontRightMotor.setPower(0.5);
                backLeftMotor.setPower(0.5);
                backRightMotor.setPower(-0.5);
                sleep(1650);
                frontLeftMotor.setPower(0.5);
                frontRightMotor.setPower(-0.5);
                backLeftMotor.setPower(-0.5);
                backRightMotor.setPower(0.5);
                sleep(800);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                beaconOnePress = true;
                break;
            } else {
                //move forward
                frontLeftMotor.setPower(0.3);
                frontRightMotor.setPower(0.3);
                backLeftMotor.setPower(0.3);
                backRightMotor.setPower(0.3);
                sleep(250);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
            }
            telemetry.addData("Red", colorSensor.red());
            telemetry.addData("Blue", colorSensor.blue());
            telemetry.update();
            sleep(1000);
        }

        //turn to be parallel with the wall
        frontLeftMotor.setPower(0.25);
        frontRightMotor.setPower(-0.25);
        backLeftMotor.setPower(0.25);
        backRightMotor.setPower(-0.25);
        sleep(200);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        //check to make sure beacon is the correct color
        if ((colorSensor.blue() - 100) > colorSensor.red()) {
            sleep(5000);
            frontLeftMotor.setPower(-0.5);
            frontRightMotor.setPower(0.5);
            backLeftMotor.setPower(0.5);
            backRightMotor.setPower(-0.5);
            sleep(1500);
            frontLeftMotor.setPower(0.5);
            frontRightMotor.setPower(-0.5);
            backLeftMotor.setPower(-0.5);
            backRightMotor.setPower(0.5);
            sleep(800);
            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backLeftMotor.setPower(0);
            backRightMotor.setPower(0);
            sleep(500);
            //turn to parallel with the wall
            frontLeftMotor.setPower(0.25);
            frontRightMotor.setPower(-0.25);
            backLeftMotor.setPower(0.25);
            backRightMotor.setPower(-0.25);
            sleep(100);
            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backLeftMotor.setPower(0);
            backRightMotor.setPower(0);
            //drive two second beacon
            driveForward(0.5, 1250);
            sleep(750);
        } else {
            //drive to second beacon
            driveForward(0.5, 1250);
            sleep(750);
        }

        //slide closer to the wall
        frontLeftMotor.setPower(-0.5);
        frontRightMotor.setPower(0.5);
        backLeftMotor.setPower(0.5);
        backRightMotor.setPower(-0.5);
        sleep(1000);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        //press second beacon
        /*
        while (beaconTwoPress != true && beaconOnePress == true){
            if ((colorSensor.red() - 100) > colorSensor.blue()){
                sleep(500);
                frontLeftMotor.setPower(-0.5);
                frontRightMotor.setPower(0.5);
                backLeftMotor.setPower(0.5);
                backRightMotor.setPower(-0.5);
                sleep(1650);
                frontLeftMotor.setPower(0.5);
                frontRightMotor.setPower(-0.5);
                backLeftMotor.setPower(-0.5);
                backRightMotor.setPower(0.5);
                sleep(1000);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                beaconTwoPress = true;
            } else {
                //move forward
                frontLeftMotor.setPower(0.3);
                frontRightMotor.setPower(0.3);
                backLeftMotor.setPower(0.3);
                backRightMotor.setPower(0.3);
                sleep(250);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
            }
            telemetry.addData("Red", colorSensor.red());
            telemetry.addData("Blue", colorSensor.blue());
            telemetry.update();
            sleep(1000);
        }
        */

        for(int i = 0; i < 5; i++){
            if ((colorSensor.red() - 100) > colorSensor.blue()){
                sleep(500);
                frontLeftMotor.setPower(-0.5);
                frontRightMotor.setPower(0.5);
                backLeftMotor.setPower(0.5);
                backRightMotor.setPower(-0.5);
                sleep(1650);
                frontLeftMotor.setPower(0.5);
                frontRightMotor.setPower(-0.5);
                backLeftMotor.setPower(-0.5);
                backRightMotor.setPower(0.5);
                sleep(800);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                beaconTwoPress = true;
                break;
            } else {
                //move forward
                frontLeftMotor.setPower(0.3);
                frontRightMotor.setPower(0.3);
                backLeftMotor.setPower(0.3);
                backRightMotor.setPower(0.3);
                sleep(250);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
            }
            telemetry.addData("Red", colorSensor.red());
            telemetry.addData("Blue", colorSensor.blue());
            telemetry.update();
            sleep(1000);
        }

        //check to make sure beacon is the correct color
        if ((colorSensor.blue() - 100) > colorSensor.red()) {
            sleep(5000);
            frontLeftMotor.setPower(-0.5);
            frontRightMotor.setPower(0.5);
            backLeftMotor.setPower(0.5);
            backRightMotor.setPower(-0.5);
            sleep(1500);
            frontLeftMotor.setPower(0.5);
            frontRightMotor.setPower(-0.5);
            backLeftMotor.setPower(-0.5);
            backRightMotor.setPower(0.5);
            sleep(800);
            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backLeftMotor.setPower(0);
            backRightMotor.setPower(0);
            sleep(500);
            //turn to parallel with the wall
            frontLeftMotor.setPower(0.25);
            frontRightMotor.setPower(-0.25);
            backLeftMotor.setPower(0.25);
            backRightMotor.setPower(-0.25);
            sleep(100);
            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backLeftMotor.setPower(0);
            backRightMotor.setPower(0);
        }

        //set all motor power to zero
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
    }
    void setPosition(int position) {
        frontLeftMotor.setTargetPosition(position);
        frontRightMotor.setTargetPosition(position);
        backLeftMotor.setTargetPosition(position);
        backRightMotor.setTargetPosition(position);
    }

    void driveForward(double power, int time) {
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        backLeftMotor.setPower(power);
        backRightMotor.setPower(power);
        sleep(time);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
    }

    void driveBack(double power, int time) {
        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(-power);
        backLeftMotor.setPower(-power);
        backRightMotor.setPower(-power);
        sleep(time);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
    }
}
