package frc.robot.subsystems;

import java.text.DecimalFormat;

import org.opencv.core.Mat;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveSystem;
import frc.robot.Constants.LimeLightSystem;

public class subLimeLightSystem extends SubsystemBase {
  private NetworkTable networkTable;
  private NetworkTableEntry yEntry;
  private NetworkTableEntry vEntry;
  private NetworkTableEntry xEntry;
  private NetworkTableEntry pipelineEntry;
  double ty;
  double tv;
  double tx;
  public boolean HasValidTarget = false;
  public double DriveCommand = 0.0;
  public double SteerCommand = 0.0;

  public PIDController steerPID;
  public PIDController drivePID;

  public subLimeLightSystem() {
    drivePID = new PIDController(DriveSystem.DrivePValue, DriveSystem.DriveIValue, DriveSystem.DriveDValue);
    steerPID = new PIDController(DriveSystem.TurnPValue, DriveSystem.TurnIValue, DriveSystem.TurnDValue);
  }

  @Override
  public void periodic() { 
    getValues();
  }

  public void getValues(){
    networkTable = NetworkTableInstance.getDefault().getTable("limelight");
    yEntry = networkTable.getEntry("ty");
    vEntry = networkTable.getEntry("tv");
    xEntry = networkTable.getEntry("tx"); 
    pipelineEntry = networkTable.getEntry("pipeline");
    
    ty = yEntry.getDouble(0.0);
    tv = vEntry.getDouble(0.0);
    tx = xEntry.getDouble(0.0);

    HasValidTarget = ((tv < 1.0) ? false : true);

    if (!HasValidTarget)
    {
      DriveCommand = 0.0;
      SteerCommand = 0.0;
    }
    else {
      SteerCommand = MathUtil.clamp(-steerPID.calculate(tx, 0), -0.5, 0.5);
      DriveCommand = MathUtil.clamp(-drivePID.calculate(ty, 0), -0.4, 0.4);
    }
  }

  public double getTY(){
    return ty;
  }

  public double getTV(){
    return tv;
  }

  public double getTX(){
    return tx;
  }

  public boolean hasTarget(){
    return tv == 1 ? true : false;
  }

  public void setToAutoTargetMode(){
    pipelineEntry.setNumber(1);
  }

  public void setToDriverMode(){
    pipelineEntry.setNumber(0);
  }

  public double getSteerCommand(){
    return SteerCommand;
  }

  public double getDriveCommand(){
    return DriveCommand;
  }
}