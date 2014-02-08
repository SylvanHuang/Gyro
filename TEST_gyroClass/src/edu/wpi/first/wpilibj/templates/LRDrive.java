/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;


public class LRDrive {
   double gyroError = 0;
   double goalDir = 0;
   double joyStickError = 0;
   double leftDrive = 0;
   double rightDrive = 0;
   
   public class Pair {
       double left;
       double right;
       
       Pair(double left_in, double right_in)
       {
           left = left_in;
           right = right_in;
       }        
   }
   
   
   
    public Pair straightDrive(double leftYaxis, double rightYaxis, double curDir, double divider) {
        joyStickError = (leftYaxis - rightYaxis) / divider;
        goalDir = goalDir + joyStickError;
        gyroError = (goalDir - curDir) / 90;
        gyroError = Util.deadZone(gyroError, -0.05, 0.05, 0);
        leftYaxis = Util.deadZone(leftYaxis, -0.1, 0.1, 0);
        rightYaxis = Util.deadZone(rightYaxis, -0.1, 0.1, 0);
        leftDrive = leftYaxis - gyroError;
        rightDrive = rightYaxis + gyroError;
        if (rightDrive > 0) {
            rightDrive = Util.map(rightDrive, 0, 1, 0.4, 1);
        } else if (rightDrive < 0) {
            rightDrive = Util.map(rightDrive, -1, 0, -1, -0.4);
       }
        if (leftDrive > 0) {
            leftDrive = Util.map(leftDrive, 0, 1, 0.4, 1);
        } else if (leftDrive < 0) {
            leftDrive = Util.map(leftDrive, -1, 0, -0.4, -1);
        }
        return new Pair(leftDrive, rightDrive);
   }
    
  
   
    
    
}
