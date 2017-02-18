package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

/**
 * Created by Morgan on 2/7/2017.
 */
@Autonomous(name = "Shockwave: VuforiaAutonomous", group = "Shockwave")
public class VuforiaOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //set up motors
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        //reverse motors
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        //set runmode
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //set up vuforia
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT; //set camera
        //set vuforia license key
        params.vuforiaLicenseKey = "Aa83fhD/////AAAAGSreheLdukF2jypD8HJoFgQqlRprE9XRkf4XhrjfDP8jqGm87y+kPUTCNqZE+fFB5raNLLV3o8eaPysaRCvB92gLKgGMXwCB+329W03utrqsgSsXtp6xoqYYg3fZiFWzp0og3AkodJHWrC/IN0oFuEXthsRsDk2SUebG8EnKHDOivyvbQ2w3O7nsEOB1EMBK8UYxnb8RR1Iw0XqwFGwbo5LNLx+8nwQjkRAQfAtzXSC3O+yJ86FxAieglPCZFLeYJn9Pl/VQjVeKGrttwK7uJz4Qwf7YKyfoluyBIJd9RlJ0EwD7HYht9jyD0PMQpHHQB5y9EO3LPQ48fmq7BgoFtwUJVMCt2oKfFbDOoVIgQ4PG";
        params.cameraMonitorFeedback = AXES; //set camera feedback icon (An axis is placed on photo in preview)

        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params); //create vuforialocalizer
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4); //set simultaneous image targets

        VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17"); //load beacon images
        beacons.get(0).setName("Wheels"); //add beacon images to array
        beacons.get(1).setName("Tools");
        beacons.get(2).setName("Legos");
        beacons.get(3).setName("Gears");

        VuforiaTrackableDefaultListener wheels = (VuforiaTrackableDefaultListener) beacons.get(0).getListener();

        waitForStart();

        beacons.activate(); //activate tracking
        //set runmode
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //drive to beacons
        frontLeftMotor.setPower(0.25);
        frontRightMotor.setPower(0.25);
        backLeftMotor.setPower(0.25);
        backRightMotor.setPower(0.25);
        sleep(1000);
        //Stop (after beacons have been seen)
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        sleep(1000);
        //Analyze beacon (Next video)

        VectorF angles = anglesFromTarget(wheels); //get angles

        VectorF trans = navOffWall(wheels.getPose().getTranslation(), Math.toDegrees(angles.get(0)) - 90, new VectorF(500, 0, 0)); //navigation values CHANGE: 500, 0, and 0 as X,Y,Z values away from the wall. This program will run 500mm off the wall.

        if (trans.get(0) > 0) { //turn to the right
            frontLeftMotor.setPower(0.2);
            frontRightMotor.setPower(-0.2);
            backLeftMotor.setPower(0.2);
            backRightMotor.setPower(-0.2);
        } else {
            frontLeftMotor.setPower(-0.2);
            frontRightMotor.setPower(0.2);
            backLeftMotor.setPower(-0.2);
            backRightMotor.setPower(0.2);
        }

        do {
            if (wheels.getPose() != null) {
                trans = navOffWall(wheels.getPose().getTranslation(), Math.toDegrees(angles.get(0)) - 90, new VectorF(500, 0, 0));
            }
            idle();
        } while (opModeIsActive() && Math.abs(trans.get(0)) > 30);

        //Stop
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        //Set mode for encoders
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Do stuff
        frontLeftMotor.setTargetPosition((int) (frontLeftMotor.getCurrentPosition() + ((Math.hypot(trans.get(0), trans.get(2)) + 100) / 4 * Math.PI * 25.4 * 1120)));//100 is the distance in mm from the axis of turning to the center of the phone.
        frontRightMotor.setTargetPosition((int) (frontRightMotor.getCurrentPosition() + ((Math.hypot(trans.get(0), trans.get(2)) + 100) / 4 * Math.PI * 25.4 * 1120)));
        backLeftMotor.setTargetPosition((int) (backLeftMotor.getCurrentPosition() + ((Math.hypot(trans.get(0), trans.get(2)) + 100) / 4 * Math.PI * 25.4 * 1120)));
        backRightMotor.setTargetPosition((int) (backRightMotor.getCurrentPosition() + ((Math.hypot(trans.get(0), trans.get(2)) + 100) / 4 * Math.PI * 25.4 * 1120)));

        //Drive
        frontLeftMotor.setPower(0.3);
        frontRightMotor.setPower(0.3);
        backLeftMotor.setPower(0.3);
        backRightMotor.setPower(0.3);

        while (opModeIsActive() && frontLeftMotor.isBusy() && frontRightMotor.isBusy() && backLeftMotor.isBusy() && backRightMotor.isBusy()) {
            idle();
        }
        //Stop
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        //set runmode
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && (wheels.getPose() == null || Math.abs(wheels.getPose().getTranslation().get(0)) > 10)) {
            if (wheels.getPose() != null) {
                if (wheels.getPose().getTranslation().get(0) > 0) {
                    //Turn to left
                    frontLeftMotor.setPower(-0.3);
                    frontRightMotor.setPower(0.3);
                    backLeftMotor.setPower(-0.3);
                    backRightMotor.setPower(0.3);
                } else {
                    //Turn to right
                    frontLeftMotor.setPower(0.3);
                    frontRightMotor.setPower(-0.3);
                    backLeftMotor.setPower(0.3);
                    backRightMotor.setPower(-0.3);
                }
            } else {
                //Turn to right
                frontLeftMotor.setPower(0.3);
                frontRightMotor.setPower(-0.3);
                backLeftMotor.setPower(0.3);
                backRightMotor.setPower(-0.3);
            }
        }

        //Stop
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
    }

    public VectorF navOffWall(VectorF trans, double robotAngle, VectorF offWall) {
        return new VectorF((float) (trans.get(0) - offWall.get(0) * Math.sin(Math.toRadians(robotAngle)) - offWall.get(2) * Math.cos(Math.toRadians(robotAngle))), trans.get(1), (float) (trans.get(2) + offWall.get(0) * Math.cos(Math.toRadians(robotAngle)) - offWall.get(2) * Math.sin(Math.toRadians(robotAngle))));
    }

    public VectorF anglesFromTarget(VuforiaTrackableDefaultListener image) {
        float[] data = image.getRawPose().getData();
        float[][] rotation = {{data[0], data[1]}, {data[4], data[5], data[6]}, {data[8], data[9], data[10]}};
        double thetaX = Math.atan2(rotation[2][1], rotation[2][2]);
        double thetaY = Math.atan2(-rotation[2][0], Math.sqrt(rotation[2][1] * rotation[2][1] + rotation[2][2] * rotation[2][2]));
        double thetaZ = Math.atan2(rotation[1][0], rotation[0][0]);
        return new VectorF((float) thetaX, (float) thetaY, (float) thetaZ);
    }
}
