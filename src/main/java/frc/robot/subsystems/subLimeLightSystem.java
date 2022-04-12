package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
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
      return;
    }
    else {
      HasValidTarget = true;

      double steer_cmd = (tx+LimeLightSystem.tx) * LimeLightSystem.STEER_K;
      SteerCommand = steer_cmd;

      double drive_cmd = (ty-LimeLightSystem.ty) * LimeLightSystem.DRIVE_K;
      DriveCommand = drive_cmd;
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