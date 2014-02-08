
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
        Random random = new Random();
//        double hoodLoc = (double)hoodAnalog.getValue() / 600;        
        
        
 
        
        
        
        
    public void robotInit() {
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        c1.start();   
        s1.set(false);
        s2.set(true);
    }
    
    
    public void autonomousPeriodic() {
//        System.out.println("                " + limitSwitch1.get());
//        try {
//            Thread.sleep(7000L);
//        }
//        catch (Exception e) {}
        double randomNumber = random.nextDouble();
        double hoodLoc = (double)hoodAnalog.getValue() / 600;
        i = i + 1;
        
        
        s1.set(true);
        s2.set(false);
        
 
        
        while (limitSwitch1.get() == false) {
            conveyor.set(1);
            triRollers.set(-1);
//            if (i > 50) {
//                if (hoodLoc > randomNumber) {
//                    hood.set(-1);
//                } else if (hoodLoc < randomNumber - 0.5) {
//                    hood.set(1);
//                } else {
//                    hood.set(0);
//                }
//                i = 0;
//            }
        }
        
//        if (limitSwitch1.get() == true) {
//            if (randomNumber > 0.92 && randomNumber < 0.3) {
//                randomNumber = 0.3;
//            } else if (randomNumber > 0.58 && randomNumber < 0.93) {
//                randomNumber = 0.55;
//            }
//            if (hoodLoc > randomNumber) {
//                hood.set(1);
//            } else if (hoodLoc < randomNumber - 0.05) {
//                hood.set(-1);
//            } else {
//                hood.set(0);
//            }
//        } else {
//            hood.set(0);
//        }  
        
        if (limitSwitch1.get() == true) {
            
//            if (randomNumber > 0.92 && randomNumber < 0.3) {
//                randomNumber = 0.3;
//            } else if (randomNumber > 0.58 && randomNumber < 0.93) {
//                randomNumber = 0.55;
//            }
//            if (hoodLoc > randomNumber) {
//                hood.set(1);
//            } else if (hoodLoc < randomNumber - 0.05) {
//                hood.set(-1);
//            } else {
//                hood.set(0);
//            }         
            conveyor.set(0);
            triRollers.set(0);
            conveyor.set(1);
            try {
                Thread.sleep(500L);
            }
            catch (Exception e) {}
            shooter.set(0);
            conveyor.set(0);
            triRollers.set(0);
        }
        
//        if (hoodLoc > random.nextDouble()) {
//            hood.set(1);
//        } else if (hoodLoc < random.nextDouble() - 0.5) {
//            hood.set (-1);
//        } else {
//            hood.set(0);
//        }
        
       
    }

    
    public void teleopPeriodic() {
       drive.arcadeDrive(drivestick.getX(), drivestick.getY());     
       throttleVal = drivestick.getThrottle();
//       double hoodLoc = (double)hoodAnalog.getValue() / 600;
       double hoodLoc = (double)hoodAnalog.getValue() / 600;
       double randomNumber = random.nextDouble();
       i = i + 1;
       
       System.out.println("          " + hoodLoc);
       
//       if (hoodLoc > 0.5) {
//           hood.set(1);
//       } else if (hoodLoc < 0.5) {
//           hood.set(-1);
//       } else {
//           hood.set(0);
//       }
       
       
    if (limitSwitch1.get() == true) {
        double shooterSpeed = shooter.get();
        
        
        if (shooterSpeed == -1) {
            try {
                Thread.sleep(500L);
            }
            catch (Exception e) {}            
            conveyor.set(1);
        } else if (shooterSpeed == 0) {
            if (conveyor.get() > 0) {
                conveyor.set(0);
            } else {
                if (drivestick.getRawButton(5) == true){
                    conveyor.set(-1);
                    triRollers.set(1);
                } else if (drivestick.getRawButton(2) == false && drivestick.getRawButton(5) == false) {
                    conveyor.set(0);
                    triRollers.set(0);
                } 
            }
        }         
        
    } else {
        if (drivestick.getRawButton(2) == true) {
            conveyor.set(1);
            triRollers.set(-1);
        } else if (drivestick.getRawButton(5) == true){
            conveyor.set(-1);
            triRollers.set(1);
        } else if (drivestick.getRawButton(2) == false && drivestick.getRawButton(5) == false) {
            conveyor.set(0);
            triRollers.set(0);
        }  
    }


       
                  
           if (drivestick.getRawButton(1) == true) {               
                if (throttleVal > 0) {
                  throttleVal = 0;
                } 
              shooter.set(throttleVal);
           } else if (drivestick.getRawButton(1) == false) {
              shooter.set(0);
           }
           
                 
//       if (drivestick.getRawButton(2) == true) {
//           conveyor.set(1);
//           triRollers.set(-1);
//       } else if (drivestick.getRawButton(5) == true){
//           conveyor.set(-1);
//           triRollers.set(1);
//       } else if (drivestick.getRawButton(2) == false && drivestick.getRawButton(5) == false) {
//           conveyor.set(0);
//           triRollers.set(0);
//       }
       
       if (drivestick.getRawAxis(6) == 1) {
           hood.set(1);
       } else if (drivestick.getRawAxis(6) == -1) {
           hood.set(-1);
       } else if (drivestick.getRawAxis(6) == 0) {
           hood.set(0);
       }
       
       
       if (drivestick.getRawButton(6) == true) {
           s1.set(true);
           s2.set(false);
       } else if (drivestick.getRawButton(7) == true) {
           s1.set(false);
           s2.set(true);
       }
    }
    
   
    public void testPeriodic() {
        double hoodLoc = (double)hoodAnalog.getValue() / 600;
        double randomNumber = random.nextDouble();
        i = i + 1;
        
        if (limitSwitch1.get() == true) {
            
            if (randomNumber > 0.92 && randomNumber < 0.3) {
                randomNumber = 0.3;
            } else if (randomNumber > 0.58 && randomNumber < 0.93) {
                randomNumber = 0.55;
            }
            if (hoodLoc > randomNumber) {
                hood.set(1);
            } else if (hoodLoc < randomNumber - 0.05) {
                hood.set(-1);
            } else {
                hood.set(0);
            }            
            conveyor.set(0);
            triRollers.set(0);
            conveyor.set(1);
            try {
                Thread.sleep(500L);
            }
            catch (Exception e) {}
            shooter.set(0);
            conveyor.set(0);
            triRollers.set(0);
        }
        
//        if (i > 500) {
//            if (randomNumber < 0.25 && randomNumber > 0.92) {
//                randomNumber = 0.3;
//            } else if (randomNumber > 0.58 && randomNumber < 0.92) {
//                randomNumber = 0.5;
//            }
//            if (hoodLoc > randomNumber) {
//                hood.set(1);
//            } else if (hoodLoc < randomNumber) {
//                hood.set(-1);               
//            } else {
//                hood.set(0);
//            }
//            if (i > 520) {
//                i = 0;
//            }
//            
//        } else {
//            System.out.println("    " + hoodLoc);
//            hood.set(0);
//        }
//        if (hoodLoc > 0.5) {
//            hood.set(1);
//        } else if (hoodLoc < 0.3) {
//            hood.set(-1);
//        } else {
//            hood.set(0);
//        }
        
    
    }
    
}
