// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private final CANSparkMax leader_left;
  private final CANSparkMax leader_right;

  private final PeriodicIO io_ = new PeriodicIO();

  private final PIDController controller_ = new PIDController(Constants.kP, 0, 0);
  /** Creates a new Intake. */
  public Intake() {

    // Initialize motor controllers 
    leader_left = new CANSparkMax(1, MotorType.kBrushless);
    leader_left.restoreFactoryDefaults();
    leader_left.setInverted(true);
    leader_left.setIdleMode(CANSparkMax.IdleMode.kBrake);



    leader_right = new CANSparkMax(2, MotorType.kBrushless);
    leader_right.restoreFactoryDefaults();
    leader_right.setInverted(false);
    leader_right.setIdleMode(CANSparkMax.IdleMode.kBrake);
    leader_right.follow(leader_left);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    io_.current_left_ = leader_left.getOutputCurrent();
    io_.current_right_ = leader_right.getOutputCurrent();
    leader_left.set(io_.left_demand);
    leader_right.set(io_.right_demand);
  }

  public void setPercent(double value) {
    io_.left_demand = value;
    io_.right_demand = value - 0.1;
  }

  public void stopMotor() {
    io_.left_demand = 0;
    io_.right_demand = 0;
  }

  public double getLeftIntakePercent() {
    return io_.left_demand;
  }

  public double getRightIntakePercent() {
    return io_.right_demand;
  }
  // IO
  public static class PeriodicIO {
    // Input
    double current_left_;
    double current_right_;

    // Output
    double left_demand;
    double right_demand;
  }

  // Constants
  public static class Constants {
    // Motor Controllers
    public static final int kLeaderLeftId = 12;
    public static final int kLeaderRightId = 11;

    // PID Constants
    public static final double kP = 0.8;
  }
}
