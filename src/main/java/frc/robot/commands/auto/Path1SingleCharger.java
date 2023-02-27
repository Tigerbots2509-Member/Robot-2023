// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.drivetrain.DriveToPosition;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Path1SingleCharger extends InstantCommand {
  private Command blueCommandSequence;
  private Command redCommandSequence;

  /** Creates a new LeftSingleCharger. */
  public Path1SingleCharger(Drivetrain sDrivetrain) {

    blueCommandSequence =
        Commands.sequence(
            sDrivetrain.setCurrentPose(new Pose2d(1.84, 0.49, new Rotation2d())),
            new DriveToPosition(sDrivetrain, new Pose2d(2.22, 2.54, new Rotation2d())),
            new DriveToPosition(sDrivetrain, new Pose2d(3.84, 2.47, new Rotation2d())));
    redCommandSequence =
        Commands.sequence(
            sDrivetrain.setCurrentPose(new Pose2d(14.76, 0.39, new Rotation2d())),
            new DriveToPosition(sDrivetrain, new Pose2d(14.36, 2.61, new Rotation2d())),
            new DriveToPosition(sDrivetrain, new Pose2d(12.72, 2.74, new Rotation2d())));
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (DriverStation.getAlliance() == Alliance.Blue) {
      blueCommandSequence.schedule();
    } else {
      redCommandSequence.schedule();
    }
  }
} // Drop sequence 3

// Move grabber to opposite side

// Drive forward and strafe left

// Grab cube

// Stafe left

// Drive back

// Deploy compressor

// Right single charger

      // Drop sequence 3

      // Move grabber to opposite side

      // Drive forward and strafe left

      // Grab cube

      // Strafe left

      // Drive back

      // Deploy compressor
