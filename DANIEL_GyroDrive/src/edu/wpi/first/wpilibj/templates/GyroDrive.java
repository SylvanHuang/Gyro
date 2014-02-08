package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class GyroDrive extends IterativeRobot {
    Joystick leftStick = new Joystick(1);
    Joystick rightStick = new Joystick(2);
    Gyro gyro = new Gyro(1);
    double curDir = gyro.getAngle();
    double goalDir = 0;
    double rightYaxis = rightStick.getY() * -1;
    double leftYaxis = leftStick.getY() * -1;
    double joyStickError = leftYaxis - rightYaxis;
    double gyroError = goalDir - curDir;    
    RobotDrive drive = new RobotDrive(5, 4, 7, 8);
    Encoder leftEncoder = new Encoder(4, 3);
    Encoder rightEncoder = new Encoder(2, 1);
    double rightDrive = 0;
    double leftDrive = 0;
    double mapRightPos = 0;
    double mapLeftPos = 0;
    double mapRightNeg = 0;
    double mapLeftNeg = 0;

    
    public void robotInit() {
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);        
        drive.setMaxOutput(0.8);
        rightYaxis = 0;
        leftYaxis = 0;
        gyroError = 0;
        
    }


    public void autonomousPeriodic() {

    }


    public void teleopPeriodic() {
        gyroError = (goalDir - curDir) / 90;
        gyroError = Util.deadZone(gyroError, -0.05, 0.05, 0);
        leftYaxis = leftStick.getY();
        rightYaxis = rightStick.getY();
        if ((rightYaxis > -0.1) && (rightYaxis < 0.1)) {
            rightYaxis = 0;
        }
        leftYaxis = Util.deadZone(leftYaxis, -0.1, 0.1, 0);
//        drive.tankDrive(leftYaxis, rightYaxis);
        joyStickError = (leftYaxis - rightYaxis) / 232  ;
        curDir = gyro.getAngle(); 
        goalDir = goalDir + joyStickError;
        rightDrive = rightYaxis + gyroError;
        leftDrive = leftYaxis - gyroError;
        if (rightDrive > 0) {
            mapRightPos = Util.map(rightDrive, 0, 1, 0.4, 1);
        } else if (rightDrive < 0) {
           mapRightNeg = Util.map(rightDrive, -1, 0, -1, -0.4);
        }
        
        if (leftDrive > 0) {
            mapLeftPos = Util.map(leftDrive, 0, 1, 0.4, 1);
        } else if (leftDrive < 0) {
            mapLeftNeg = Util.map(leftDrive, -1, 0, -1, -0.4);

        }
        
        
//        if (gyroError > 0) {
//            leftDrive = leftYaxis;
//            rightDrive = rightYaxis + gyroError;
//        } else if (gyroError < 0) {
//            rightDrive = rightYaxis;
//            leftDrive = leftYaxis - gyroError;
//        }
        
        
        
        
        if (rightStick.getRawButton(8)) {
            gyro.reset();
            goalDir = 0;
            leftDrive = 0;
            rightDrive =0;
        }
        
        System.out.println(leftDrive + "   " + rightDrive + "   " + goalDir + "   " + gyroError);
        
            
        drive.tankDrive(leftDrive, rightDrive);
        
    }
    

    public void testPeriodic() {
    
    }
}
