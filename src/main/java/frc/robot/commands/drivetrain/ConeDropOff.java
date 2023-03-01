// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision.LimeLight;

public class ConeDropOff extends CommandBase {
  private final Drivetrain m_Drivetrain;
  private LimeLight m_Vision;
  private double X;
  private Joystick stick;

  /** Creates a new DriveTele. */
  public ConeDropOff(Joystick pstick, Drivetrain subsystem, LimeLight pLimeLight) {
    m_Drivetrain = subsystem;
    m_Vision = pLimeLight;
    stick = pstick;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Drivetrain);
    addRequirements(m_Vision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_Vision.getTargetX() < -2) {X = (m_Vision.getTargetX() * .014) - .15;}
    else if (m_Vision.getTargetX() > 2) {X = (m_Vision.getTargetX() * .014) + .15;}
    else {X = 0;}

    SmartDashboard.putNumber("x", m_Vision.getTargetX());

    m_Drivetrain.TeleMecDrive(
      stick.getY(),
      -X,
      stick.getZ());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.TeleMecDrive(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}