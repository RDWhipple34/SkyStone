/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Mecanumy", group = "")
//@Disabled
public class RealRobotMecanumWheels extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;
    private DcMotor InAndOut = null;
    int i = 0;
    int I = 0;
    double lf = .57;

    @Override
    public void runOpMode() {
        telemetry.addData("status", "Initialized");
        telemetry.update();


        leftFront = hardwareMap.get(DcMotor.class, "left_front");
        leftBack = hardwareMap.get(DcMotor.class, "left_back");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");
        rightBack = hardwareMap.get(DcMotor.class, "right_back");
        InAndOut = hardwareMap.get(DcMotor.class, "inandout");

        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        InAndOut.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        runtime.reset();



        while (opModeIsActive()) {
            Drive();
            MoveArmIn();
            MoveArmOut();

            // move forward
            //if ( move >= -1 && move < 0 ) {

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", InAndOut);
            telemetry.update();

        }
    }
    public void MoveArmOut() {
        Drive();
        if (gamepad2.y) {
            Drive();
            while (gamepad2.y){
                InAndOut.setPower(0.4);
            }
            i=0;
            while (i < 35) {
                Drive();
                InAndOut.setPower(0);
                sleep(10);
                InAndOut.setPower(-0.09);
                sleep(15);
                i++;
            }
        }

    }

    public void MoveArmIn() {
        Drive();
        if (gamepad2.a) {
            Drive();
            while (gamepad2.a){
                InAndOut.setPower(-0.4);
            }
            I=0;
            while (I < 35) {
                Drive();
                InAndOut.setPower(0);
                sleep(10);
                InAndOut.setPower(0.09);
                sleep(15);
                I++;
            }
        }

    }

    public void Drive() {

        if (gamepad1.dpad_up) {
            leftFront.setDirection(DcMotor.Direction.REVERSE);
            leftBack.setDirection(DcMotor.Direction.REVERSE);
            rightFront.setDirection(DcMotor.Direction.FORWARD);
            rightBack.setDirection(DcMotor.Direction.FORWARD);
            leftFront.setPower(lf);
            rightFront.setPower(1);
            leftBack.setPower(1);
            rightBack.setPower(1);
        }
        //move backwards
        else if (gamepad1.dpad_down) {
            leftFront.setDirection(DcMotor.Direction.FORWARD);
            leftBack.setDirection(DcMotor.Direction.FORWARD);
            rightFront.setDirection(DcMotor.Direction.REVERSE);
            rightBack.setDirection(DcMotor.Direction.REVERSE);
            leftFront.setPower(lf);
            rightFront.setPower(1);
            leftBack.setPower(1);
            rightBack.setPower(1);
        }
        //turn tank right
        else if (gamepad1.dpad_right) {
            leftFront.setDirection(DcMotor.Direction.REVERSE);
            leftBack.setDirection(DcMotor.Direction.REVERSE);
            rightFront.setDirection(DcMotor.Direction.REVERSE);
            rightBack.setDirection(DcMotor.Direction.REVERSE);
            leftFront.setPower(lf);
            rightFront.setPower(1);
            leftBack.setPower(1);
            rightBack.setPower(1);
        }
        //turn tank left
        else if (gamepad1.dpad_left) {
            leftFront.setDirection(DcMotor.Direction.FORWARD);
            leftBack.setDirection(DcMotor.Direction.FORWARD);
            rightFront.setDirection(DcMotor.Direction.FORWARD);
            rightBack.setDirection(DcMotor.Direction.FORWARD);
            leftFront.setPower(lf);
            rightFront.setPower(1);
            leftBack.setPower(1);
            rightBack.setPower(1);
        } else if (-gamepad1.right_stick_x == 1) {
            leftBack.setDirection(DcMotor.Direction.REVERSE);
            leftFront.setDirection(DcMotor.Direction.FORWARD);
            rightBack.setDirection(DcMotor.Direction.REVERSE);
            rightFront.setDirection(DcMotor.Direction.FORWARD);
            leftFront.setPower(lf);
            leftBack.setPower(1);
            rightFront.setPower(1);
            rightBack.setPower(1);
        } else if (gamepad1.right_stick_x == 1) {
            leftBack.setDirection(DcMotor.Direction.FORWARD);
            leftFront.setDirection(DcMotor.Direction.REVERSE);
            rightBack.setDirection(DcMotor.Direction.FORWARD);
            rightFront.setDirection(DcMotor.Direction.REVERSE);
            leftFront.setPower(lf);
            leftBack.setPower(1);
            rightFront.setPower(1);
            rightBack.setPower(1);
        } else {
            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);
        }
    }
}
