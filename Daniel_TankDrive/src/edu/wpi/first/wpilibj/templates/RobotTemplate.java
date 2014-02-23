package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import java.util.Random;

public class RobotTemplate extends IterativeRobot {
    Joystick rightStick = new Joystick(2);
    Joystick leftStick = new Joystick(1);
    Victor conveyor = new Victor(3);
    Victor triRollers = new Victor(1);
    Victor hood = new Victor(2);
    Victor shooter = new Victor(6);
    RobotDrive drive = new RobotDrive(5, 4, 7, 8);
    double throttleVal = 0;
    double shooterSpeed = -0.5;
    public Solenoid s1 = new Solenoid(7), s2 = new Solenoid(8); 
    public Compressor c1 = new Compressor( 5, 1);
    AnalogChannel hoodAnalog = new AnalogChannel(4);
    DigitalInput limitSwitch1 = new DigitalInput(7);
    DigitalInput limitSwitch2 = new DigitalInput(8);
    int i = 0;
    Random random = new Random();
    Gyro gyro = new Gyro(1);
    double curDir = gyro.getAngle();
    double leftYaxis = leftStick.getY() * -1;
    double rightYaxis = rightStick.getY() * -1;
    double goalDir = 10;
    

    
//    double leftXaxis = leftStick.getDirectionRadians();
//    double rightXaxis = rightStick.getDirectionRadians();
    

    public void robotInit() {
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        drive.setMaxOutput(0.85);
        c1.start();
        s1.set(false);
        s2.set(true);

    }

    public void autonomousPeriodic() {

    }

    public void teleopPeriodic() {
        drive.tankDrive(leftStick, rightStick);

        if (shooterSpeed > -0.5) {
            shooterSpeed = -0.5;
        } else if (shooterSpeed < -1) {
            shooterSpeed = -1;
        }
        
        if (rightStick.getRawButton(2)) {
            s1.set(true);
            s2.set(false);
        } else if (rightStick.getRawButton(5)) {
            s1.set(false);
            s2.set(true);
        }
        
        if (leftStick.getRawAxis(6) == 1) {
            hood.set(1);
        } else if (leftStick.getRawAxis(6) == -1) {
            hood.set(-1);
        } else {
            hood.set(0);
        }
         
        if (rightStick.getRawAxis(6) == 1) {
            shooterSpeed = shooterSpeed + 0.1;
        } else if (rightStick.getRawAxis(6) == -1) {
            shooterSpeed = shooterSpeed - 0.1;
        } 
        
        if (limitSwitch1.get() == true) {
            
            if (shooter.get() < 0) {
                if (i > 110) {
                    i = 0;
                } else {
                    i = i+1;
                }
                while (i > 100) {
                    conveyor.set(1);
                    i = 0;
                }
            } else { 
                if (leftStick.getRawButton(5)) {
                    conveyor.set(-1);
                    triRollers.set(1);
                } else {
                    conveyor.set(0);
                    triRollers.set(0);
                }
            }

            if (rightStick.getTrigger() == true) {
                shooter.set(shooterSpeed);
            } else {
                shooter.set(0);
            }
        } else {
            if (leftStick.getRawButton(5)) {
                conveyor.set(-1);
                triRollers.set(1);
            } else if (leftStick.getRawButton(2)) {
                conveyor.set(1);
                triRollers.set(-1);
            } else {
                conveyor.set(0);
                triRollers.set(0);
            }

            if (rightStick.getTrigger() == true) {
                shooter.set(shooterSpeed);
            } else {
                shooter.set(0);
            }
        }
        
        
        
        
        
    }
    
    public void testPeriodic() {
    
    }
    
}
