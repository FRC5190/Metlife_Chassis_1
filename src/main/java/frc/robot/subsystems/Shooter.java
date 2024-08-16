// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  // Motor Controllers
  private final CANSparkMax left_leader_;
  private final CANSparkMax right_leader_;

  //io
  private final PeriodicIO io_ = new PeriodicIO();
  /** Creates a new Shooter. */
  public Shooter() {
    // Initialize motor controllers
    left_leader_ = new CANSparkMax(Constants.kLeaderLeftId, MotorType.kBrushless);
    left_leader_.restoreFactoryDefaults();
    left_leader_.setInverted(false);
    left_leader_.setIdleMode(CANSparkMax.IdleMode.kCoast);
    left_leader_.enableVoltageCompensation(12.0);

    right_leader_ = new CANSparkMax(Constants.kRightLeaderId, MotorType.kBrushless);
    right_leader_.restoreFactoryDefaults();
    right_leader_.setInverted(false);
    right_leader_.setIdleMode(CANSparkMax.IdleMode.kCoast);
    right_leader_.enableVoltageCompensation(12.0);

    // Safety
    left_leader_.setSmartCurrentLimit((int) Constants.kCurrentLimit);
    right_leader_.setSmartCurrentLimit((int) Constants.kCurrentLimit);
  }

  public double getPercent() {
    return io_.demand;
  }

  public void setPercent(double value) {
    io_.demand = value;
  }

  public void stopMotor() {
    io_.demand = 0;
  }

  @Override
  public void periodic() {
    // read inputs

    io_.left_leader_supply_current = left_leader_.getOutputCurrent();
    io_.right_leader_supply_current = right_leader_.getOutputCurrent();

    left_leader_.set(io_.demand);
    right_leader_.set(io_.demand);
  }

  public static class PeriodicIO {
    // Inputs
    double left_leader_supply_current;
    double right_leader_supply_current;

    // Outputs
    double demand;
  }

  public static class Constants {
    // Motor Controllers
    public static final int kLeaderLeftId = 14;
    public static final int kRightLeaderId = 13;

    // PID
    public static final double kP = 2.8;

    // Current Limit
    public static final double kCurrentLimit = 40;
  }
}
