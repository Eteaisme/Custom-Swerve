package frc.robot.subsystems.Drivetrain;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

/*This module should contain:
 * 1. Drive Motor
 * 2. Turning Motor
 * 3. Absolute Encoder 
 */
public class SwerveModule {
    //Define all the varibles
    private Talon driveMotor; 
    private Talon turningMotor; 

    //State Varibles
    private SwerveModuleState currentState; 
    private SwerveModuleState desiredState;

    //Two PID controllers (1 for drive 1 for steering)
    PIDController drivePIDController; 
    PIDController turningPIDController;

    //Constructor 
    SwerveModule(
        int driveMotorPort,
        int turningMotorPort        
    ) {
        System.out.println("SwerveModule Constructor");


        driveMotor = new Talon(driveMotorPort);
        turningMotor = new Talon(turningMotorPort);

        drivePIDController = new PIDController(0.1, 0, 0 ); 
        turningPIDController = new PIDController(0.1, 0, 0); 

        currentState = new SwerveModuleState();
        
   } 

   public SwerveModuleState getState() {
       return currentState;
  }

  public void setDesiredState(SwerveModuleState newState) {
    desiredState = newState; 
  }
}
