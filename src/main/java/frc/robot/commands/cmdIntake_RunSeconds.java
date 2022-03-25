package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subIndexerSystem;
import frc.robot.subsystems.subIntakeSystem;

public class cmdIntake_RunSeconds extends CommandBase {
  subIntakeSystem intake;
  subIndexerSystem indexer;
  Timer time;
  public cmdIntake_RunSeconds(subIntakeSystem _intake, subIndexerSystem _indexer) {
    intake = _intake;
    indexer = _indexer;
    addRequirements(intake);
    addRequirements(indexer);
  }

  @Override
  public void initialize() {
    time = new Timer();
    time.start();
  }

  @Override
  public void execute() {
    intake.runIntake();
    indexer.runIndexer();
  }

  @Override
  public void end(boolean interrupted) {
    intake.stopIntake();
    indexer.stopIndexer();
  }

  @Override
  public boolean isFinished() {
    return time.get() > 0.5 ? true : false;
  }
}
