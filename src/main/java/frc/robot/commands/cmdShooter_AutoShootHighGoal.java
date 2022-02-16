package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subFeedSystem;
import frc.robot.subsystems.subShootSystem;

public class cmdShooter_AutoShootHighGoal extends CommandBase {
  subShootSystem shooter;
  subFeedSystem feed;
  Double seconds;
  Timer time;
  public cmdShooter_AutoShootHighGoal(subShootSystem _shooter, subFeedSystem _feed, double _seconds) {
    shooter = _shooter;
    feed = _feed;
    seconds = _seconds;
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
    shooter.setShooterToHighGoal();
    if(time.get() > 1){
      feed.runFeed();
    }
  }

  @Override
  public void end(boolean interrupted) {
    shooter.stopShooter();
    feed.stopFeed();
  }

  @Override
  public boolean isFinished() {
    return time.get() > seconds ? true : false;
  }
}
