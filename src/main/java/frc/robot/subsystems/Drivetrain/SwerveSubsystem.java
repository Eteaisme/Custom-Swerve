package frc.robot.subsystems.Drivetrain;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class SwerveSubsystem extends SubsystemBase {
    //Drive train dimensions 
    public static final double DRIVE_BASE_X = Units.inchesToMeters(24.750);
    public static final double DRIVE_BASE_Y = Units.inchesToMeters(24.750);
    //Create 4 Swerve modules 
    SwerveModule frontLeftModule = new SwerveModule(0,1);
    SwerveModule frontRightModule = new SwerveModule(2,3);
    SwerveModule backLeftModule = new SwerveModule(4,5);
    SwerveModule backRightModule = new SwerveModule(6,7);
    //Define a kinimatics object that (Takes chasis speed and returns swervemodule states)
    SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
        new Translation2d(DRIVE_BASE_X / 2.0, -DRIVE_BASE_Y / 2.0),
        new Translation2d(DRIVE_BASE_X / 2.0, DRIVE_BASE_Y / 2.0),
        new Translation2d(-DRIVE_BASE_X / 2.0, DRIVE_BASE_Y / 2.0),
        new Translation2d(-DRIVE_BASE_X / 2.0, -DRIVE_BASE_Y / 2.0) 
    ); 
    //Constructor
    CommandXboxController controller; 
    public SwerveSubsystem(CommandXboxController io) {
        System.out.println("SwerveSubsytem Constructor");
        controller = io; 
    }

    public void setChasisSpeeds(ChassisSpeeds desiredSpeed){
        SwerveModuleState[] newStates = kinematics.toSwerveModuleStates(desiredSpeed);
        
        frontLeftModule.setState(newStates[0]);
        frontRightModule.setState(newStates[1]);
        backLeftModule.setState(newStates[2]);
        backRightModule.setState(newStates[3]);
    }
    @Override
    public void periodic() {
        /*Read data from controller  */
        ChassisSpeeds newDesiredSpeeds = new ChassisSpeeds(
        //Pushing foward will ask the robot will ask the robot to move foward
        controller.getLeftY(),
        //Pushing left will ask the robot to move left
        controller.getLeftX(),
        //Pushing left will ask the robot to rotate left
        controller.getRightX()
        ); 

        setChasisSpeeds(newDesiredSpeeds);
        //Data I want to send to the smart dashboard
        //FL FR BL BR
        double loggingState[] = {
            frontLeftModule.getState().angle.getDegrees(), 
            frontLeftModule.getState().speedMetersPerSecond, 

            frontRightModule.getState().angle.getDegrees(), 
            frontRightModule.getState().speedMetersPerSecond, 

            backLeftModule.getState().angle.getDegrees(), 
            backLeftModule.getState().speedMetersPerSecond, 

            backRightModule.getState().angle.getDegrees(), 
            backRightModule.getState().speedMetersPerSecond, 
        }; 
        SmartDashboard.putNumberArray("Swervemodulestate", loggingState);
    }
}
