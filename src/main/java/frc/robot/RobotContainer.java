// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Autonomous;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    /* 
    // The robot's subsystems and commands are defined here...
    private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

    // Replace with CommandPS4Controller or CommandJoystick if needed
    private final CommandXboxController m_driverController =
        new CommandXboxController(OperatorConstants.kDriverControllerPort);*/

    private Joystick m_stick1 = new Joystick(0);
    // private Joystick m_stick2 = new Joystick(1);

    private Drivetrain m_drivetrain;
    // Propeller m_propeller;

    private Autonomous m_autonomousCommand = new Autonomous(m_drivetrain);

    private double speed = 0;

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Initialize all of your commands and subsystems here
        // ((-m_stick2.getThrottle() + 2) / 3)

        m_drivetrain.setDefaultCommand(
            new TankDrive(
                () -> {
                    return (
                        (
                            (m_stick1.getY()
                                * (
                                    1 - 0.75 * Math.abs(
                                        m_stick1.getZ()
                                            * (m_stick1.getRawButton(1) ? 0 : 1)
                                    )
                                )
                            + m_stick1.getZ()
                                * 0.75
                                * (m_stick1.getRawButton(1) ? 0 : 1)
                            )
                        ) * ((-m_stick1.getThrottle() + 2) / 3)
                    );
                },
                () -> {
                    return (
                        (
                            (
                                m_stick1.getY()
                                    * (
                                        1 - 0.75 * Math.abs(
                                            m_stick1.getZ()
                                                * (m_stick1.getRawButton(1) ? 0 : 1)
                                        )
                                    )
                                - m_stick1.getZ()
                                    * 0.75
                                    * (m_stick1.getRawButton(1) ? 0 : 1)
                            )
                        ) * ((-m_stick1.getThrottle() + 2) / 3)
                    );
                },
                m_drivetrain
            )
        );

        // Configure the trigger bindings
        configureBindings();
        
        // if (m_stick2.GetRawButton(3)){
        //     speed += 0.01;
        // }
        // if (m_stick2.GetRawButton(5)){
        //     speed -= 0.01;
        // }
    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
     * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        // frc2::JoystickButton(&m_stick2,2).WhenHeld(
        //     SpinPropeller(m_propeller)
        // );
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An example command will be run in autonomous
        return m_autonomousCommand;
    }
}