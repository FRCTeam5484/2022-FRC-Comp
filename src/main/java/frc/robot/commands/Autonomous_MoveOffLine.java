package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subDriveSystem;

public class Autonomous_MoveOffLine extends CommandBase {
  subDriveSystem drive;
  Timer time;
  public Autonomous_MoveOffLine(subDriveSystem _drive) {
    drive = _drive;
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    time = new Timer();
    time.start();
  }

  @Override
  public void execute() {
    drive.autoDriveByPower(-0.4);
  }

  @Override
  public void end(boolean interrupted) {
    drive.stopDrive();
  }

  @Override
  public boolean isFinished() {
    return time.get() > 2 ? true : false;
  }
}
