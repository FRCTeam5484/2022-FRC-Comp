package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.DriveSystem;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    //SmartDashboard.setDefaultBoolean("Enable Compressor", true);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    
    SmartDashboard.putData("Current Command", CommandScheduler.getInstance());

    SmartDashboard.putNumber("Ball Feeder Velocity", m_robotContainer.feed.getVelocity());
    SmartDashboard.putData("Ball Feeder Current Command", m_robotContainer.feed);

    SmartDashboard.putNumber("Climb Encoder", m_robotContainer.climb.getPosition());    
    //SmartDashboard.putBoolean("Climb Air", m_robotContainer.air.getClimbSolenoidStatus());
    SmartDashboard.putData("Climb Command", m_robotContainer.climb);

    SmartDashboard.putNumber("Drive Left Position", m_robotContainer.drive.getLeftPosition());
    SmartDashboard.putNumber("Drive Right Position", m_robotContainer.drive.getRightPosition());
    SmartDashboard.putNumber("Drive Avg Encoder", m_robotContainer.drive.getAverageEncoderDistance());
    SmartDashboard.putNumber("Drive Distance Inches", m_robotContainer.drive.getAverageEncoderDistance() / DriveSystem.EncoderTickToInch);
    SmartDashboard.putNumber("Gyro Heading", m_robotContainer.drive.getGyroHeading());
    SmartDashboard.putData("Drive Command", m_robotContainer.drive);

    SmartDashboard.putNumber("Intake Velocity", m_robotContainer.intake.getVelocity());
    //SmartDashboard.putBoolean("Intake Air", m_robotContainer.air.getIntakeSolenoidStatus());
    SmartDashboard.putData("Intake Command", m_robotContainer.intake);
    
    SmartDashboard.putNumber("Shooter Velocity", m_robotContainer.shoot.getVelocity());
    SmartDashboard.putNumber("Shooter Power", m_robotContainer.shoot.getPowerValue());
    SmartDashboard.putData("Shooter Command", m_robotContainer.shoot);

    SmartDashboard.putString("LimeLight Video", "http://10.54.84.150:5800");
    SmartDashboard.putString("LimeLight Config", "http://10.54.84.150:5801");
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_robotContainer.lime.setToAutoTargetMode();
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    m_robotContainer.lime.setToDriverMode();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    m_robotContainer.drive.setDriveUnlocked();
    m_robotContainer.drive.stopDrive();
    m_robotContainer.intake.stopIntake();
    m_robotContainer.feed.stopFeed();
    m_robotContainer.shoot.stopShooter();
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
    ShuffleboardTab testingTab = Shuffleboard.getTab("Testing");
    Shuffleboard.selectTab("Testing");
    XboxController testController = m_robotContainer.driverOne;
    if(testController.getXButton()){
      m_robotContainer.drive.leftDriveMaster.set(-testController.getLeftY());
      testingTab.add("Motor", "Left Drive Master (1)");
      testingTab.add("Power", m_robotContainer.drive.leftDriveMaster.get());
      testingTab.add("Velocity", m_robotContainer.drive.leftDriveMaster.getEncoder().getVelocity());
      testingTab.add("Position", m_robotContainer.drive.leftDriveMaster.getEncoder().getPosition());
    }
    else if(testController.getAButton()){
      m_robotContainer.drive.leftDriveSlave.set(-testController.getLeftY());
      testingTab.add("Motor", "Left Drive Slave (2)");
      testingTab.add("Power", m_robotContainer.drive.leftDriveSlave.get());
      testingTab.add("Velocity", m_robotContainer.drive.leftDriveSlave.getEncoder().getVelocity());
      testingTab.add("Position", m_robotContainer.drive.leftDriveSlave.getEncoder().getPosition());
    }
    else if(testController.getYButton()){
      m_robotContainer.drive.rightDriveMaster.set(-testController.getLeftY());
      testingTab.add("Motor", "Right Drive Master (3)");
      testingTab.add("Power", m_robotContainer.drive.rightDriveMaster.get());
      testingTab.add("Velocity", m_robotContainer.drive.rightDriveMaster.getEncoder().getVelocity());
      testingTab.add("Position", m_robotContainer.drive.rightDriveMaster.getEncoder().getPosition());
    }
    else if(testController.getBButton()){
      m_robotContainer.drive.rightDriveSlave.set(-testController.getLeftY());
      testingTab.add("Motor", "Right Drive Slave (4)");
      testingTab.add("Power", m_robotContainer.drive.rightDriveSlave.get());
      testingTab.add("Velocity", m_robotContainer.drive.rightDriveSlave.getEncoder().getVelocity());
      testingTab.add("Position", m_robotContainer.drive.rightDriveSlave.getEncoder().getPosition());
    }
    else if(testController.getStartButton()){
      m_robotContainer.feed.feedMotor.set(-testController.getLeftY());
      testingTab.add("Motor", "Feed Motor (5)");
      testingTab.add("Power", m_robotContainer.feed.feedMotor.get());
      testingTab.add("Velocity", m_robotContainer.feed.feedMotor.getEncoder().getVelocity());
      testingTab.add("Position", m_robotContainer.feed.feedMotor.getEncoder().getPosition());
    }
    else if(testController.getBackButton()){
      m_robotContainer.climb.climbMotor.set(-testController.getLeftY());
      testingTab.add("Motor", "Climb Motor (6)");
      testingTab.add("Power", m_robotContainer.climb.climbMotor.get());
      testingTab.add("Velocity", m_robotContainer.climb.climbMotor.getEncoder().getVelocity());
      testingTab.add("Position", m_robotContainer.climb.climbMotor.getEncoder().getPosition());
    }
    else if(testController.getLeftBumper()){
      m_robotContainer.intake.intakeMotor.set(-testController.getLeftY());
      testingTab.add("Motor", "Intake Motor (7)");
      testingTab.add("Power", m_robotContainer.intake.intakeMotor.get());
      testingTab.add("Velocity", m_robotContainer.intake.intakeMotor.getEncoder().getVelocity());
      testingTab.add("Position", m_robotContainer.intake.intakeMotor.getEncoder().getPosition());
    }
    else if(testController.getRightBumper()){
      m_robotContainer.shoot.shooterMotor.set(-testController.getLeftY());
      testingTab.add("Motor", "Shooter Motor (8)");
      testingTab.add("Power", m_robotContainer.shoot.shooterMotor.get());
      testingTab.add("Velocity", m_robotContainer.shoot.shooterMotor.getEncoder().getVelocity());
      testingTab.add("Position", m_robotContainer.shoot.shooterMotor.getEncoder().getPosition());
    }
    else if(testController.getLeftStickButtonPressed()){
      m_robotContainer.air.toggleIntake();
      testingTab.add("Pneumatic", "Intake");
      testingTab.add("State", m_robotContainer.air.getIntakeSolenoidStatus());
    }
    else if(testController.getRightStickButtonPressed()){
      m_robotContainer.air.toggleClimb();
      testingTab.add("Pneumatic", "Climb");
      testingTab.add("State", m_robotContainer.air.getClimbSolenoidStatus());
    }
    else{
      m_robotContainer.drive.leftDriveMaster.stopMotor();
      m_robotContainer.drive.leftDriveSlave.stopMotor();
      m_robotContainer.drive.rightDriveMaster.stopMotor();
      m_robotContainer.drive.rightDriveSlave.stopMotor();
      m_robotContainer.feed.feedMotor.stopMotor();
      m_robotContainer.climb.climbMotor.stopMotor();
      m_robotContainer.intake.intakeMotor.stopMotor();
      m_robotContainer.shoot.shooterMotor.stopMotor();
      testingTab.add("Motor", "No Motor Selected");
      testingTab.add("Power", 0);
      testingTab.add("Velocity", 0);
      testingTab.add("Position", 0);
    }    
  }
}
