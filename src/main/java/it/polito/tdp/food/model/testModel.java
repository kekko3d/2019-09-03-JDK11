package it.polito.tdp.food.model;

public class testModel {

	public static void main(String[] args) {
		Model md = new Model();
		md.creaGrafo(20.0);
		md.init(10, "packet");
		System.out.println(md.getPesoCammino());

	}

}
