/*
 * Copyright 2026 FRCSoftware
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package first.robot.opmode;

import org.wpilib.command3.Trigger;
import org.wpilib.command3.button.CommandXboxController;
import org.wpilib.opmode.PeriodicOpMode;
import org.wpilib.opmode.Teleop;

import first.robot.Robot;

@Teleop
public class MyTeleop extends PeriodicOpMode {
  private final Robot robot;
  private final CommandXboxController xbox = new CommandXboxController(0);

  /** The Robot instance is passed into the opmode via the constructor. */
  public MyTeleop(Robot robot) {
    this.robot = robot;

    Trigger leftBumperTrigger = xbox.leftBumper()
      .whileTrue(robot.intakeLauncher.intake())
      .whileTrue(robot.feeder.intake());
    Trigger rightBumperTrigger = xbox.rightBumper()
      .whileTrue(robot.intakeLauncher.shoot())
      .whileTrue(robot.feeder.feed());
    Trigger aButtonTrigger = xbox.a()
      .whileTrue(robot.intakeLauncher.outtake())
      .whileTrue(robot.feeder.outtake());
  }

  @Override
  public void periodic() {
    /* Called periodically (set time interval) while the robot is enabled. */
  }
}
