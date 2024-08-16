// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  private CANSparkMax left_leader;
  private CANSparkMax left_follower1;
  private CANSparkMax right_leader;
  private CANSparkMax right_follower1;
  private final DifferentialDrive m_myRobot;

  
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    left_leader = new CANSparkMax(1, MotorType.kBrushless);
    left_leader.restoreFactoryDefaults();

    left_follower1 = new CANSparkMax(2, MotorType.kBrushless);
    left_follower1.restoreFactoryDefaults();
    left_follower1.follow(left_leader);
    
    

    right_leader = new CANSparkMax(3, MotorType.kBrushless);
    right_leader.restoreFactoryDefaults();

    right_follower1 = new CANSparkMax(4, MotorType.kBrushless);
    right_follower1.restoreFactoryDefaults();
    right_follower1.follow(right_leader);

    m_myRobot = new DifferentialDrive(left_leader, right_leader);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }

  public void drive(double speed, double rotation) {
    m_myRobot.arcadeDrive(speed, rotation);
  }

  public void stopMotor() {
    m_myRobot.arcadeDrive(0,0);
  }

  
}
