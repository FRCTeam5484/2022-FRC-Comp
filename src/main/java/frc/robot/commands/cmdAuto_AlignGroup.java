package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.subDriveSystem;
import frc.robot.subsystems.subLimeLightSystem;

public class cmdAuto_AlignGroup extends SequentialCommandGroup {
  public cmdAuto_AlignGroup(subDriveSystem drive, subLimeLightSystem lime) {
    addCommands(
      new InstantCommand(() -> lime.setToAutoTargetMode()),
      new cmdAuto_AlignToTarget(drive, lime)
      //new cmdAuto_DriveToTarget(drive, lime)
    );
  }
}
