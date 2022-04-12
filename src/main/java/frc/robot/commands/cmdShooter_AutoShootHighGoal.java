package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subDriveSystem;
import frc.robot.subsystems.subFeedSystem;
import frc.robot.subsystems.subLimeLightSystem;
import frc.robot.subsystems.subShootSystem;

public class cmdShooter_AutoShootHighGoal extends CommandBase {
  subDriveSystem drive;
  subLimeLightSystem lime;
  subShootSystem shooter;
  subFeedSystem feed;
  Double seconds;
  Timer time;
  public cmdShooter_AutoShootHighGoal(subDriveSystem _drive, subLimeLightSystem _lime, subShootSystem _shooter, subFeedSystem _feed, double _seconds) {
    drive = _drive;
    lime = _lime;
    shooter = _shooter;
    feed = _feed;
    seconds = _seconds;
    addRequirements(drive, shooter, feed);
  }

  @Override
  public void initialize() {
    time = new Timer();
    time.start();
    if(feed.BallInShooter())
    {
      feed.reverseFeed(); 
    }
  }

  @Override
  public void execute() {
    shooter.setShooterToHighGoal();
    //if(drive.autoMoveToTargetDistance()){
      if(time.get() > 1){
        feed.runFeed();
      }
    //}
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
