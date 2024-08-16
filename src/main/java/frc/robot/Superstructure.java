// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.subsystems.Feeder;

/** Add your docs here. */
public class Superstructure {
    // Subsystems
    private final Intake intake_;
    private final Shooter shooter_;
    private final Feeder feeder_;

    // Constructor
    public Superstructure(Intake intake, Shooter shooter, Feeder feeder) {
        intake_ = intake;
        shooter_ = shooter;
        feeder_ = feeder;
    }

    public void periodic() {

    }

    //Intake Setter
    public Command setIntake(double percent) {
        return new StartEndCommand(
            () -> intake_.setPercent(percent), 
            () -> intake_.stopMotor(), 
            intake_);
    }

    // Shooter Setter
    public Command setShooter(double percent) {
        return new StartEndCommand(
            () -> shooter_.setPercent(percent), 
            () -> shooter_.stopMotor(), 
            shooter_);
    }

    // Feeder Setter
    public Command setFeeder(double percent) {
        return new StartEndCommand(
            () -> feeder_.setPercent(percent),
            () -> feeder_.stopMotor(),
            feeder_);
    }
    
}
