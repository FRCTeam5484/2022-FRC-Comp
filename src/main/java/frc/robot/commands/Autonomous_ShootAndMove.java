package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.subDriveSystem;
import frc.robot.subsystems.subFeedSystem;
import frc.robot.subsystems.subShootSystem;

public class Autonomous_ShootAndMove extends SequentialCommandGroup {
  
  public Autonomous_ShootAndMove(subDriveSystem _drive, subFeedSystem _feed, subShootSystem _shooter) {
    addCommands(
      // Shoot ball
      new cmdShooter_AutoShootHighGoal(_shooter, _feed, 3),
      // Leave loading zone
      new cmdDrive_DriveStraightByEncoder(_drive, 30, 0.7),
      new InstantCommand(() -> _drive.setDriveLocked())
    );
  }
}