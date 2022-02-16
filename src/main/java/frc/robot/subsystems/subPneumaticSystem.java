package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PneumaticSystem;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class subPneumaticSystem extends SubsystemBase {
  private PneumaticHub hub;
  private Solenoid intakeSolenoid;
  private Solenoid climbSolenoid;

  public subPneumaticSystem() {
    hub = new PneumaticHub(PneumaticSystem.PneumaticHubId);
    intakeSolenoid = hub.makeSolenoid(PneumaticSystem.IntakeSolenoid);
    climbSolenoid = hub.makeSolenoid(PneumaticSystem.ClimbSolenoid);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Pressure", hub.getPressure(0));
    SmartDashboard.putBoolean("Compressor Running", hub.getCompressor());
    if (SmartDashboard.getBoolean("Enable Compressor", true)) {
      hub.enableCompressorAnalog(PneumaticSystem.MinimumPressure, PneumaticSystem.MaximumPressure);
    }
    else{
      hub.disableCompressor();
    }
  }

  //#region Solenoid Methods
  public void lowerIntake(){
    intakeSolenoid.set(true);
  }

  public void raiseIntake(){
    intakeSolenoid.set(false);
  }

  public void toggleIntake(){
    intakeSolenoid.toggle();
  }

  public void setClimbClosed(){
    climbSolenoid.set(true);
  }

  public void setClimbOpen(){
    climbSolenoid.set(false);
  }

  public void toggleClimb(){
    climbSolenoid.toggle();
  }

  public boolean getIntakeSolenoidStatus(){
    return intakeSolenoid.get();
  }

  public boolean getClimbSolenoidStatus(){
    return climbSolenoid.get();
  }
  //#endregion
}
