package frc.robot;

public final class Constants {
    private final static class HardwareId{
        // Controllers
        private final static int DriverOneId = 0;
        private final static int DriverTwoId = 1;
        
        // SparkMax
        private final static int DriveRightMaster = 3;
        private final static int DriveRightSlave = 4;
        private final static int DriveLeftMaster = 1;
        private final static int DriveLeftSlave = 2;
        private final static int FeedMotor = 5;
        private final static int IntakeMotor = 7;
        private final static int ClimbMotor = 6;
        private final static int ShooterMotor = 8;

        // Pneumatic 
        private final static int pneumaticHubId = 1;
        private final static int intakeSolendoidId = 1;
        private final static int climbSolendoidId = 0;

        //Power System
        private final static int pdhId = 1;
        private final static int leftDriveMasterPowerPortId = 18;
        private final static int leftDriveFollowerPowerPortId = 19;
        private final static int rightDriveMasterPowerPortId = 0;
        private final static int rightDriveFollowerPowerPortId = 1;
        private final static int shooterPowerPortId = 2;
        private final static int feedPowerPortId = 3;
        private final static int climbPowerPortId = 4;
        private final static int intakePowerPortId = 17;
        private final static int roboRioPowerPortId = 20;
        private final static int phPowerPortId = 21;
        private final static int radioPowerPortId = 22;
        private final static int limeLightPowerPortId = 23;
    }

    public final static class DriveControllers {
        public final static int DriverOne = HardwareId.DriverOneId;
        public final static int DriverTwo = HardwareId.DriverTwoId;
    }

    public final static class DriveSystem {
        // Ids
        public final static int RightMasterMotorId = HardwareId.DriveRightMaster;
        public final static int RightSlaveMotorId = HardwareId.DriveRightSlave;
        public final static int LeftMasterMotorId = HardwareId.DriveLeftMaster;
        public final static int LeftSlaveMotorId = HardwareId.DriveLeftSlave;        
        // Inversion
        public final static boolean LeftDriveInverted = false;
        public final static boolean RightDriveInverted = true;
        // Speed
        public final static double ManualSpeedFactor = 0.95;
        public final static double AutoMaxSpeed = 0.70;
        public final static double AutoMinSpeed = -0.70;
        public final static double AutoTurnMaxSpeed = 0.70;
        // PID Turn Values
        public static final double TurnPValue = 0.06;
        public static final double TurnIValue = 0;
        public static final double TurnDValue = 0.0033;
        public static final double TurnToleranceDeg = 3.5;
        public static final double MaxTurnRateDegPerSec = 100;
        public static final double TurnRateToleranceDegPerSec = 10;
        // PID Drive Values
        public static final double DrivePValue = 0.20;
        public static final double DriveIValue = 0;
        public static final double DriveDValue = 0;
        public static final double DriveToleranceDis = 1;
        
        public static final double IZValue = 0;
        public static final double FFalue = 0.1;
        
        public static final double MaxTurnAccelerationDegPerSecSquared = 300;
        public static final boolean GyroReversed = false;
        public static final double EncoderTickToInch = 0.48;
    }

    public final static class IntakeSystem {
        // Ids
        public final static int IntakeId = HardwareId.IntakeMotor;
        // Inversion
        public final static boolean IntakeInverted = true;
        // Speed
        public final static double IntakeSpeed = 1;
    }
    
    public final static class ShooterSystem {
        // Ids
        public final static int FeedId = HardwareId.FeedMotor;
        public final static int ShooterId = HardwareId.ShooterMotor;
        // Inversion
        public final static boolean BallFeedInverted = false;
        public final static boolean ShooterInverted = true;
        // Speed
        public final static double BallFeedSpeed = 0.5;
        public final static double ShooterLowGoalVelocity = 2000;
        public final static double ShooterHighGoalVelocity = 4000;
        public static final double VelocityTolerance = 200;
        // PID Shooter Values
        public static final double PValue = 0.20;
        public static final double IValue = 0;
        public static final double DValue = 0;
    }

    public final static class ClimbSystem {
        // Ids
        public final static int ClimbId = HardwareId.ClimbMotor;
        // Inversion
        public final static boolean ClimbInverted = true;
        // Speed
        public final static double ClimbSpeed = 1;
        // Limits
        //public final static int ClimbEncoderTopLimitWhenDown = 151;
        //public final static int ClimbEncoderTopLimitWhenUp = 155;
        public final static int ClimbEncoderTopLimit = 148;
        public final static int ClimbEncoderBottomLimit = -14;
    }

    public final static class PneumaticSystem {
        // Ids
        public final static int PneumaticHubId = HardwareId.pneumaticHubId;
        public final static int IntakeSolenoid = HardwareId.intakeSolendoidId;
        public final static int ClimbSolenoid = HardwareId.climbSolendoidId;
        // Pressure
        public final static double MinimumPressure = 100.0;
        public final static double MaximumPressure = 120.0;
    }

    public final static class PowerSystem {
        // Ids
        public final static int pdhId = HardwareId.pdhId;
        public final static int leftDriveMasterId = HardwareId.leftDriveMasterPowerPortId;
        public final static int leftDriveFollowerId = HardwareId.leftDriveFollowerPowerPortId;
        public final static int rightDriveMasterId = HardwareId.rightDriveMasterPowerPortId;
        public final static int rightDriveFollowerId = HardwareId.rightDriveFollowerPowerPortId;
        public final static int shooterId = HardwareId.shooterPowerPortId;
        public final static int feedId = HardwareId.feedPowerPortId;
        public final static int climbId = HardwareId.climbPowerPortId;
        public final static int intakeId = HardwareId.intakePowerPortId;
        public final static int roboRioId = HardwareId.roboRioPowerPortId;
        public final static int phId = HardwareId.phPowerPortId;
        public final static int radioId = HardwareId.radioPowerPortId;
        public final static int limeLightId = HardwareId.limeLightPowerPortId;
    }
}