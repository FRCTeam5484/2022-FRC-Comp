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

public class Autonomous_Option3 extends SequentialCommandGroup {
  public Autonomous_Option3(subDriveSystem drive, subFeedSystem feed, subIntakeSystem intake, subPneumaticSystem air, subShootSystem shooter, subLimeLightSystem lime) {
    addCommands(
      // Run intake, lower intake, drive backwards and pickup ball
      new ParallelCommandGroup(
        new InstantCommand(() -> intake.runIntake()),
        new InstantCommand(() -> air.lowerIntake()),
        new cmdDrive_DriveStraightByEncoder(drive, 35, 0.4)
      ),
      // Stop intake, raise intake, drive forwards
      new InstantCommand(() -> drive.setDriveLocked()),
      new ParallelCommandGroup(
        new InstantCommand(() -> intake.stopIntake()),
        new InstantCommand(() -> air.raiseIntake()),
        new cmdDrive_DriveStraightByEncoder(drive, 27, -0.5)
      ),
      // Stop drive, shoot balls
      new RunCommand(()-> drive.autoMoveToTargetDistance()).withTimeout(2),
      new InstantCommand(() -> drive.setDriveLocked()),
      new cmdShooter_AutoShootHighGoal(drive, lime, shooter, feed, 3),
      // Leave loading zone
      new cmdDrive_DriveStraightByEncoder(drive, 30, 0.7),
      new InstantCommand(() -> drive.setDriveLocked())
    );
  }
}
