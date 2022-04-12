package frc.robot.subsystems;

import org.opencv.core.Mat;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

  public subLimeLightSystem() {}

  @Override
  public void periodic() {
    networkTable = NetworkTableInstance.getDefault().getTable("limelight");
    if((networkTable.getEntry("tv").getDouble(0.0) < 1.0 ? false : true)){
      getValues();
      SmartDashboard.putBoolean("TX Aligned", (DriveCommand < 0.05 && SteerCommand < 0.05 ? true : false));
    }
    else{
      SmartDashboard.putBoolean("TX Aligned", false);
    }
  }

  public void getValues(){
    yEntry = networkTable.getEntry("ty");
    vEntry = networkTable.getEntry("tv");
    xEntry = networkTable.getEntry("tx"); 
    pipelineEntry = networkTable.getEntry("pipeline");
    
    ty = yEntry.getDouble(0.0);
    tv = vEntry.getDouble(0.0);
    tx = xEntry.getDouble(0.0);

    HasValidTarget = ((tv < 1.0) ? false : true);

    if (HasValidTarget)
    {
      DriveCommand = 0.0;
      SteerCommand = 0.0;
    }
    else {
      double steer_cmd = tx * LimeLightSystem.STEER_K;
      SteerCommand = MathUtil.clamp(steer_cmd, DriveSystem.AutoMinSpeed, DriveSystem.AutoMaxSpeed);

      double drive_cmd = ty * LimeLightSystem.DRIVE_K;
      DriveCommand = MathUtil.clamp(drive_cmd, DriveSystem.AutoMinSpeed, DriveSystem.AutoMaxSpeed);
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