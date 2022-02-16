package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subDriveSystem;

public class cmdDrive_DriveStraightByEncoder extends CommandBase {
  subDriveSystem drive;
  double speedValue;
  double targetValue;
  public cmdDrive_DriveStraightByEncoder(subDriveSystem _drive, double _targetValue, double _speedValue) {
    drive = _drive;
    targetValue = _targetValue;
    speedValue = _speedValue;
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    drive.ResetEncoders();
  }

  @Override
  public void execute() {
    drive.autoDriveByPower(speedValue);
  }

  @Override
  public void end(boolean interrupted) {
    drive.stopDrive();
  }

  @Override
  public boolean isFinished() {
    return Math.abs(drive.getAverageEncoderDistance()) >= targetValue ? true : false;
  }
}
