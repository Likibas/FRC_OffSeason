// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class intake extends SubsystemBase {
  /** Creates a new intake. */
  public intake() {}
  // Motores e Sensor
  public final TalonFX bola = new TalonFX(39);
  public final SparkMax ligar = new SparkMax(20, MotorType.kBrushless);
  private final DigitalInput pino1 = new DigitalInput(9);

  // Variável de estado para controlar a lógica de captura
  private boolean esperandoPeca = false;

  // PID e variáveis de estado
  private boolean sensorAtivo = true;
  private static PIDController PIDintake = new PIDController(0.05, 0, 0);
  public double intp = -20;
  public double Mbola = 0;

  // --- MÉTODOS DE AÇÃO ---
  public void pegar() {
    intp = -3; // Posição de pegar
    Mbola = 0.30;
    // this.esperandoPeca = true;
  }

  public void pegarBoia() {
    intp = -3; // Posição de pegar
    Mbola = 0.14;
   // this.esperandoPeca = true;
  }

  public void solta() {
    Mbola = -0.5;
  //  this.esperandoPeca = false;
  }

  public void cima() {
    intp = -17; // Posição de subida
    Mbola = 0.17;
    // this.esperandoPeca = false; 
  }
  public void cima2() {
    intp = -17; // Posição de subida
    Mbola = 0;
    // this.esperandoPeca = false; 
  }

 // public void pararSensor() {
   // sensorAtivo = false;
  // }  

  /** Retorna true quando o sensor detecta a peça (LED vermelho). */
 // public boolean temPeca() {
   // if (sensorAtivo) {
     // return false; // Sensor desligado → sempre retorna falso
   // }
   // return pino1.get();
 // }  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Dashboard
    // SmartDashboard.putBoolean("Sensor Detectou Peça?", temPeca());
    
  // if (temPeca()) {
     //  this.cima();
    // }
    
    // Controle dos motores
    bola.set(Mbola);
    ligar.set(MathUtil.clamp(PIDintake.calculate(ligar.getEncoder().getPosition(), intp), -0.8, 0.8));
  }

  // Comandos pro autônomo
  public void soltaAuto() {
    Mbola = -0.5;
  }

}