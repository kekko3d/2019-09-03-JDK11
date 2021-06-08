package it.polito.tdp.food.model;

public class porzioneAdiacente {

	private String porzione;
	private Integer peso;
	
	
	
	public porzioneAdiacente(String porzione, Integer peso) {
		super();
		this.porzione = porzione;
		this.peso = peso;
	}
	
	
	public String getPorzione() {
		return porzione;
	}
	public void setPorzione(String porzione) {
		this.porzione = porzione;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}


	@Override
	public String toString() {
		return porzione + " " + peso + '\n';
	}
	
	
	
	
}
