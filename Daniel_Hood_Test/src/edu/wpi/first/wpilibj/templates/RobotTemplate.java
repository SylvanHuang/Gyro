/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
    Joystick stick = new Joystick(2);
    AnalogChannel hoodAnalog = new AnalogChannel(4);
    Victor hood = new Victor(2);
    
    
    public void robotInit() {

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
        
        if (stick.getRawAxis(6) == 1) {
            hood.set(1);
        } else if (stick.getRawAxis(6) == -1) {
            hood.set(-1);
        } else {
            hood.set(0);
        }
        
        
        double hoodLoc = hoodAnalog.getValue();
        double throtLoc = (stick.getThrottle());
        System.out.println("                  " + hoodLoc + "    " + hoodAnalog.getValue());
        
        
        
//        if (hoodLoc > throtLoc) {
//            hood.set(1);
//        } else if (hoodLoc > throtLoc) {
//            hood.set(-1);
//        } else {
//            hood.set(0);
//        }
        
       
        
        
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
