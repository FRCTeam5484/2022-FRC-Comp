package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

public class subClimbSystem extends SubsystemBase {
  private CANSparkMax climbMotor = new CANSparkMax(ClimbSystem.ClimbId, MotorType.kBrushless);
  private RelativeEncoder climbEncoder = climbMotor.getEncoder();

  public subClimbSystem() {
    SetMotorSettings();
  }

  @Override
  public void periodic() {}

  private void SetMotorSettings() {
    climbMotor.restoreFactoryDefaults();
    climbMotor.setSmartCurrentLimit(40);
    climbMotor.setInverted(ClimbSystem.ClimbInverted);
    climbMotor.setIdleMode(IdleMode.kBrake);
    climbMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, (float)ClimbSystem.ClimbEncoderTopLimit);
    climbMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, (float)ClimbSystem.ClimbEncoderBottomLimit);
    climbMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    climbMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
    climbMotor.burnFlash();
  }

  public void stopClimb(){
    climbMotor.stopMotor();
  }

  public void TeleOp(final XboxController driver, boolean climbIsUp){
    // If driver is holding right trigger, enable climb
    if(driver.getRightTriggerAxis() > 0.5){
      double driverCommand = driver.getLeftY() > 0.1 || driver.getLeftY() < -0.1 ? -driver.getLeftY() : 0.0;
      if(driverCommand > 0 && getPosition() >= ClimbSystem.ClimbEncoderTopLimit){
        climbMotor.stopMotor();
        setRumble(driver, 1);
      }
      else if (driverCommand < 0 && getPosition() <= ClimbSystem.ClimbEncoderBottomLimit){
        climbMotor.stopMotor();
        setRumble(driver, 1);
      }
      else{
        climbMotor.set(driverCommand);
      }
    }
    else{
      climbMotor.stopMotor();
    }

    /* **************************************************************************************** 
       This was used when climb was out of spec.  Do not delete incase we need it in the future
       ****************************************************************************************

    // If Climb is up and climb is over 5' 6", auto pull it down.
    if(climbIsUp && getClimbEncoderPosition() > ClimbSystem.ClimbEncoderTopLimitWhenUp){
      climbMotor.set(-0.5);
    }
    // If driver is holding right trigger, enable climb
    else if(driver.getRightTriggerAxis() > 0.5){
      // Get climb speed value from driver left Y stick
      double driverCommand = driver.getLeftY() > 0.1 || driver.getLeftY() < -0.1 ? -driver.getLeftY() : 0.0;
      // If climb is up and request is overdriving top limit up, stop motor
      if(climbIsUp && driverCommand > 0 && getClimbEncoderPosition() >= ClimbSystem.ClimbEncoderTopLimitWhenUp){
        climbMotor.stopMotor();
        setRumble(driver, 1);
      }
      // If Climb is down and request is overdriving top limit down, stop motor
      else if(!climbIsUp && driverCommand > 0 && getClimbEncoderPosition() >= ClimbSystem.ClimbEncoderTopLimitWhenDown){
        climbMotor.stopMotor();
        setRumble(driver, 1);
      }
      // If request is overdriving going down, stop motor
      else if (driverCommand < 0 && getClimbEncoderPosition() <= ClimbSystem.ClimbEncoderBottomLimit){
        climbMotor.stopMotor();
        setRumble(driver, 1);
      }
      // Else take command and move or stop lift
      else{
        climbMotor.set(driverCommand);
      }
    }
    else{
      climbMotor.stopMotor();
    }
    */
  }

  public void setRumble(XboxController driver, double secs){
    Timer time = new Timer();
    time.start();
    driver.setRumble(RumbleType.kLeftRumble, 1);
    if(time.get() > secs) { driver.setRumble(RumbleType.kLeftRumble, 0); }
  }

  public void setPullClimbDown(){
    climbMotor.set(-1);
  }

  public double getVelocity(){
    return climbEncoder.getVelocity();
  }

  public double getPosition(){
    return climbEncoder.getPosition();
  }
}