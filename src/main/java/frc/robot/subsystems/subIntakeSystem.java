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
  public CANSparkMax indexerMotor = new CANSparkMax(IntakeSystem.IndexerId, MotorType.kBrushless);

  public subIntakeSystem() {
    SetMotorSettings();
  }

  @Override
  public void periodic() {}

  private void SetMotorSettings() {
    intakeMotor.restoreFactoryDefaults();
    intakeMotor.setInverted(IntakeSystem.IntakeInverted);
    intakeMotor.setSmartCurrentLimit(15);
    intakeMotor.setIdleMode(IdleMode.kCoast);
    intakeMotor.burnFlash();

    indexerMotor.restoreFactoryDefaults();
    indexerMotor.setInverted(IntakeSystem.IndexerInverted);
    indexerMotor.setSmartCurrentLimit(15);
    indexerMotor.setIdleMode(IdleMode.kCoast);
    indexerMotor.burnFlash();
  }

  public void TeleOp(XboxController driver){
    if(driver.getLeftTriggerAxis() > 0.1){
      runIntake();
      runIndexer();
    }
    else if (driver.getRightTriggerAxis() > 0.1) {
      runIntakeReverse();
    }
    else{
      stopIntake();
      stopIndexer();
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

  public void runIndexer(){
    indexerMotor.set(IntakeSystem.IndexerSpeed);
  }

  public void stopIndexer(){
    indexerMotor.stopMotor();
  }
}
