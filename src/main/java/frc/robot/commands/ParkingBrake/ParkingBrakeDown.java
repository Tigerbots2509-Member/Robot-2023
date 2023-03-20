// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ParkingBrake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class ParkingBrakeDown extends CommandBase {
  /** Creates a new GrabClose. */
  private Drivetrain Dt;

  public ParkingBrakeDown(Drivetrain pDrivetrain) {

    Dt = pDrivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Dt.ParkingBrakeDown();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}