package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerSystem;

public class subIndexerSystem extends SubsystemBase {
  public CANSparkMax indexerMotor = new CANSparkMax(IndexerSystem.IndexerId, MotorType.kBrushless);
  public subIndexerSystem() {
    SetMotorSettings();
  }

  @Override
  public void periodic() { }

  public void SetMotorSettings() {
    indexerMotor.restoreFactoryDefaults();
    indexerMotor.setInverted(IndexerSystem.IndexerInverted);
    indexerMotor.setSmartCurrentLimit(15);
    indexerMotor.setIdleMode(IdleMode.kCoast);
    indexerMotor.burnFlash();
  }

  public void runIndexer(){
    indexerMotor.set(IndexerSystem.IndexerSpeed);
  }

  public void stopIndexer(){
    indexerMotor.stopMotor();
  }
}
