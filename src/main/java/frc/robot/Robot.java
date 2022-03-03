package frc.robot;

import org.opencv.core.Mat;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.TimedRobot;
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
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
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
    m_robotContainer.led.rainbow();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    m_robotContainer.led.green();
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
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
  public void teleopPeriodic() {
    m_robotContainer.led.team5484();
  }

  @Override
  public void testInit() { }

  @Override
  public void testPeriodic() { }    
}