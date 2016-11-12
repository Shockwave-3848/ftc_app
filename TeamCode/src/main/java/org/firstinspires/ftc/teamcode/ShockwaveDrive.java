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
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TeleOp", group="Shockwave")
//@Disabled
public class ShockwaveDrive extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();

    HardwareMap map = hardwareMap;

    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;
    private DcMotor elevatorMotor = null;

    private float leftPower = 0;
    private float rightPower = 0;

    @Override
    public void init() {
        leftMotor = map.dcMotor.get("leftMotor");
        rightMotor = map.dcMotor.get("rightMotor");
        elevatorMotor = map.dcMotor.get("elevatorMotor");
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        elevatorMotor.setDirection(DcMotor.Direction.FORWARD);
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
        /* elevator motor setting */
        if(gamepad2.dpad_up) {
            elevatorMotor.setPower(0.5);
        }else if(gamepad2.dpad_down){ //TODO: Make autonomous positions using encoders
            elevatorMotor.setPower(-0.5);
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
    }

    @Override
    public void stop() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

}
