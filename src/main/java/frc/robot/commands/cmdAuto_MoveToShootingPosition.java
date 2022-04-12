package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subDriveSystem;
import frc.robot.subsystems.subLimeLightSystem;

public class cmdAuto_MoveToShootingPosition extends CommandBase {
  subDriveSystem drive;
  subLimeLightSystem lime;
  double KpAim = -0.1;
  double KpDistance = -0.1;
  double min_aim_command = 0.05;
  double left_command = 0.0;
  double right_command = 0.0;
  
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
    double tx = lime.getTX();
    double ty = lime.getTY();

    double heading_error = -tx;
    double distance_error = -ty;
    double steering_adjust = 0.0;

    if (tx > 1.0)
    {
      steering_adjust = KpAim*heading_error - min_aim_command;
    }
    else if (tx < -1.0)
    {
      steering_adjust = KpAim*heading_error + min_aim_command;
    }

    double distance_adjust = KpDistance * distance_error;

    left_command += steering_adjust + distance_adjust;
    right_command -= steering_adjust + distance_adjust;

    drive.autoTankDrive(left_command, right_command);
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
