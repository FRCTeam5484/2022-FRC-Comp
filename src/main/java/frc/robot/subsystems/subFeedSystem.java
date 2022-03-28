package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerSystem;
import frc.robot.Constants.ShooterSystem;

public class subFeedSystem extends SubsystemBase {
  public CANSparkMax feedMotor = new CANSparkMax(ShooterSystem.FeedId, MotorType.kBrushless);
  private RelativeEncoder feedEncoder = feedMotor.getEncoder();
  private MedianFilter shooterBallFilter = new MedianFilter(5);
  private AnalogInput shooterBall = new AnalogInput(IndexerSystem.BallSensorShooterId);
  private MedianFilter intakeBallFilter = new MedianFilter(5);
  private AnalogInput intakeBall = new AnalogInput(IndexerSystem.BallSensorIntakeId);
  
  public subFeedSystem() {
    SetMotorSettings();
  }

  @Override
  public void periodic() {}

  public void SetMotorSettings() {
    feedMotor.restoreFactoryDefaults();
    feedMotor.setInverted(ShooterSystem.BallFeedInverted);
    feedMotor.setIdleMode(IdleMode.kBrake);
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

  public boolean BallInShooter(){
    return shooterBallFilter.calculate(shooterBall.getVoltage()) >= IndexerSystem.BallSensorIntakeValue ? true : false; 
  }

  public boolean BallInIntake(){
    return intakeBallFilter.calculate(intakeBall.getVoltage()) >= IndexerSystem.BallSensorIntakeValue ? true : false; 
  }
}
