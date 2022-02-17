package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterSystem;

public class subFeedSystem extends SubsystemBase {
  private CANSparkMax feedMotor = new CANSparkMax(ShooterSystem.FeedId, MotorType.kBrushless);
  private RelativeEncoder feedEncoder = feedMotor.getEncoder();
  
  public subFeedSystem() {
    SetMotorSettings();
  }

  @Override
  public void periodic() {}

  private void SetMotorSettings() {
    feedMotor.restoreFactoryDefaults();
    feedMotor.setInverted(ShooterSystem.BallFeedInverted);
    feedMotor.setIdleMode(IdleMode.kCoast);
    feedMotor.burnFlash();
  }

  //#region Ball Feeder Methods
  public void runFeed(){
    feedMotor.set(ShooterSystem.BallFeedSpeed);
  }

  public void reverseFeed(){
    feedMotor.set(-ShooterSystem.BallFeedSpeed);
  }

  public void stopFeed(){
    feedMotor.stopMotor();
  }
  //#endregion

  public double getVelocity(){
    return feedEncoder.getVelocity();
  }
}
