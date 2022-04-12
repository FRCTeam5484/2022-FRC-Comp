package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subDriveSystem;
import frc.robot.subsystems.subLimeLightSystem;

public class cmdAuto_MoveToShootingPosition extends CommandBase {
  subDriveSystem drive;
  subLimeLightSystem lime;
  
  public cmdAuto_MoveToShootingPosition(subDriveSystem _drive, subLimeLightSystem _lime) {
    drive = _drive;
    lime = _lime;
    addRequirements(drive, lime);
  }

  @Override
  public void initialize() {
    lime.setToAutoTargetMode();
  }

  @Override
  public void execute() {
    if(lime.hasTarget()){
      drive.autoDrive(lime.DriveCommand, lime.SteerCommand);
    }
  }

  @Override
  public void end(boolean interrupted) {
    drive.stopDrive();
    lime.setToDriverMode();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
