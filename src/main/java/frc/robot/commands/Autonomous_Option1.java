package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.subDriveSystem;
import frc.robot.subsystems.subFeedSystem;
import frc.robot.subsystems.subLimeLightSystem;
import frc.robot.subsystems.subShootSystem;

public class Autonomous_Option1 extends SequentialCommandGroup {
  
  public Autonomous_Option1(subDriveSystem drive, subLimeLightSystem lime, subFeedSystem feed, subShootSystem shooter) {
    addCommands(
      // Move
      new cmdDrive_DriveStraightByEncoder(drive, 8, 0.6),
      // Stop
      new InstantCommand(() -> drive.setDriveLocked()),
      // Shoot ball
      new cmdShooter_AutoShootHighGoal(drive, lime, shooter, feed, 3),
      // Leave loading zone
      new cmdDrive_DriveStraightByEncoder(drive, 20, 0.7),
      new InstantCommand(() -> drive.setDriveLocked())
    );
  }
}