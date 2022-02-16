package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DriveSystem;
import frc.robot.subsystems.subDriveSystem;

public class cmdDrive_GyroTurnToAngle extends PIDCommand {
  public cmdDrive_GyroTurnToAngle(subDriveSystem drive, double targetAngle) {
    super(
      new PIDController(
          DriveSystem.TurnPValue, 
          DriveSystem.TurnIValue, 
          DriveSystem.TurnDValue),
        drive::getGyroHeading,
        targetAngle,
        output -> drive.autoTurn(output),
        drive);
        getController().enableContinuousInput(-180, 180);
        getController().setTolerance(DriveSystem.TurnToleranceDeg, DriveSystem.TurnRateToleranceDegPerSec);
  }

  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
