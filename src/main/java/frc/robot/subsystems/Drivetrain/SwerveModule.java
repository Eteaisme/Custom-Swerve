package frc.robot.subsystems.Drivetrain;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
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
    private SwerveModuleState currentState; 

    SwerveModule(
        int driveMotorPort,
        int turningMotorPort        
    ) {
        System.out.println("SwerveModule Constructor");
        driveMotor = new Talon(driveMotorPort);
        turningMotor = new Talon(turningMotorPort);
        currentState = new SwerveModuleState(); 
   } 

   public SwerveModuleState getState() {
       return currentState;
  }

  public void setState(SwerveModuleState newState) {
      currentState = newState; 
  }
}
