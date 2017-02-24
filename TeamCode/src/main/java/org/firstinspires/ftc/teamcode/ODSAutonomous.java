package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by Morgan on 2/7/2017.
 */
@Autonomous(name = "Shockwave: ODSAutonomous", group = "Shockwave")
public class ODSAutonomous extends LinearOpMode {
    //init motors
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;

    public float redVal;
    public float blueVal;

    private boolean beaconOnePress = false;

    @Override
    public void runOpMode() throws InterruptedException {
        //set motors
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        //reverse motors
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        //set runmode //TODO
        /*
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        */
        //set ods
        OpticalDistanceSensor ods = hardwareMap.opticalDistanceSensor.get("ods");
        //set color sensor
        ColorSensor colorSensor = hardwareMap.colorSensor.get("colorSensor");
        //wait for coach to press start
        waitForStart();

        //run
        //set runmode //TODO
        /*
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //drive to line up with beacons
        frontLeftMotor.setPower(0.25);
        frontRightMotor.setPower(0.25);
        backLeftMotor.setPower(0.25);
        backRightMotor.setPower(0.25);
        setPosition(3360);
        sleep(4000);
        //turn back to position
        frontLeftMotor.setTargetPosition(5600);
        backLeftMotor.setTargetPosition(5600);
        sleep(1600);
        //drive forward to be level with beacon
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(500);
        setPosition(2240);
        sleep(1500);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        */
        //drive forward from wall
        driveForward(0.25, 2100);
        sleep(1000);
        //turn to be parallel with the wall5
        frontLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0.5);
        backRightMotor.setPower(0);
        sleep(900);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        sleep(1000);
        //check color
        while (beaconOnePress != true){
            if ((colorSensor.red() - 100) > colorSensor.blue()){
                /*
                frontLeftMotor.setPower(0.25);
                frontRightMotor.setPower(0.25);
                backLeftMotor.setPower(0.25);
                backRightMotor.setPower(0.25);
                sleep(250);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                */
                sleep(500);
                frontLeftMotor.setPower(-0.5);
                frontRightMotor.setPower(0.5);
                backLeftMotor.setPower(0.5);
                backRightMotor.setPower(-0.5);
                sleep(3000);
                frontLeftMotor.setPower(0.5);
                frontRightMotor.setPower(-0.5);
                backLeftMotor.setPower(-0.5);
                backRightMotor.setPower(0.5);
                sleep(3000);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
            } else {
                setPosition(3360);
                frontLeftMotor.setPower(0.25);
                frontRightMotor.setPower(0.25);
                backLeftMotor.setPower(0.25);
                backRightMotor.setPower(0.25);
                sleep(250);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
            }
            telemetry.addData("Red", colorSensor.red());
            telemetry.addData("Blue", colorSensor.blue());
            telemetry.update();
            sleep(1500);
        }
        /*
        while (beaconOnePress != true){
            if ((colorSensor.red() - 300) > colorSensor.blue()){
                frontLeftMotor.setPower(-0.5);
                frontRightMotor.setPower(0.5);
                backLeftMotor.setPower(0.5);
                backRightMotor.setPower(-0.5);
                sleep(3000);
                frontLeftMotor.setPower(0.5);
                frontRightMotor.setPower(-0.5);
                backLeftMotor.setPower(-0.5);
                backRightMotor.setPower(0.5);
                sleep(3000);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
            } else {
                frontLeftMotor.setPower(0.25);
                frontRightMotor.setPower(0.25);
                backLeftMotor.setPower(0.25);
                backRightMotor.setPower(0.25);
                sleep(2000);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
            }
        }
        */
        /*
        if(colorSensor.red() + 200 < colorSensor.blue()){
            //this is blue side, drive forward and press
            frontLeftMotor.setPower(0.25);
            frontRightMotor.setPower(0.25);
            backLeftMotor.setPower(0.25);
            backRightMotor.setPower(0.25);
            sleep(500);
            frontLeftMotor.setPower(-0.5);
            frontRightMotor.setPower(0.5);
            backLeftMotor.setPower(0.5);
            backRightMotor.setPower(-0.5);
            sleep(3000);
        } else if (colorSensor.blue() + 200 < colorSensor.red()){
            //this is red side, press
            frontLeftMotor.setPower(-0.35);
            frontRightMotor.setPower(0.35);
            backLeftMotor.setPower(0.35);
            backRightMotor.setPower(-0.35);
            sleep(3000);
        }
        */
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        //Update telemetry on color values
        while (opModeIsActive()) {
            telemetry.addData("Red", redVal);
            telemetry.addData("Blue", blueVal);
            telemetry.update();
        }
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

    void colorCheck() {
        if ((redVal - 200) > blueVal){
            frontLeftMotor.setPower(-1);
            frontRightMotor.setPower(1);
            backLeftMotor.setPower(1);
            backRightMotor.setPower(-1);
            sleep(3000);
            frontLeftMotor.setPower(0.5);
            frontRightMotor.setPower(-0.5);
            backLeftMotor.setPower(-0.5);
            backRightMotor.setPower(0.5);
            sleep(3000);
            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backLeftMotor.setPower(0);
            backRightMotor.setPower(0);
        } else {
            setPosition(3360);
            frontLeftMotor.setPower(0.25);
            frontRightMotor.setPower(0.25);
            backLeftMotor.setPower(0.25);
            backRightMotor.setPower(0.25);
            sleep(500);
            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backLeftMotor.setPower(0);
            backRightMotor.setPower(0);
        }
    }

    void telemetryUpdate(){
        //redVal = colorSensor.red();
        //blueVal = colorSensor.blue();
        telemetry.addData("Red", redVal);
        telemetry.addData("Blue", blueVal);
        telemetry.update();
    }
}
