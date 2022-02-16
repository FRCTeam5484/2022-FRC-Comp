package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subFeedSystem;
import frc.robot.subsystems.subShootSystem;

public class cmdShooter_AutoShootLowGoal extends CommandBase {
  subShootSystem shooter;
  subFeedSystem feed;
  Timer time;
  public cmdShooter_AutoShootLowGoal(subShootSystem _shooter, subFeedSystem _feed) {
    shooter = _shooter;
    feed = _feed;
    addRequirements(shooter);
    addRequirements(feed);
  }

  @Override
  public void initialize() {
    time = new Timer();
    time.start();
  }

  @Override
  public void execute() {
    shooter.setShooterToLowGoal();
    if(time.get() > 1){
      feed.runFeed();
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
