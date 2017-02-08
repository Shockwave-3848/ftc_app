package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

/**
 * Created by Morgan on 2/7/2017.
 */

public class VuforiaOp extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        params.vuforiaLicenseKey = "Aa83fhD/////AAAAGSreheLdukF2jypD8HJoFgQqlRprE9XRkf4XhrjfDP8jqGm87y+kPUTCNqZE+fFB5raNLLV3o8eaPysaRCvB92gLKgGMXwCB+329W03utrqsgSsXtp6xoqYYg3fZiFWzp0og3AkodJHWrC/IN0oFuEXthsRsDk2SUebG8EnKHDOivyvbQ2w3O7nsEOB1EMBK8UYxnb8RR1Iw0XqwFGwbo5LNLx+8nwQjkRAQfAtzXSC3O+yJ86FxAieglPCZFLeYJn9Pl/VQjVeKGrttwK7uJz4Qwf7YKyfoluyBIJd9RlJ0EwD7HYht9jyD0PMQpHHQB5y9EO3LPQ48fmq7BgoFtwUJVMCt2oKfFbDOoVIgQ4PG";
        params.cameraMonitorFeedback = AXES;

        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

        VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
        beacons.get(0).setName("Wheels");
        beacons.get(0).setName("Tools");
        beacons.get(0).setName("Legos");
        beacons.get(0).setName("Gears");

        waitForStart();
    }
}
