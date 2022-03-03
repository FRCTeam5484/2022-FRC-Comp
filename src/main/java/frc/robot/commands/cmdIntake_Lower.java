package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subIndexerSystem;
import frc.robot.subsystems.subIntakeSystem;
import frc.robot.subsystems.subPneumaticSystem;

public class cmdIntake_Lower extends CommandBase {
  subPneumaticSystem air;
  subIntakeSystem intake;
  subIndexerSystem indexer;
  public cmdIntake_Lower(subPneumaticSystem _air, subIntakeSystem _intake, subIndexerSystem _indexer) {
    air = _air;
    intake = _intake;
    indexer = _indexer;
    addRequirements(air);
    addRequirements(intake);
    addRequirements(indexer);
  }

  @Override
  public void initialize() {
    air.lowerIntake();
    intake.runIntake();
    indexer.runIndexer();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) { }

  @Override
  public boolean isFinished() {
    return false;
  }
}
