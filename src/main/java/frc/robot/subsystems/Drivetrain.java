// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveWheelPositions;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.opConstants;

public class Drivetrain extends SubsystemBase {

  // Motors & Drivetrain
  private WPI_TalonFX frontRightMotor = new WPI_TalonFX(opConstants.kFrontRightID);
  private WPI_TalonFX frontLeftMotor = new WPI_TalonFX(opConstants.kFrontLeftID);
  private WPI_TalonFX rearRightMotor = new WPI_TalonFX(opConstants.kRearRightID);
  private WPI_TalonFX rearLeftMotor = new WPI_TalonFX(opConstants.kRearLeftID);
  private MecanumDrive drive =
      new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

  // Nav-X
  private AHRS navx = new AHRS();

  // Kinematics

  // The locations for the wheels must be relative to the center of the robot.
  // Positive x values represent moving toward the front of the robot whereas
  // positive y values represent moving toward the left of the robot.
  private Translation2d frontLeftTranslate = new Translation2d(0.381, 0.381);
  private Translation2d frontRightTranslate = new Translation2d(0.381, -0.381);
  private Translation2d rearLeftTranslate = new Translation2d(-0.381, 0.381);
  private Translation2d rearRightTranslate = new Translation2d(-0.381, -0.381);

  // Creating my kinematics object using the wheel locations.
  private MecanumDriveKinematics kinematics =
      new MecanumDriveKinematics(
          frontLeftTranslate, frontRightTranslate, rearLeftTranslate, rearRightTranslate);
  // Creating my odometry object from the kinematics object and the initial wheel
  // positions. Here, our starting pose is 5 meters along the long end of the
  // field and in
  // the center of the field along the short end, facing the opposing alliance
  // wall.
  private MecanumDriveOdometry odometry;
  private Pose2d robotPose;
  private Field2d field = new Field2d();

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    this.setName("Drivetrain");
    this.addChild("Mecanum Drive", drive);

    // Inverts all the motors that need to be inverted
    frontRightMotor.setInverted(false);
    frontLeftMotor.setInverted(true);
    rearRightMotor.setInverted(false);
    rearLeftMotor.setInverted(true);

    // Sets the motors to break when stopped
    frontRightMotor.setNeutralMode(NeutralMode.Brake);
    frontLeftMotor.setNeutralMode(NeutralMode.Brake);
    rearRightMotor.setNeutralMode(NeutralMode.Brake);
    rearLeftMotor.setNeutralMode(NeutralMode.Brake);

    navx.reset();

    // Setup Odeometry
    robotPose = new Pose2d(13.5, 5.0, new Rotation2d()); // Inital pose of the robot
    odometry =
        new MecanumDriveOdometry(kinematics, navx.getRotation2d(), getWheelPositions(), robotPose);
    SmartDashboard.putData("Field", field);
    SmartDashboard.putNumber("Odom X", robotPose.getX());
    SmartDashboard.putNumber("Odom Y", robotPose.getY());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // Get my wheel positions
    MecanumDriveWheelPositions wheelPositions = getWheelPositions();
    // Get the rotation of the robot from the gyro.
    Rotation2d gyroAngle = navx.getRotation2d();
    // Update the pose
    robotPose = odometry.update(gyroAngle, wheelPositions);

    // Display Telemetry
    field.setRobotPose(odometry.getPoseMeters());
    SmartDashboard.putNumber("Yaw", navx.getYaw());
    SmartDashboard.putNumber("Roll", navx.getRoll());
    SmartDashboard.putNumber("Pitch", navx.getPitch());
    SmartDashboard.updateValues();
  }

  /**
   * Returns the current heading of the robot.
   *
   * @return value from -180 to 180 degrees.
   */
  public double getAngle() {
    return navx.getYaw();
  }

  public void arcadeDrive(double forward, double rotation) {
    drive.driveCartesian(0, forward, rotation);
  }

  public void mecanumDrive(double x, double y, double z) {
    drive.driveCartesian(x, y, z, Rotation2d.fromDegrees(getAngle()));
  }

  public void stopDrive() {
    drive.stopMotor();
  }

  /**
   * Calculates the distance the wheel has traveled.
   *
   * @param motor
   * @return The distance in meters
   */
  public double getWheelDistance(WPI_TalonFX motor) {
    double rawValue = motor.getSelectedSensorPosition();
    double distance =
        (rawValue / opConstants.kFalconUnitsPerRotation) * opConstants.kWheelDiameter * Math.PI;
    return distance / 100.0;
  }

  /**
   * Constructs a MecanumDriveWheelPosisitons object for the drivetrain.
   *
   * @return MecanumDriveWheelPosisitons
   */
  public MecanumDriveWheelPositions getWheelPositions() {
    double fLeftVal, fRightVal, rLeftVal, rRightVal;

    fLeftVal = getWheelDistance(this.frontLeftMotor);
    fRightVal = getWheelDistance(this.frontRightMotor);
    rLeftVal = getWheelDistance(this.rearLeftMotor);
    rRightVal = getWheelDistance(this.rearRightMotor);

    return new MecanumDriveWheelPositions(fLeftVal, fRightVal, rLeftVal, rRightVal);
  }
}
