package br.com.codenation.calculadora;

public class CalculadoraSalario {

	private double calcularInss(double salarioBase) {
		double baseminss = 0.0;
		if (salarioBase <= 1500) {
			baseminss = salarioBase - (salarioBase*8/100);
		} else if (salarioBase <= 4000) {
			baseminss = salarioBase - (salarioBase*9/100);
		} else {
			baseminss = salarioBase - (salarioBase*11/100);
		}
		return baseminss;
	}

	private double calcularIRRF(double baseminss) {
		double descIRRF = baseminss;
		if (baseminss > 6000) {
			descIRRF = baseminss - (baseminss*15/100);
		} else if (baseminss > 3000) {
			descIRRF = baseminss - (baseminss*7.5/100);
		}
		return descIRRF;
	}

	public long calcularSalarioLiquido(double salarioBase) {
		double saliq = 0.0;
		if (salarioBase > 1039) {
			double descinss = calcularInss(salarioBase);
			saliq = calcularIRRF(descinss);
		}

		return Math.round(saliq);
	}


}
