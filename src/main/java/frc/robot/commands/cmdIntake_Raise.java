package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subIntakeSystem;
import frc.robot.subsystems.subPneumaticSystem;

public class cmdIntake_Raise extends CommandBase {
  subPneumaticSystem air;
  subIntakeSystem intake;
  public cmdIntake_Raise(subPneumaticSystem _air, subIntakeSystem _intake) {
    air = _air;
    intake = _intake;
    addRequirements(air);
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    air.raiseIntake();
    intake.stopIntake();
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
