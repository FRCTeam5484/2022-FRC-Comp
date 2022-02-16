package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class subLimeLight extends SubsystemBase {
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

  public subLimeLight() {}

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

    SmartDashboard.putNumber("Limelight Y Offset", ty);
    SmartDashboard.putNumber("Limelight Target Area", ta);
    SmartDashboard.putNumber("Limelight Latency", tl);
    SmartDashboard.putNumber("Limelight Valid Target", tv);
    SmartDashboard.putNumber("Limelight Skew", ts);
    SmartDashboard.putNumber("LimeLight X Offset" ,tx);
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
    pipelineEntry.setNumber(0);
  }

  public void setToDriverMode(){
    pipelineEntry.setNumber(1);
  }
}