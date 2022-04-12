package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subIntakeSystem;

public class cmdIntake_RunSeconds extends CommandBase {
  subIntakeSystem intake;
  Timer time;
  public cmdIntake_RunSeconds(subIntakeSystem _intake) {
    intake = _intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    time = new Timer();
    time.start();
  }

  @Override
  public void execute() {
    intake.runIntake();
  }

  @Override
  public void end(boolean interrupted) {
    intake.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return time.get() > 0.5 ? true : false;
  }
}
