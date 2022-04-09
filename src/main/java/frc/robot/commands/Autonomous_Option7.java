package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.subFeedSystem;
import frc.robot.subsystems.subIndexerSystem;
import frc.robot.subsystems.subDriveSystem;
import frc.robot.subsystems.subIntakeSystem;
import frc.robot.subsystems.subLimeLightSystem;
import frc.robot.subsystems.subPneumaticSystem;
import frc.robot.subsystems.subShootSystem;

public class Autonomous_Option7 extends SequentialCommandGroup {
  public Autonomous_Option7(subDriveSystem _drive, subFeedSystem _feed, subIntakeSystem _intake, subPneumaticSystem _air, subShootSystem _shooter, subLimeLightSystem _lime, subIndexerSystem _indexer) {
    addCommands(
      // Run intake, lower intake, drive backwards and pickup ball
      new ParallelCommandGroup(
        new InstantCommand(() -> _intake.runIntake()),
        new InstantCommand(() -> _air.lowerIntake()),
        new cmdDrive_DriveStraightByEncoder(_drive, 35, 0.4)
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
      new InstantCommand(() -> _drive.ResetGyro()),
      new cmdDrive_GyroTurnToAngle(_drive, -10).withTimeout(2),
      new cmdShooter_AutoShootHighGoal(_drive, _lime, _shooter, _feed, 3),
      // Turn to human feed and go forward
      new InstantCommand(() -> _drive.ResetGyro()),
      new cmdDrive_GyroTurnToAngle(_drive, 24).withTimeout(2),
      new InstantCommand(() -> _drive.setDriveLocked()),
      new cmdDrive_DriveStraightByEncoder(_drive, 57, 0.7),
      new InstantCommand(() -> _drive.setDriveLocked()),
      new ParallelCommandGroup(
        new InstantCommand(() -> _intake.runIntake()),
        new InstantCommand(() -> _air.lowerIntake()),
        new InstantCommand(() -> _indexer.runIndexer()),
        new cmdDrive_DriveStraightByEncoder(_drive, 25, 0.4).withTimeout(4)
      ),      
      new InstantCommand(() -> _drive.setDriveLocked()),
      new ParallelCommandGroup(
        new InstantCommand(() -> _intake.stopIntake()),
        new InstantCommand(() -> _air.raiseIntake()),
        new InstantCommand(() -> _indexer.runIndexer()),
        new cmdDrive_DriveStraightByEncoder(_drive, 70, -0.7)
      ),
      new InstantCommand(() -> _drive.setDriveLocked()),
      new InstantCommand(() -> _drive.ResetGyro()),
      new cmdDrive_GyroTurnToAngle(_drive, -20)
    );
  }
}