package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

public class subDriveSystem extends SubsystemBase {
  //Making Speed Controllers
  public CANSparkMax leftDriveMaster = new CANSparkMax(DriveSystem.LeftMasterMotorId, MotorType.kBrushless);
  public CANSparkMax leftDriveSlave = new CANSparkMax(DriveSystem.LeftSlaveMotorId, MotorType.kBrushless);
  public CANSparkMax rightDriveMaster = new CANSparkMax(DriveSystem.RightMasterMotorId, MotorType.kBrushless);
  public CANSparkMax rightDriveSlave = new CANSparkMax(DriveSystem.RightSlaveMotorId, MotorType.kBrushless);
  public MotorControllerGroup leftDrive = new MotorControllerGroup(leftDriveMaster, leftDriveSlave);
  public MotorControllerGroup rightDrive = new MotorControllerGroup(rightDriveMaster, rightDriveSlave);
  private DifferentialDrive driveTrain = new DifferentialDrive(leftDrive, rightDrive);
  
  public subDriveSystem() {
    SetMotorSettings();
  }

  public AHRS gyro = new AHRS(SPI.Port.kMXP);

  @Override
  public void periodic() {}

  private void SetMotorSettings() {
    leftDriveMaster.restoreFactoryDefaults();
    leftDriveSlave.restoreFactoryDefaults();

    leftDriveMaster.setInverted(DriveSystem.LeftDriveInverted);
    leftDriveSlave.setInverted(DriveSystem.LeftDriveInverted);

    leftDriveMaster.setIdleMode(IdleMode.kCoast);
    leftDriveSlave.setIdleMode(IdleMode.kCoast);

    leftDriveMaster.setSmartCurrentLimit(50);
    leftDriveSlave.setSmartCurrentLimit(50);

    rightDriveMaster.restoreFactoryDefaults();
    rightDriveSlave.restoreFactoryDefaults();

    rightDriveMaster.setInverted(DriveSystem.RightDriveInverted);
    rightDriveSlave.setInverted(DriveSystem.RightDriveInverted);

    rightDriveMaster.setIdleMode(IdleMode.kCoast);
    rightDriveSlave.setIdleMode(IdleMode.kCoast);

    rightDriveMaster.setSmartCurrentLimit(50);
    rightDriveSlave.setSmartCurrentLimit(50);

    leftDriveMaster.burnFlash();
    leftDriveSlave.burnFlash();
    rightDriveMaster.burnFlash();
    rightDriveSlave.burnFlash();
  }

  //Drive Methods
  public void stopDrive() {
    leftDrive.stopMotor();
    rightDrive.stopMotor();
  }
  
  public void TeleOp(final XboxController driver) {
    driveTrain.tankDrive(-driver.getLeftY() * DriveSystem.ManualSpeedFactor, -driver.getRightY() * DriveSystem.ManualSpeedFactor);
  }

  public void autoTurn(double targetRot) {
    driveTrain.arcadeDrive(0, MathUtil.clamp(targetRot, DriveSystem.AutoMinSpeed, DriveSystem.AutoMaxSpeed));
  }

  public void autoDriveByPower(double targetSpeed) {
    driveTrain.arcadeDrive(MathUtil.clamp(targetSpeed, DriveSystem.AutoMinSpeed, DriveSystem.AutoMaxSpeed), 0);
  }

  public void setDriveLocked() {
    stopDrive();
    leftDriveMaster.setIdleMode(IdleMode.kBrake);
    leftDriveSlave.setIdleMode(IdleMode.kBrake);
    rightDriveMaster.setIdleMode(IdleMode.kBrake);
    rightDriveSlave.setIdleMode(IdleMode.kBrake);
  }

  public void setDriveUnlocked() {
    leftDriveMaster.setIdleMode(IdleMode.kCoast);
    leftDriveSlave.setIdleMode(IdleMode.kCoast);
    rightDriveMaster.setIdleMode(IdleMode.kCoast);
    rightDriveSlave.setIdleMode(IdleMode.kCoast);
  }

  //Gyro Methods
  public double getGyroHeading(){
    return Math.IEEEremainder(gyro.getAngle(), 360) * (DriveSystem.GyroReversed ? -1.0 : 1.0);
  }

  //Encoder Methods
  public void ResetEncoders(){
    leftDriveMaster.getEncoder().setPosition(0);
    leftDriveSlave.getEncoder().setPosition(0);
    rightDriveMaster.getEncoder().setPosition(0);
    rightDriveSlave.getEncoder().setPosition(0);
  }

  public double getLeftPosition(){
    return -leftDriveMaster.getEncoder().getPosition();
  }

  public double getRightPosition(){
    return -rightDriveMaster.getEncoder().getPosition();
  }

  public double getAverageEncoderDistance(){
    return (getLeftPosition() + getRightPosition()) / 2.0;
  }

  public void ResetGyro(){
    gyro.zeroYaw();
  }
}