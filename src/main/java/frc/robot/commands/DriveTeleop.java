// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class DriveTeleop extends Command {
  private DriveTrain drivetrain_;
  private final CommandXboxController controller_;
  
  /** Creates a new DriveTeleop. */
  public DriveTeleop(DriveTrain drivetrain, CommandXboxController controller) {
    drivetrain_ = drivetrain;
    controller_ = controller;

    
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain_);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = controller_.getLeftY() * Constants.kTranslationMultiplier;
    double rotation = controller_.getRightX() * Constants.kTranslationMultiplier;
    drivetrain_.drive(speed, rotation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public static class Constants {
    // Percent -> Speed
    public static  double kTranslationMultiplier = 0.90;
    public static double kRotationMultiplier = 0.90;
  }
}
