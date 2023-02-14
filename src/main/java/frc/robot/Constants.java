// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  // Joystick ID's
  public static final int kOpStickID = 0;
  public static final int kCoopStickID = 1;

  public static final class opConstants {

    // Drivetrain Constants
    public static final int kFrontLeftID = 14;
    public static final int kFrontRightID = 1;
    public static final int kRearLeftID = 15;
    public static final int kRearRightID = 0;
    public static final double kMaxSpeed = 0.8;
    public static final double kMaxAngularSpeed = 0.8;
    public static final double kHighSpeedStrafe = 1;
    public static final double kLowSpeedStrafe = .6;
    public static final double kSkewRateLimit = 0.8;
    public static final double kGearRatio = 12.0;
    public static final int kFalconUnitsPerRotation = 2048;
    public static final double kWheelDiameter = 15.24; // This is in centimeters.
    public static final int kParkingBrakeExtend = 2;
    public static final int kParkingBrakeRetract = 3;

    // Travelator Constants
    public static final int kTravelatorID = 13;
    public static final double kTravelatorSpeed = .75;
    public static final int kBackRightLimitSwitchID = 0;
    public static final int kBackLeftLimitSwitchID = 1;
    public static final int kFrontRightLimitSwitchID = 2;
    public static final int kFrontLeftLimitSwitchID = 3;

    // Arm Constants
    public final static int kArmMotor1ID = 3;
    public final static int kArmMotor2ID = 2;
    public final static int kArmMotor3ID = 0;
    public final static double kMaxArm1Speed = .3;
    public final static double kMaxArm2Speed = .6;
    public final static int kGrabberP1 = 0;
    public final static int kGrabberP2 = 1;
    public final static int kArmCounterID = 0;
  }

  public static final class camConstants {

    // Network
    public static final String kLimelightIP = "10.25.9.11"; // IP Address of Camera
    public static final String kLimelightNetworkID = "limelight"; // Name of Camera on Network

    // Settings
    // Sets LED. 0 = Set by Pipline, 1 = Force off, 2 = Force blink, 3 = Force on
    public static final int kLimelightLED = 0;

    // Sets camera mode. 0 = Vision processor, 1 = Driver Camera
    public static final int kLimelightMode = 0;

    // Sets streaming mode. 0 = Side-by-Side, 1 = PiP main, 2 = PiP secondary
    public static final int kLimelightStream = 0;

    // The default pipeline to stream
    public static final int kLimelightStartingPipeline = 1;

    // Pipeline latency in milliseconds
    public static final String kLimelightLatencyID = "tl";

    // Whether or not a valid target is found (0 or 1)
    public static final String kLimelightTargetID = "tv";

    // Horizontal offset from crosshair to target (+/- 27 degrees)
    public static final String kLimelightTargetXID = "tx";

    // Vertical offset from crosshair to target (+/- 20.5 degrees)
    public static final String kLimelightTargetYID = "ty";

    // Target area (0-100 % of image)
    public static final String kLimelightTargetAreaID = "ta";

    // Target skew/rotation (-90 to 0 degrees)
    public static final String kLimelightTargetSkewID = "ts";

    // Vertical sidelength of bounding box (0-320 pixels)
    public static final String kLimelightTargetVertID = "tvert";

    // Horizontal sidelength of bounding box (0-320 pixels)
    public static final String kLimelightTargetHorID = "thor";

    // Camera Variables
    public static final double kCameraHeight = 4;
    public static final double kCameraAngle = -31.47286489; // -28.23744554
    public static final double kTargetHeight = 31.5;
    public static final double kpAim = -0.02;
    public static final double kpDistance = -0.05;
    public static final double kmin_aim_command = -0.5;
    public static final double kdistance_command = -0.5;
    public static final double kTargetDistanceFromTarget = 24;

    // Constants for April Tags using PhotonVision below
    private static final double kFieldLength = Units.inchesToMeters(651.2);
    private static final double kFieldWidth = Units.inchesToMeters(315.75);

    // needs to be replaced with what's in http://photonvision:5800 settings
    public static final double camFOV = 0;
    public static final int camResWidth = 0;
    public static final int camResHeight = 0;
    // Set this to what we plan on using, probably should play around with a good distance.
    public static final double minClosestTargetDistance = 0; // Goes by meters

    public static final AprilTagFieldLayout tagPlayground =
        new AprilTagFieldLayout(null, kFieldLength, kFieldWidth);

    public static Transform3d robotToCamera =
        new Transform3d(new Translation3d(0, 0, 0), new Rotation3d(0, 0, 0));
  }
}
