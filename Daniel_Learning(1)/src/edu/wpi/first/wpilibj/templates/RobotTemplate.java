
package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import java.util.Random;


public class RobotTemplate extends IterativeRobot {
    Joystick drivestick = new Joystick(2);
    Victor conveyor = new Victor(3);
    Victor triRollers = new Victor(1);
    Victor hood = new Victor(2);
    Victor shooter = new Victor(6);
    RobotDrive drive = new RobotDrive(5, 4, 7, 8);
    double throttleVal = 0;
    public Solenoid s1 = new Solenoid(7), s2 = new Solenoid(8); 
    public Compressor c1 = new Compressor( 5, 1);
    AnalogChannel hoodAnalog = new AnalogChannel(4);
    DigitalInput limitSwitch1 = new DigitalInput(7);
    DigitalInput limitSwitch2 = new DigitalInput(8);
    int i = 0;
    int n = 0;
    int m = 0;
    int x = 0;
    double hoodPlacer = 0;
//    double hoodLoc = (double)hoodAnalog.getValue() / 600;
    Random random = new Random();
    
    public void robotInit() {
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        c1.start();
        s1.set(false);
        s2.set(true);
        
    }
    public void autonomousPeriodic() {
        System.out.println("   " + i);
        i = i + 1;
        x = x + 1;
        
        double randomNumber = random.nextDouble();
        double hoodLoc = (double)hoodAnalog.getValue() / 600;
        s1.set(true);
        s2.set(false);

        if (x > 50) {
            if (randomNumber > 0 && randomNumber < 0.34) {
                hoodPlacer = 0.3;
            } else if (randomNumber > 0.33 && randomNumber < 0.67) {
                hoodPlacer = 0.4;
            } else if (hoodPlacer > 0.66 && hoodPlacer < 1) {
                hoodPlacer = 0.5;
            } else {
                hoodPlacer = 0.4;
            }
        } else {
            hoodPlacer = 0.4;
        }
        
        System.out.println("   " + hoodLoc + "   " + randomNumber + "   " + hoodPlacer);
        
        if (limitSwitch1.get() == false) {
            n = n + 1;
            conveyor.set(1);
            triRollers.set(-1);
            if (n > 5) {
                shooter.set(0);
                n = 0;
            }
            if (i > 100) {
                if (hoodLoc > hoodPlacer) {
                    hood.set(1);
                } else if (hoodLoc < hoodPlacer - 0.07) {
                    hood.set(-1);
                } else {
                    hood.set(0);
                }
                if (i > 110) {
                    i = 0;
                }
                
            } else { 
                hood.set(0);
            }
        } else if (limitSwitch1.get() == true) {
             m = m + 1;
             conveyor.set(0);
             triRollers.set(0);
             shooter.set(-1);
             hood.set(0);
             
             if (m > 5) {
                 conveyor.set(1);
                 m = 0;
             } 
            
        } 
    }

    public void teleopPeriodic() {
//        drive.arcadeDrive(drivestick.getX(), drivestick.getY());
       double hoodLoc = (double)hoodAnalog.getValue() / 600;
        
        
       if (drivestick.getRawAxis(6) == 1) {
           hood.set(1);
       } else if (drivestick.getRawAxis(6) == -1) {
           hood.set(-1);
       } else if (drivestick.getRawAxis(6) == 0) {
           hood.set(0);
       }
       
       System.out.println(hoodLoc);
       
       
       
    }
    
    public void testPeriodic() {
    
    }


    
}
