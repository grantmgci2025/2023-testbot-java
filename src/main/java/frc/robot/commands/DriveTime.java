package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveTime extends CommandBase {
    private double m_endTime;
    private final double m_speed;
    private final Drivetrain m_drivetrain;

    private double m_distanceCounter;

    public DriveTime(double ms, double speed, Drivetrain drivetrain) {
        m_endTime = ms;
        m_speed = speed;
        m_drivetrain = drivetrain;
        
        setName("DriveTime");
        addRequirements(m_drivetrain);
    }

    @Override
    public void initialize() {
        m_drivetrain.drive(0, 0);
        // m_drivetrain.m_leftLeadEncoder.setPosition(0);
        // m_drivetrain.m_rightLeadEncoder.setPosition(0);
        //m_drivetrain.m_Encoder.setPosition(0);
        m_endTime += System.currentTimeMillis();
    }

    @Override
    public void execute() {
        m_drivetrain.drive(m_speed, m_speed);
    }

    @Override
    public boolean isFinished() {
        // m_distanceCounter = (- m_drivetrain.m_leftLeadEncoder.getPosition() 
        //                      + m_drivetrain.m_rightLeadEncoder.getPosition())
        //                     / 2;
        return System.currentTimeMillis()>=m_endTime;
    }
  
    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_drivetrain.drive(0, 0);
    }

}
