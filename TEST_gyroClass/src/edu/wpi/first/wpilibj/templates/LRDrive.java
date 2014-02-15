/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

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
   
   
   
    public Pair straightDrive(double leftYaxis, double rightYaxis, double curDir, double divider, boolean resetButton) {
        joyStickError = (leftYaxis - rightYaxis) / divider;
        joyStickError = Util.deadZone(joyStickError, -0.1, 0.1, 0);
        goalDir = goalDir + joyStickError;
        gyroError = (goalDir - curDir) / 90;
        gyroError = Util.deadZone(gyroError, -0.05, 0.05, 0);
        leftYaxis = Util.deadZone(leftYaxis, -0.1, 0.1, 0);
        rightYaxis = Util.deadZone(rightYaxis, -0.1, 0.1, 0);
        leftDrive = leftYaxis - gyroError;
        rightDrive = rightYaxis + gyroError;
//        if (rightDrive > 0) {
//            rightDrive = Util.map(rightDrive, 0, 1, 0.4, 1);
//        } else if (rightDrive < 0) {
//            rightDrive = Util.map(rightDrive, -1, 0, -1, -0.4);
//       }
//        if (leftDrive > 0) {
//            leftDrive = Util.map(leftDrive, 0, 1, 0.4, 1);
//        } else if (leftDrive < 0) {
//            leftDrive = Util.map(leftDrive, -1, 0, -0.4, -1);
//        }
        if (resetButton) {
            rightDrive = 0;
            leftDrive = 0;
            goalDir = curDir;
        }
        System.out.println(joyStickError + "   " + curDir + "   "  + goalDir + "   " + leftDrive + "   " + rightDrive);
        return new Pair(leftDrive, rightDrive);
   }
    
  
   
    
    
}









































