package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LimeLightSystem;

public class subLimeLightSystem extends SubsystemBase {
  private NetworkTable networkTable;
  private NetworkTableEntry yEntry;
  private NetworkTableEntry aEntry;
  private NetworkTableEntry lEntry;
  private NetworkTableEntry vEntry;
  private NetworkTableEntry sEntry;
  private NetworkTableEntry xEntry;
  private NetworkTableEntry tshortEntry;
  private NetworkTableEntry tlongEntry;
  private NetworkTableEntry thorEntry;
  private NetworkTableEntry tvertEntry;
  private NetworkTableEntry getpipeEntry;
  private NetworkTableEntry camtranEntry;
  private NetworkTableEntry ledModeEntry;
  private NetworkTableEntry pipelineEntry;
  private NetworkTableEntry camModeEntry;
  double ty;
  double ta;
  double tl;
  double tv;
  double ts;
  double tx;
  public boolean HasValidTarget = false;
  public double DriveCommand = 0.0;
  public double SteerCommand = 0.0;

  public subLimeLightSystem() {}

  @Override
  public void periodic() {
    networkTable = NetworkTableInstance.getDefault().getTable("limelight");
    yEntry = networkTable.getEntry("ty");
    aEntry = networkTable.getEntry("ta");
    lEntry = networkTable.getEntry("tl");
    vEntry = networkTable.getEntry("tv");
    sEntry = networkTable.getEntry("ts");   
    xEntry = networkTable.getEntry("tx"); 
    tshortEntry = networkTable.getEntry("tshort");
    tlongEntry = networkTable.getEntry("tlong");
    thorEntry = networkTable.getEntry("thor");
    tvertEntry = networkTable.getEntry("tvert");
    getpipeEntry = networkTable.getEntry("getpipe");
    camtranEntry = networkTable.getEntry("camtran");
    ledModeEntry = networkTable.getEntry("ledMode");
    pipelineEntry = networkTable.getEntry("pipeline");
    camModeEntry = networkTable.getEntry("camMode");

    ty = yEntry.getDouble(0.0);
    ta = aEntry.getDouble(0.0);
    tl = lEntry.getDouble(0.0);
    tv = vEntry.getDouble(0.0);
    ts = sEntry.getDouble(0.0);
    tx = xEntry.getDouble(0.0);

    HasValidTarget = ((tv < 1.0) ? false : true);

    SmartDashboard.putNumber("Limelight Y Offset", ty);
    SmartDashboard.putNumber("Limelight Target Area", ta);
    SmartDashboard.putNumber("Limelight Latency", tl);
    SmartDashboard.putNumber("Limelight Valid Target", tv);
    SmartDashboard.putNumber("Limelight Skew", ts);
    SmartDashboard.putNumber("LimeLight X Offset" ,tx);
    SmartDashboard.putBoolean("Has Target", HasValidTarget);

    if (tv < 1.0)
    {
      HasValidTarget = false;
      DriveCommand = 0.0;
      SteerCommand = 0.0;
      return;
    }

    HasValidTarget = true;

    double steer_cmd = (tx+LimeLightSystem.tx) * LimeLightSystem.STEER_K;
    SteerCommand = steer_cmd;

    double drive_cmd = (ty-LimeLightSystem.ty) * LimeLightSystem.DRIVE_K;
    DriveCommand = drive_cmd;
  }

  public double getTY(){
    return ty;
  }

  public double getTA(){
    return ta;
  }

  public double getTL(){
    return tl;
  }

  public double getTV(){
    return tv;
  }

  public double getTS(){
    return ts;
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