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
        double left;
        double right;
        
        Pair(double left_in, double right_in) {
            left = left_in;
            right = right_in;
        }
    }
    
    public Pair straightDrive(double wheel, double speedStick, double difference, double leftDrive, double rightDrive, double curDir){
        if (wheel < 0) {
            wheel = Util.map(wheel, -1, 0, -1, -0.1);
        } else if (wheel > 0) {
            wheel = Util.map(wheel, 0, 1, 0.1, 1);
        } 
        wheel = Util.deadZone(wheel, -0.15, 0.15, 0);
        
        if (speedStick > 0) {
            speedStick = Util.map(speedStick, 0, 1, 0.1, 1);
        } else if (speedStick < 0) {
            speedStick = Util.map(speedStick, -1, 0, -1, -0.1);
        } 
        speedStick = Util.deadZone(speedStick, -0.15, 0.15, 0);
        
//        Gyro start
        
        difference = 0 - wheel;
        double speed = speedStick;
        double goalDir = curDir + speed;
        rightDrive = speed + difference;
        leftDrive = speed - difference;
                
//        Gyro end
        return new Pair(leftDrive, rightDrive);
    }
}    
