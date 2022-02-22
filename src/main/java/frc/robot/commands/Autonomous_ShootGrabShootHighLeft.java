package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.subFeedSystem;
import frc.robot.subsystems.subDriveSystem;
import frc.robot.subsystems.subIntakeSystem;
import frc.robot.subsystems.subPneumaticSystem;
import frc.robot.subsystems.subShootSystem;

public class Autonomous_ShootGrabShootHighLeft extends SequentialCommandGroup {
  public Autonomous_ShootGrabShootHighLeft(subDriveSystem _drive, subFeedSystem _feed, subIntakeSystem _intake, subPneumaticSystem _air, subShootSystem _shooter) {
    addCommands(
      new cmdShooter_AutoShootHighGoal(_shooter, _feed, 2),  
      // Run intake, lower intake, drive backwards and pickup ball
      new ParallelCommandGroup(
        new InstantCommand(() -> _intake.runIntake()),
        new InstantCommand(() -> _air.lowerIntake()),
        new cmdDrive_DriveStraightByEncoder(_drive, 35, 0.6)
      ),
      // Stop intake, raise intake, drive forwards
      new InstantCommand(() -> _drive.setDriveLocked()),
      new ParallelCommandGroup(
        new InstantCommand(() -> _intake.stopIntake()),
        new InstantCommand(() -> _air.raiseIntake()),
        new cmdDrive_DriveStraightByEncoder(_drive, 27, -0.5)
      ),
      // Stop drive, shoot balls
      new InstantCommand(() -> _drive.setDriveLocked()),
      new cmdShooter_AutoShootHighGoal(_shooter, _feed, 3),
      // Leave loading zone
      new cmdDrive_DriveStraightByEncoder(_drive, 30, 0.7),
      new InstantCommand(() -> _drive.setDriveLocked())
    );
  }
}
