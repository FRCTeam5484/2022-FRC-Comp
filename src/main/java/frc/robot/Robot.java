package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    m_robotContainer.drive.ResetEncoders();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    m_robotContainer.drive.ResetEncoders();
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
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_robotContainer.drive.ResetEncoders();
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
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    m_robotContainer.lime.setToAutoTargetMode();
    m_robotContainer.drive.setDriveUnlocked();
    m_robotContainer.drive.stopDrive();
    m_robotContainer.intake.stopIntake();
    m_robotContainer.feed.stopFeed();
    m_robotContainer.shoot.stopShooter();
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() { }

  @Override
  public void testPeriodic() { }    
}