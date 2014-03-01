/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


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
    Joystick speedStick = new Joystick(1);
    Joystick Wheel = new Joystick(2);
    RobotDrive drive = new RobotDrive(8, 4, 1, 3);
    Gyro gyro = new Gyro(1);
    double leftDrive = 0;
    double rightDrive = 0;
    double speed = 0;
    double wheelAngle = 0;
    Wheel_Class wheelClass = new Wheel_Class();
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        gyro.reset();

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
        wheelAngle = Wheel.getX();
        speed = speedStick.getY();
        Wheel_Class.Pair pair = wheelClass.straightDrive(wheelAngle, speed, leftDrive, rightDrive, gyro.getAngle(), gyro.getAngle());
        
        System.out.println(pair.one+ "  "+ pair.two);
        drive.tankDrive(pair.one, pair.two);
//        drive.tankDrive(speedStick.getY(), Wheel.getY());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
