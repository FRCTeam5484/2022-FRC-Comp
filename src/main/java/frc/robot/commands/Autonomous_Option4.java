package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.subFeedSystem;
import frc.robot.subsystems.subDriveSystem;
import frc.robot.subsystems.subIntakeSystem;
import frc.robot.subsystems.subLimeLightSystem;
import frc.robot.subsystems.subPneumaticSystem;
import frc.robot.subsystems.subShootSystem;

public class Autonomous_Option4 extends SequentialCommandGroup {
  public Autonomous_Option4(subDriveSystem _drive, subFeedSystem _feed, subIntakeSystem _intake, subPneumaticSystem _air, subShootSystem _shooter, subLimeLightSystem _lime) {
    addCommands(
      // Lower intake, run intake and drive backwards to ball
      new ParallelCommandGroup(
        new InstantCommand(() -> _intake.runIntake()),
        new InstantCommand(() -> _air.lowerIntake()),
        new cmdDrive_DriveStraightByEncoder(_drive, 22, 0.5).withTimeout(4)
      ),
      // Stop drive, stop intake, raise intake, drive forward
      new InstantCommand(() -> _drive.setDriveLocked()),
      new ParallelCommandGroup(
        new InstantCommand(() -> _intake.stopIntake()),
        new InstantCommand(() -> _air.raiseIntake()),
        new cmdDrive_DriveStraightByEncoder(_drive, 20, -0.5)
      ),
      // Stop drive, shoot balls, turn robot
      new InstantCommand(() -> _drive.setDriveLocked()),
      new cmdAuto_AlignToTarget(_drive, _lime).withTimeout(2),
      new cmdShooter_AutoShootHighGoal(_shooter, _feed, 3),
      new InstantCommand(() -> _drive.ResetGyro()),
      new cmdDrive_GyroTurnToAngle(_drive, 70),
      // Run intake, lower intake, drive to ball
      new ParallelCommandGroup(
        new InstantCommand(() -> _intake.runIntake()),
        new InstantCommand(() -> _air.lowerIntake()),
        new cmdDrive_DriveStraightByEncoder(_drive, 50, 0.5)
      ),
      // stop drive, stop intake, raise intake, drive forward
      new InstantCommand(() -> _drive.setDriveLocked()),
      new ParallelCommandGroup(
        new InstantCommand(() -> _intake.stopIntake()),
        new InstantCommand(() -> _air.raiseIntake()),
        new cmdDrive_DriveStraightByEncoder(_drive, 35, -0.5)
      ),
      // Turn robot, shoot balls, turn robot drive out.
      new InstantCommand(() -> _drive.ResetGyro()),
      new cmdDrive_GyroTurnToAngle(_drive, -70),
      new cmdAuto_AlignToTarget(_drive, _lime).withTimeout(2),
      new cmdShooter_AutoShootHighGoal(_shooter, _feed, 3),
      new InstantCommand(() -> _drive.ResetGyro()),
      new cmdDrive_GyroTurnToAngle(_drive, 70),
      new cmdDrive_DriveStraightByEncoder(_drive, 28, 0.3)
    );
  }
}
