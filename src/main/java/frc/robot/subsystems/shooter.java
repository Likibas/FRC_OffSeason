// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class shooter extends SubsystemBase {
  public final SparkMax lacador = new SparkMax(30, MotorType.kBrushless);
  public final SparkMax pacador = new SparkMax(31, MotorType.kBrushless);

  public final SparkMax elevador = new SparkMax(14, MotorType.kBrushless);
  public final TalonFX direcao = new TalonFX(36);

  private static PIDController PIDdirecao = new PIDController(0.1, 0, 0);

  public double Vlancador = 0;
  public double Vpacador = 0;
  public double Velevador = 0;
  public double Vposicao = 0;
  public int tempo = 0;
  /** Creates a new shooter. */
  public shooter() {}

  public void atirar(){
    Vlancador = -0.7;
    Vpacador = -0.7;
    Velevador = 0.9;
  }
  public void parar(){
    Vlancador = 0;
    Vpacador = 0;
    Velevador = 0;
  }

  public void direita(){
    Vposicao = Vposicao -0.08;
  }
  public void esquerda(){
    Vposicao = Vposicao +0.08;
  }


  @Override
  public void periodic() {

    if (Velevador > 0){
      tempo = tempo + 1;
      if (tempo > 20){
        elevador.set(Velevador);
      }
    }
    else {
      tempo = 0;
      elevador.set(Velevador);
    }
    pacador.set(Vpacador);
    lacador.set(Vlancador);

    direcao.set(MathUtil.clamp(PIDdirecao.calculate(direcao.getPosition().getValueAsDouble(), Vposicao), -0.4,0.4));

    //ligar.set(MathUtil.clamp(PIDintake.calculate(ligar.getEncoder().getPosition(), intp), -0.8, 0.8));
  }
}
