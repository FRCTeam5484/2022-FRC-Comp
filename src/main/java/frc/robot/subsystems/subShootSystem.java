package frc.robot.subsystems;

import frc.robot.Constants.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class subShootSystem extends SubsystemBase {
  public CANSparkMax shooterMotor = new CANSparkMax(ShooterSystem.ShooterId, MotorType.kBrushless);
  private RelativeEncoder encoder = shooterMotor.getEncoder();
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;

  public subShootSystem() {
    SetMotorSettings();
  }

  @Override
  public void periodic() { }

  private void SetMotorSettings(){
    shooterMotor.restoreFactoryDefaults();
    shooterMotor.setSmartCurrentLimit(50);
    shooterMotor.setInverted(ShooterSystem.ShooterInverted);
    shooterMotor.setIdleMode(IdleMode.kCoast);
    shooterMotor.burnFlash();
  }

  public void TeleOp(XboxController driver){
    shooterMotor.set(driver.getRightY());
  }

  //#region Shooter Methods
  public void setShooterSpeed(double speed){
    shooterMotor.set(speed);
  }

  public void stopShooter(){
    shooterMotor.stopMotor();
  }

  public double getPowerValue(){
    return shooterMotor.get();
  }

  public double getVelocity(){
    return encoder.getVelocity();
  }

  public void setShooterToHighGoal(){
    shooterMotor.setVoltage(6.25);
  }

  public void setShooterToLowGoal(){
    shooterMotor.setVoltage(3.5);
  }
  public void setShooterReversed(){
    shooterMotor.set(-0.8);
  }
  //#endregion
}