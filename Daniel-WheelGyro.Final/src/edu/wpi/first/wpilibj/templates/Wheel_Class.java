/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Luniel
 */
public class Wheel_Class {
    
    public class Pair {
        double one;
        double two;
        
        Pair(double left_in, double right_in) {
            one = left_in;
            two = right_in;
        }
    }
    
    /**
     *
     * @param X
     */
    public static double goalDir(double wheel, double goalDir) {
        goalDir = goalDir + wheel;
               return goalDir;
    }
    
    public Pair straightDrive(double wheel, double speedStick, double curDir){
        
        curDir = Util.deadZone(curDir, -0.6, 0.6, 0);
        wheel = Util.deadZone(wheel, -0.1, 0.1, 0);        
        goalDir(wheel, curDir);
        speedStick = Util.deadZone(speedStick, -0.1, 0.1, 0);
         
//        Gyro start
        
        double speed = speedStick;   
//        double gyroDif = goalDir - curDir;
//        gyroDif = Util.deadZone(gyroDif, -0.1, 0.1, 0);
        System.out.println("   " + curDir + "   ");
        double rightDrive = speed + (wheel);
        double leftDrive = speed - (wheel);
                
//        Gyro end
        
        return new Pair(leftDrive, rightDrive);
    }
}    
