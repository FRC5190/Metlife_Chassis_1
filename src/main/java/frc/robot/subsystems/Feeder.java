// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Feeder extends SubsystemBase {
  private final CANSparkMax leader_;

  private final PeriodicIO io_ = new PeriodicIO();
  /** Creates a new Feeder. */
  public Feeder() {
    leader_ = new CANSparkMax(Constants.kFeederId, MotorType.kBrushless);
    leader_.restoreFactoryDefaults();
    leader_.setInverted(false);
    leader_.setIdleMode(CANSparkMax.IdleMode.kCoast);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    leader_.set(io_.demand);
  }

  public void setPercent(double value) {
    io_.demand = value;
  }

  public void stopMotor() {
    io_.demand = 0;
  }

  public double getPercent() {
    return leader_.get();
  }
  // IO
  public static class PeriodicIO {
    public double demand;
  }

  public static class Constants {
    public static final int kFeederId = 3;
  }
}
