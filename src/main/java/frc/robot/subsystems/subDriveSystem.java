package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

public class subDriveSystem extends SubsystemBase {
  //Making Speed Controllers
  public CANSparkMax leftDriveMaster = new CANSparkMax(DriveSystem.LeftMasterMotorId, MotorType.kBrushless);
  public CANSparkMax leftDriveSlave = new CANSparkMax(DriveSystem.LeftSlaveMotorId, MotorType.kBrushless);
  public CANSparkMax rightDriveMaster = new CANSparkMax(DriveSystem.RightMasterMotorId, MotorType.kBrushless);
  public CANSparkMax rightDriveSlave = new CANSparkMax(DriveSystem.RightSlaveMotorId, MotorType.kBrushless);
  private DifferentialDrive driveTrain = new DifferentialDrive(leftDriveMaster, rightDriveMaster);
  private SparkMaxPIDController leftPidController = leftDriveMaster.getPIDController();
  private SparkMaxPIDController rightPidController = rightDriveMaster.getPIDController();
  private RelativeEncoder left1Encoder = leftDriveMaster.getEncoder();
  private RelativeEncoder left2Encoder = leftDriveSlave.getEncoder();
  private RelativeEncoder right1Encoder = rightDriveMaster.getEncoder();
  private RelativeEncoder right2Encoder = rightDriveSlave.getEncoder();
  private subLimeLight limeLight;
  
  public subDriveSystem(subLimeLight _limeLight) {
    limeLight = _limeLight;
    SetMotorSettings();
  }

  public AHRS gyro = new AHRS(SPI.Port.kMXP);

  @Override
  public void periodic() {}

  private void SetMotorSettings() {
    leftDriveMaster.restoreFactoryDefaults();
    leftDriveSlave.restoreFactoryDefaults();

    leftDriveMaster.setInverted(DriveSystem.LeftDriveInverted);

    leftDriveMaster.setIdleMode(IdleMode.kCoast);
    leftDriveSlave.setIdleMode(IdleMode.kCoast);

    leftDriveSlave.follow(leftDriveMaster, false);

    leftDriveMaster.setSmartCurrentLimit(50);
    leftDriveSlave.setSmartCurrentLimit(50);

    rightDriveMaster.restoreFactoryDefaults();
    rightDriveSlave.restoreFactoryDefaults();

    rightDriveMaster.setInverted(DriveSystem.RightDriveInverted);

    rightDriveMaster.setIdleMode(IdleMode.kCoast);
    rightDriveSlave.setIdleMode(IdleMode.kCoast);

    rightDriveSlave.follow(rightDriveMaster, false);

    rightDriveMaster.setSmartCurrentLimit(50);
    rightDriveSlave.setSmartCurrentLimit(50);

    leftDriveMaster.burnFlash();
    leftDriveSlave.burnFlash();
    rightDriveMaster.burnFlash();
    rightDriveSlave.burnFlash();

  }

  //Drive Methods
  public void stopDrive() {
    leftDriveMaster.stopMotor();
    rightDriveMaster.stopMotor();
  }
  
  public void TeleOp(final XboxController driver) {
    driveTrain.setDeadband(0.08);
    driveTrain.tankDrive(driver.getLeftY() * DriveSystem.ManualSpeedFactor, driver.getRightY() * DriveSystem.ManualSpeedFactor);
  }

  public void autoTurn(double targetRot) {
    driveTrain.arcadeDrive(0, MathUtil.clamp(-targetRot, DriveSystem.AutoMinSpeed, DriveSystem.AutoMaxSpeed));
  }

  public void autoDriveByPower(double targetSpeed) {
    driveTrain.arcadeDrive(MathUtil.clamp(-targetSpeed, DriveSystem.AutoMinSpeed, DriveSystem.AutoMaxSpeed), 0);
    System.out.println(-targetSpeed);
  }

  public void autoDriveByEncoderPID(double encoderDistance){
    leftPidController.setP(6e-5);
    leftPidController.setI(0);
    leftPidController.setD(0);
    leftPidController.setIZone(0);
    leftPidController.setFF(0.000015);
    leftPidController.setOutputRange(-1, 1);
    leftPidController.setReference(encoderDistance, CANSparkMax.ControlType.kPosition);

    rightPidController.setP(6e-5);
    rightPidController.setI(0);
    rightPidController.setD(0);
    rightPidController.setIZone(0);
    rightPidController.setFF(0.000015);
    rightPidController.setOutputRange(-1, 1);
    rightPidController.setReference(encoderDistance, CANSparkMax.ControlType.kPosition);
  }

  public void setDriveLocked() {
    leftDriveMaster.stopMotor();
    rightDriveMaster.stopMotor();
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
    left1Encoder.setPosition(0);
    left2Encoder.setPosition(0);
    right1Encoder.setPosition(0);
    right2Encoder.setPosition(0);
  }

  public double getLeftPosition(){
    return -left1Encoder.getPosition();
  }

  public double getRightPosition(){
    return -right1Encoder.getPosition();
  }

  public double getAverageEncoderDistance(){
    return (getLeftPosition() + getRightPosition()) / 2.0;
  }

  public void ResetGyro(){
    gyro.zeroYaw();
  }
}