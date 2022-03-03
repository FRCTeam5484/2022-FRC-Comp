package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PneumaticSystem;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class subPneumaticSystem extends SubsystemBase {
  private PneumaticHub m_ph = new PneumaticHub(1);
  private Solenoid intakeSolenoid = new Solenoid(PneumaticsModuleType.REVPH, PneumaticSystem.IntakeSolenoid);
  private Solenoid climbSolenoid = new Solenoid(PneumaticsModuleType.REVPH, PneumaticSystem.ClimbSolenoid);

  public subPneumaticSystem() {
    lowerClimb();
    raiseIntake();

  }

  @Override
  public void periodic() {
    m_ph.enableCompressorDigital();
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

  public void raiseClimb(){
    climbSolenoid.set(true);
  }

  public void lowerClimb(){
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
