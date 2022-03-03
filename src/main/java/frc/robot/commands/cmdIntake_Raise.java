package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.subIndexerSystem;
import frc.robot.subsystems.subIntakeSystem;
import frc.robot.subsystems.subPneumaticSystem;

public class cmdIntake_Raise extends SequentialCommandGroup  {
  public cmdIntake_Raise(subPneumaticSystem air, subIntakeSystem intake, subIndexerSystem indexer) {
    addCommands(new InstantCommand(() -> air.raiseIntake()), new cmdIntake_RunSeconds(intake, indexer));
  }
}
