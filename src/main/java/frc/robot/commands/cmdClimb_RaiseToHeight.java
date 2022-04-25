package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.ClimbSystem;
import frc.robot.subsystems.subClimbSystem;

public class cmdClimb_RaiseToHeight extends PIDCommand {
  public cmdClimb_RaiseToHeight(subClimbSystem climb) {
    super(
      new PIDController(
          ClimbSystem.ClimbPValue, 
          ClimbSystem.ClimbIValue, 
          ClimbSystem.ClimbDValue),
        climb::getPosition,
        ClimbSystem.ClimbEncoderTopLimitWhenUp,
        output -> climb.AutoClimb(output),
        climb);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
