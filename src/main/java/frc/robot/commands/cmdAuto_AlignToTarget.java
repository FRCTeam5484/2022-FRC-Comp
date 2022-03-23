package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DriveSystem;
import frc.robot.subsystems.subDriveSystem;
import frc.robot.subsystems.subLimeLightSystem;

public class cmdAuto_AlignToTarget extends PIDCommand {
  public cmdAuto_AlignToTarget(subDriveSystem drive, subLimeLightSystem lime) {
    super(
        new PIDController(
          DriveSystem.TurnPValue, 
          DriveSystem.TurnIValue, 
          DriveSystem.TurnDValue),
        lime::getTX,
        0,
        output -> drive.autoTurn(-output),
        drive);
        getController().setTolerance(0.5);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
