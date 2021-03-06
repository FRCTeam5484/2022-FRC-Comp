package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

public class subIntakeSystem extends SubsystemBase {
  public CANSparkMax intakeMotor = new CANSparkMax(IntakeSystem.IntakeId, MotorType.kBrushless);

  public subIntakeSystem() {
    SetMotorSettings();
  }

  @Override
  public void periodic() {}

  public void SetMotorSettings() {
    intakeMotor.restoreFactoryDefaults();
    intakeMotor.setInverted(IntakeSystem.IntakeInverted);
    intakeMotor.setSmartCurrentLimit(30);
    intakeMotor.setIdleMode(IdleMode.kCoast);
    intakeMotor.burnFlash();
  }

  public void TeleOp(XboxController driver){
    if(driver.getLeftTriggerAxis() > 0.1){
      runIntake();
    }
    else if (driver.getRightTriggerAxis() > 0.1) {
      runIntakeReverse();
    }
    else{
      stopIntake();
    }
  }
  public void runIntake(){
    intakeMotor.set(IntakeSystem.IntakeSpeed);
  }
  
  public void runIntakeReverse(){
    intakeMotor.set(-IntakeSystem.IntakeSpeed);
  }

  public void stopIntake(){
    intakeMotor.stopMotor();
  }
}
