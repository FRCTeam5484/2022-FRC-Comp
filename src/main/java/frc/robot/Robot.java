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
  Thread m_visionThread;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    /*m_visionThread =
        new Thread(
            () -> {
              UsbCamera camera = CameraServer.startAutomaticCapture();
              camera.setResolution(380, 240);
              CvSink cvSink = CameraServer.getVideo();
              CvSource outputStream = CameraServer.putVideo("Rectangle", 380, 240);
              Mat mat = new Mat();
              while (!Thread.interrupted()) {
                if (cvSink.grabFrame(mat) == 0) {
                  outputStream.notifyError(cvSink.getError());
                  continue;
                }
                outputStream.putFrame(mat);
              }
            });
    m_visionThread.setDaemon(true);
    m_visionThread.start();
    
    try {
      m_robotContainer.vision.startCameraStreams();
    } catch (Exception e) {
      System.out.println("Unable to start cameras");
    }*/
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    /*
    SmartDashboard.putData("Feeder Current Command", m_robotContainer.feed);

    SmartDashboard.putNumber("Climb Encoder", m_robotContainer.climb.getPosition());    
    SmartDashboard.putData("Climb Command", m_robotContainer.climb);

    SmartDashboard.putNumber("Drive Left Position", m_robotContainer.drive.getLeftPosition());
    SmartDashboard.putNumber("Drive Right Position", m_robotContainer.drive.getRightPosition());
    SmartDashboard.putNumber("Drive Avg Encoder", m_robotContainer.drive.getAverageEncoderDistance());
    SmartDashboard.putNumber("Drive Distance Inches", m_robotContainer.drive.getAverageEncoderDistance() / DriveSystem.EncoderTickToInch);
    SmartDashboard.putNumber("Gyro Heading", m_robotContainer.drive.getGyroHeading());
    SmartDashboard.putData("Drive Command", m_robotContainer.drive);

    SmartDashboard.putData("Intake Command", m_robotContainer.intake);
    
    SmartDashboard.putNumber("Shooter Velocity", m_robotContainer.shoot.getVelocity());
    SmartDashboard.putData("Shooter Command", m_robotContainer.shoot);
    */
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
  }

  @Override
  public void autonomousInit() {
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
    m_robotContainer.drive.setDriveUnlocked();
    m_robotContainer.drive.stopDrive();
    m_robotContainer.intake.stopIntake();
    m_robotContainer.feed.stopFeed();
    m_robotContainer.shoot.stopShooter();
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() { }

  @Override
  public void testPeriodic() { }    
}