package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

public class subIntakeSystem extends SubsystemBase {
  public CANSparkMax intakeMotor = new CANSparkMax(IntakeSystem.IntakeId, MotorType.kBrushless);
  private RelativeEncoder intakeEncoder = intakeMotor.getEncoder();

  public subIntakeSystem() {
    SetMotorSettings();
  }

  @Override
  public void periodic() {}

  private void SetMotorSettings() {
    intakeMotor.restoreFactoryDefaults();
    intakeMotor.setInverted(IntakeSystem.IntakeInverted);
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

  public double getVelocity(){
    return intakeEncoder.getVelocity();
  }
}
