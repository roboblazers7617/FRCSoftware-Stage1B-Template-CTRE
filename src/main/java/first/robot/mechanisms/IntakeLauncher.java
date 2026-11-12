package first.robot.mechanisms;

import static org.wpilib.units.Units.Seconds;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;
import org.wpilib.framework.RobotBase;
import org.wpilib.hardware.bus.CANPort;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.TalonFX;

import first.robot.simulation.SingleFlywheelSim;

public class IntakeLauncher implements Mechanism {
    private final TalonFX motor = new TalonFX(4, new CANBus(CANPort.CAN_S0));

    public IntakeLauncher() {
        setDefaultCommand(idle());
    }

    public Command idle() {
        return run(coroutine -> {
            motor.setThrottle(0);
        }).named("Idle");
    }
    public Command shoot() {
        return run(coroutine -> {
            coroutine.wait(Seconds.of(2));
            motor.setThrottle(0.9);
        }).named("Shoot");
    }
    public Command intake() {
        return run(coroutine -> {
            motor.setThrottle(0.8);
        }).named("Intake");
    }
    public Command outtake() {
        return run(coroutine -> {
            motor.setThrottle(-0.8);
        }).named("Outtake");
    }

    private final SingleFlywheelSim sim = SingleFlywheelSim.forIntakeLauncher(motor);
    public void periodic() {
        if (RobotBase.isSimulation()) {
            sim.periodic();
        }
    }

}
