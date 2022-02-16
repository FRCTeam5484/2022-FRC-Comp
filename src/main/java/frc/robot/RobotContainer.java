package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Autonomous_TwoBallShootHighLeft;
import frc.robot.commands.Autonomous_TwoBallShootHighRight;
import frc.robot.commands.Autonomous_MoveOffLine;
import frc.robot.commands.cmdIntake_Lower;
import frc.robot.commands.cmdIntake_Raise;
import frc.robot.commands.cmdShooter_HighGoalSpeed;
import frc.robot.commands.cmdShooter_LowGoalSpeed;
import frc.robot.subsystems.subFeedSystem;
import frc.robot.subsystems.subClimbSystem;
import frc.robot.subsystems.subDriveSystem;
import frc.robot.subsystems.subIntakeSystem;
import frc.robot.subsystems.subLimeLight;
import frc.robot.subsystems.subPneumaticSystem;
import frc.robot.subsystems.subPowerDistributionSystem;
import frc.robot.subsystems.subShootSystem;

public class RobotContainer {
  SendableChooser<Command> autoChooser = new SendableChooser<>();

  // Controllers
  public final XboxController driverOne = new XboxController(Constants.DriveControllers.DriverOne);
  public final XboxController driverTwo = new XboxController(Constants.DriveControllers.DriverTwo);

  // Driver One Buttons
  Trigger driverOne_RightTrigger = new Trigger(() -> driverOne.getRightTriggerAxis() > 0.1);
  Trigger driverOne_LeftTrigger = new Trigger(() -> driverOne.getLeftTriggerAxis() > 0.1);
  JoystickButton driverOne_A = new JoystickButton(driverOne, Button.kA.value);
  JoystickButton driverOne_B = new JoystickButton(driverOne, Button.kB.value);
  JoystickButton driverOne_X = new JoystickButton(driverOne, Button.kX.value);
  JoystickButton driverOne_Y = new JoystickButton(driverOne, Button.kY.value);
  JoystickButton driverOne_Start = new JoystickButton(driverOne, Button.kStart.value);
  JoystickButton driverOne_Back = new JoystickButton(driverOne, Button.kBack.value);
  JoystickButton driverOne_LeftBumper = new JoystickButton(driverOne, Button.kLeftBumper.value);
  JoystickButton driverOne_RightBumper = new JoystickButton(driverOne, Button.kRightBumper.value);
  JoystickButton driverOne_LeftStick = new JoystickButton(driverOne, Button.kLeftStick.value);
  JoystickButton driverOne_RightStick = new JoystickButton(driverOne, Button.kRightStick.value);

  // Driver Two Buttons
  Trigger driverTwo_RightTrigger = new Trigger(() -> driverTwo.getRightTriggerAxis() > 0.1);
  Trigger driverTwo_LeftTrigger = new Trigger(() -> driverTwo.getLeftTriggerAxis() > 0.1);
  JoystickButton driverTwo_A = new JoystickButton(driverTwo, Button.kA.value);
  JoystickButton driverTwo_B = new JoystickButton(driverTwo, Button.kB.value);
  JoystickButton driverTwo_X = new JoystickButton(driverTwo, Button.kX.value);
  JoystickButton driverTwo_Y = new JoystickButton(driverTwo, Button.kY.value);
  JoystickButton driverTwo_Start = new JoystickButton(driverTwo, Button.kStart.value);
  JoystickButton driverTwo_Back = new JoystickButton(driverTwo, Button.kBack.value);
  JoystickButton driverTwo_LeftBumper = new JoystickButton(driverTwo, Button.kLeftBumper.value);
  JoystickButton driverTwo_RightBumper = new JoystickButton(driverTwo, Button.kRightBumper.value);
  JoystickButton driverTwo_LeftStick = new JoystickButton(driverTwo, Button.kLeftStick.value);
  JoystickButton driverTwo_RightStick = new JoystickButton(driverTwo, Button.kRightStick.value);

  // SubSystems
  public final subLimeLight lime = new subLimeLight();
  public final subDriveSystem drive = new subDriveSystem(lime);
  public final subIntakeSystem intake = new subIntakeSystem();
  public final subClimbSystem climb = new subClimbSystem();
  public final subPneumaticSystem air = new subPneumaticSystem();
  public final subShootSystem shoot = new subShootSystem();
  public final subFeedSystem feed = new subFeedSystem();
  public final subPowerDistributionSystem power = new subPowerDistributionSystem();

  public RobotContainer() {
    AddAutoCommands();
    DriverOneFunctions();
    DriverTwoFunctions();
  }

  private void AddAutoCommands(){
    autoChooser.setDefaultOption("Move Off Line", new Autonomous_MoveOffLine(drive));  
    autoChooser.addOption("Two Ball Shoot High - DS Left", new Autonomous_TwoBallShootHighLeft(drive, feed, intake, air, shoot));
    autoChooser.addOption("Two Ball Shoot High - DS Right", new Autonomous_TwoBallShootHighRight(drive, feed, intake, air, shoot));
    SmartDashboard.putData("Autonomous", autoChooser);
  }

  private void DriverOneFunctions() {    
    drive.setDefaultCommand(new RunCommand(() -> drive.TeleOp(driverOne), drive));
    
    driverOne_X
      .whenPressed(new InstantCommand(() -> drive.setDriveLocked(), drive))
      .whenReleased(new InstantCommand(() -> drive.setDriveUnlocked(), drive));

    driverOne_LeftBumper
      .whenPressed(new InstantCommand(() -> air.toggleIntake(), air));
    
    driverOne_RightBumper
      .whenPressed(new InstantCommand(() -> air.toggleClimb(), air));    

    driverOne_LeftTrigger
      .whenActive(new cmdIntake_Lower(air, intake))
      .whenInactive(new cmdIntake_Raise(air, intake));

    driverOne_RightTrigger
      .whenActive(new InstantCommand(() -> intake.runIntakeReverse(), intake))
      .whenInactive(new InstantCommand(() -> intake.stopIntake(), intake));
    } 

  private void DriverTwoFunctions() {   
    climb.setDefaultCommand(new RunCommand(() -> climb.TeleOp(driverTwo, air.getClimbSolenoidStatus()), climb));
    //shoot.setDefaultCommand(new RunCommand(() -> shoot.TeleOp(driverTwo), shoot));

    driverTwo_Y
      .whenPressed(new cmdShooter_HighGoalSpeed(shoot))  
      //.whenPressed(new RunCommand(() -> shoot.setShooterSpeed(0.55), shoot))
      .whenReleased(new InstantCommand(() -> shoot.stopShooter(), shoot));
    
    driverTwo_A
      .whenPressed(new cmdShooter_LowGoalSpeed(shoot))
      //.whenPressed(new RunCommand(() -> shoot.setShooterSpeed(0.3), shoot))
      .whenReleased(new InstantCommand(() -> shoot.stopShooter(), shoot));

    driverTwo_RightBumper
      .whenPressed(new RunCommand(() -> feed.runFeed(), feed))
      .whenReleased(new InstantCommand(() -> feed.stopFeed(), feed));    

    driverTwo_LeftBumper
      .whenPressed(new RunCommand(() -> feed.reverseFeed(), feed))
      .whenReleased(new InstantCommand(() -> feed.stopFeed(), feed));    
  }
  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}