package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subFeedSystem;
import frc.robot.subsystems.subShootSystem;

public class cmdShooter_HighGoalSpeed extends CommandBase {
  subShootSystem shooter;
  subFeedSystem feed;
  public cmdShooter_HighGoalSpeed(subShootSystem _shooter, subFeedSystem _feed) {
    shooter = _shooter;
    feed = _feed;
    addRequirements(shooter, feed);
  }

  @Override
  public void initialize() {
    if(feed.BallInShooter())
    {
      feed.reverseFeed();
    }
  }

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
