/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
    DriverStationEnhancedIO cypress = DriverStation.getInstance().getEnhancedIO();
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    DriverStationLCD lcd = DriverStationLCD.getInstance();
    Joystick leftStick = new Joystick(1);
    Joystick rightStick = new Joystick(2);
    RobotDrive drive = new RobotDrive(5, 4, 7, 8);
    double wheel = 0;
    double speedStick = 0;
    double difference = 0;
    double rightDrive = 0;
    double leftDrive = 0;
    double speed = 0;
    double curDir = 0;
    double goalDir = 0;
    Gyro gyro = new Gyro(1);
    
    
    
    public void robotInit() {
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        
       
 
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        curDir = gyro.getAngle();
        
//        Joystick Wheel and SpeedStick start
        wheel = rightStick.getX();
        if (wheel < 0) {
            wheel = Util.map(wheel, -1, 0, -1, -0.1);
        } else if (wheel > 0) {
            wheel = Util.map(wheel, 0, 1, 0.1, 1);
        }
        wheel = Util.deadZone(wheel, -0.15, 0.15, 0);

        speedStick = leftStick.getY();
        if (speedStick < 0) {
            speedStick = Util.map(speedStick, -1, 0, -1, -0.1);
        } else if (wheel > 0) {
            speedStick = Util.map(speedStick , 0, 1, 0.1, 1);
        }
        speedStick = Util.deadZone(speedStick, -0.15, 0.15, 0);
        
//        Joystick Wheel and SpeedStick end
        
        
//        Wheel and Stick start
//        try {
//            wheel = -cypress.getAnalogIn(8);
//            speedStick = cypress.getAnalogIn(1);
//        } catch (DriverStationEnhancedIO.EnhancedIOException e) {}
//        wheel = Util.map(wheel, -3.3, 0, -1, 1);
//        if (wheel > 0) {
//            wheel = Util.map(wheel, 0, 1, 0.3, 1);
//        } else if (wheel < 0) {
//            wheel = Util.map(wheel, -1, 0, -1, -0.3);
//        }
//        wheel = Util.deadZone(wheel, -0.32, 0.32, 0);
//        speedStick = Util.map(speedStick, 0, 3.3, -1, 1);
//        if (speedStick < 0) {
//            speedStick = Util.map(speedStick, -1, -0.18, -1, 0);
//        } else if (speedStick > 0) {
//            speedStick = Util.map(speedStick, 0.3, 1, 0, 1);
//        }
        
//        Wheel and Stick end
        
        
        
        
//        Gyro Start
        difference = 0 - wheel;
        speed = speedStick;
        goalDir = curDir + speed;
        rightDrive = speed + difference;
        leftDrive = speed - difference;
        
//        Gyro end
        
        
                
        
        
        System.out.println("diff..:" + difference + "   " + "speed:" + speed + "   drive:" + leftDrive + "   " + rightDrive);
        
        
//        drive.arcadeDrive(speedStick,  wheel);
       
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}