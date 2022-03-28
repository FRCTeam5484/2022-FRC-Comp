package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.subFeedSystem;
import frc.robot.subsystems.subDriveSystem;
import frc.robot.subsystems.subIntakeSystem;
import frc.robot.subsystems.subLimeLightSystem;
import frc.robot.subsystems.subPneumaticSystem;
import frc.robot.subsystems.subShootSystem;

public class Autonomous_Option4 extends SequentialCommandGroup {
  public Autonomous_Option4(subDriveSystem drive, subFeedSystem feed, subIntakeSystem intake, subPneumaticSystem air, subShootSystem shooter, subLimeLightSystem lime) {
    addCommands(
      // Lower intake, run intake and drive backwards to ball
      new ParallelCommandGroup(
        new InstantCommand(() -> intake.runIntake()),
        new InstantCommand(() -> air.lowerIntake()),
        new cmdDrive_DriveStraightByEncoder(drive, 23, 0.5).withTimeout(4)
      ),
      // Stop drive, stop intake, raise intake, drive forward
      new InstantCommand(() -> drive.setDriveLocked()),
      new ParallelCommandGroup(
        new InstantCommand(() -> intake.stopIntake()),
        new InstantCommand(() -> air.raiseIntake()),
        new cmdDrive_DriveStraightByEncoder(drive, 20, -0.5)
      ),
      // Stop drive, shoot balls, turn robot
      new RunCommand(()-> drive.autoMoveToTargetDistance()).withTimeout(2),
      new InstantCommand(() -> drive.setDriveLocked()),
      new cmdShooter_AutoShootHighGoal(drive, lime, shooter, feed, 3),
      new InstantCommand(() -> drive.ResetGyro()),
      new cmdDrive_GyroTurnToAngle(drive, 80),
      // Run intake, lower intake, drive to ball
      new ParallelCommandGroup(
        new InstantCommand(() -> intake.runIntake()),
        new InstantCommand(() -> air.lowerIntake()),
        new cmdDrive_DriveStraightByEncoder(drive, 50, 0.5)
      ),
      // stop drive, stop intake, raise intake, drive forward
      new InstantCommand(() -> drive.setDriveLocked()),
      new ParallelCommandGroup(
        new InstantCommand(() -> intake.stopIntake()),
        new InstantCommand(() -> air.raiseIntake()),
        new cmdDrive_DriveStraightByEncoder(drive, 40, -0.5)
      ),
      // Turn robot, shoot balls, turn robot drive out.
      new InstantCommand(() -> drive.ResetGyro()),
      new cmdDrive_GyroTurnToAngle(drive, -65),
      new RunCommand(()-> drive.autoMoveToTargetDistance()).withTimeout(2),
      new cmdShooter_AutoShootHighGoal(drive, lime, shooter, feed, 3),
      new InstantCommand(() -> drive.ResetGyro()),
      new cmdDrive_GyroTurnToAngle(drive, 70),
      new cmdDrive_DriveStraightByEncoder(drive, 28, 0.3)
    );
  }
}
