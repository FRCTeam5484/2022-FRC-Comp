package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subFeedSystem;
import frc.robot.subsystems.subShootSystem;

public class cmdShooter_UnJam extends CommandBase {
  subFeedSystem feed;
  subShootSystem shoot;
  public cmdShooter_UnJam(subShootSystem _shoot, subFeedSystem _feed) {
    feed = _feed;
    shoot = _shoot;
    addRequirements(feed);
    addRequirements(shoot);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    shoot.setShooterReversed();
    feed.reverseFeed();
  }

  @Override
  public void end(boolean interrupted) {
    shoot.stopShooter();
    feed.stopFeed();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
