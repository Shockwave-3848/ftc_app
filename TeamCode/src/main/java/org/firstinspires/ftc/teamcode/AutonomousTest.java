package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// DOES NOT WORK AS OF 10/26/15 DUE TO A BUG IN THE SDK REPORTED HERE
// http://ftcforum.usfirst.org/showthread.php?4576-Game-controller-input-during-init_loop()&p=18496&viewfull=1#post18496
// SHOULD WORK(?) WHEN THE BUG IS FIXED IN THE NEXT RELEASE!!!!!
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Shockwave: AutonomousTesting", group = "Shockwave")
public class AutonomousTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        FtcConfig ftcConfig = new FtcConfig();

        ftcConfig.init(hardwareMap.appContext, this);
        sleep(50);
        while (!opModeIsActive()) {
            ftcConfig.init_loop(hardwareMap.appContext, this);
            sleep(50);
        }
        //no waitForStart needed, put any config before this opModeIsActive loop. Works like an init_loop.
        sleep(ftcConfig.param.delayInMillis);
        
        while (opModeIsActive()) {
            telemetry.clear();
            telemetry.addData("A", "Running the LinearOpMode now");
            telemetry.addData("ColorIsRed", Boolean.toString(ftcConfig.param.colorIsRed));
            telemetry.addData("DelayInSec", Integer.toString(ftcConfig.param.delayInSec));
            telemetry.addData("AutonType", ftcConfig.param.autonType);
            waitOneFullHardwareCycle();
        }

        telemetry.clear();
        telemetry.addData("A", "LinearOpMode no longer running");

    }
}
