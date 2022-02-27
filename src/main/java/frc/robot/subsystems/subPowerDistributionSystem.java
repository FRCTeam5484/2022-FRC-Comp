package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PowerSystem;

public class subPowerDistributionSystem extends SubsystemBase {
  PowerDistribution pdh;
  public subPowerDistributionSystem() {
    pdh = new PowerDistribution(PowerSystem.pdhId, ModuleType.kRev);
    pdh.setSwitchableChannel(true);
  }

  @Override
  public void periodic() {
    /*
    SmartDashboard.putNumber("Voltage", pdh.getVoltage());
    SmartDashboard.putNumber("Total Current", pdh.getTotalCurrent());
    SmartDashboard.putNumber("Left Master Current", pdh.getCurrent(PowerSystem.leftDriveMasterId));
    SmartDashboard.putNumber("Left Follower Current", pdh.getCurrent(PowerSystem.leftDriveFollowerId));
    SmartDashboard.putNumber("Right Master Current", pdh.getCurrent(PowerSystem.rightDriveMasterId));
    SmartDashboard.putNumber("Right Follower Current", pdh.getCurrent(PowerSystem.rightDriveFollowerId));
    SmartDashboard.putNumber("Shooter Current", pdh.getCurrent(PowerSystem.shooterId));
    SmartDashboard.putNumber("Feed Current", pdh.getCurrent(PowerSystem.feedId));
    SmartDashboard.putNumber("Climb Current", pdh.getCurrent(PowerSystem.climbId));
    SmartDashboard.putNumber("Intake Current", pdh.getCurrent(PowerSystem.intakeId));
    SmartDashboard.putNumber("RoboRio Current", pdh.getCurrent(PowerSystem.roboRioId));
    SmartDashboard.putNumber("Pneumatic Hub Current", pdh.getCurrent(PowerSystem.phId));
    SmartDashboard.putNumber("Radio Current", pdh.getCurrent(PowerSystem.radioId));
    */
  }
}
