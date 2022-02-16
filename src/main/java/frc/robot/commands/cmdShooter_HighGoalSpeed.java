package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subShootSystem;

public class cmdShooter_HighGoalSpeed extends CommandBase {
  subShootSystem shooter;
  public cmdShooter_HighGoalSpeed(subShootSystem _shooter) {
    shooter = _shooter;
    addRequirements(shooter);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    shooter.setShooterToHighGoal();
  }

  @Override
  public void end(boolean interrupted) {
    shooter.stopShooter();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
