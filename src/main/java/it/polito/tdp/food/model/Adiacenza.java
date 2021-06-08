package it.polito.tdp.food.model;

public class Adiacenza {

	private String porzione1;
	private String porzione2;
	private Integer peso;
	
	
	
	public Adiacenza(String porzione1, String porzione2, Integer peso) {
		this.porzione1 = porzione1;
		this.porzione2 = porzione2;
		this.peso = peso;
	}
	public String getPorzione1() {
		return porzione1;
	}
	public void setPorzione1(String porzione1) {
		this.porzione1 = porzione1;
	}
	public String getPorzione2() {
		return porzione2;
	}
	public void setPorzione2(String porzione2) {
		this.porzione2 = porzione2;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Adiacenze [porzione1=" + porzione1 + ", porzione2=" + porzione2 + ", peso=" + peso + "]";
	}
	
	
	
	
	
}
