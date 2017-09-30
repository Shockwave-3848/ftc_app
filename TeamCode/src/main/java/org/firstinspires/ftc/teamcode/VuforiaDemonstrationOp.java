package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.Parameters.CameraMonitorFeedback.BUILDINGS;

/**
 * Created by Morgan on 2/7/2017.
 */
//@Autonomous(name = "Shockwave: VuforiaTest", group = "Shockwave")
public class VuforiaDemonstrationOp extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        params.vuforiaLicenseKey = "Aa83fhD/////AAAAGSreheLdukF2jypD8HJoFgQqlRprE9XRkf4XhrjfDP8jqGm87y+kPUTCNqZE+fFB5raNLLV3o8eaPysaRCvB92gLKgGMXwCB+329W03utrqsgSsXtp6xoqYYg3fZiFWzp0og3AkodJHWrC/IN0oFuEXthsRsDk2SUebG8EnKHDOivyvbQ2w3O7nsEOB1EMBK8UYxnb8RR1Iw0XqwFGwbo5LNLx+8nwQjkRAQfAtzXSC3O+yJ86FxAieglPCZFLeYJn9Pl/VQjVeKGrttwK7uJz4Qwf7YKyfoluyBIJd9RlJ0EwD7HYht9jyD0PMQpHHQB5y9EO3LPQ48fmq7BgoFtwUJVMCt2oKfFbDOoVIgQ4PG";
        params.cameraMonitorFeedback = BUILDINGS;

        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

        VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
        beacons.get(0).setName("Wheels");
        beacons.get(1).setName("Tools");
        beacons.get(2).setName("Legos");
        beacons.get(3).setName("Gears");

        waitForStart();

        beacons.activate();

        while (opModeIsActive()){
            for (VuforiaTrackable beac : beacons){
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();

                if (pose != null){
                    VectorF translation = pose.getTranslation();

                    telemetry.addData(beac.getName() + "-Translation", translation);

                    double degreesToTurn = Math.toDegrees(Math.atan2(translation.get(1),translation.get(2)));

                    telemetry.addData(beac.getName() + "-Degrees", degreesToTurn);
                }
            }
            telemetry.update();
        }
    }
}
