package models;

public class valoresMedios {

	public valoresMedios(String indicador, double valor) {
		super();
		this.indicador = indicador;
		this.valor = valor;
	}
	String indicador;
	double valor;
	public String getIndicador() {
		return indicador;
	}
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
}
