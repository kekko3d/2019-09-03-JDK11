package it.polito.tdp.food.model;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {

	private FoodDao dao;
	private SimpleWeightedGraph <String, DefaultWeightedEdge> grafo;
	private List <String> listaV;
	
	
	
	//recursion
	private double peso = 0;
	
	
	public void model() {
		dao = new FoodDao();
		peso = Double.MIN_VALUE;
	}
	
	
	public void creaGrafo(double calorie) {
		DefaultWeightedEdge b;
		dao = new FoodDao();
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		dao.getPorzioni(calorie);
		listaV = new LinkedList <String> (dao.getPorzioni(calorie));
		listaV.addAll(dao.getPorzioni(calorie));
		Graphs.addAllVertices(grafo, listaV);
		System.out.println(grafo.vertexSet().size());
		
		for(Adiacenza a : dao.getAdiacenze(calorie)) {

			//questo if e il seguente servono a non aggiungere lo stesso arco più volte
			if(this.grafo.containsVertex(a.getPorzione1()) ||
					this.grafo.containsVertex(a.getPorzione1())) {


				b = this.grafo.getEdge(a.getPorzione1(), a.getPorzione1());
				if(b == null)
					Graphs.addEdgeWithVertices(this.grafo, a.getPorzione1(), a.getPorzione2(), a.getPeso());
			}

		}

	System.out.println(grafo.edgeSet().size());

	}


	public List<String> getPorzioni() {
		return listaV;
	}


	public LinkedList<porzioneAdiacente> getCorrelate(String value) {
		LinkedList <porzioneAdiacente> temp = new LinkedList<>();
		//come lo fa questo il programma
		for(DefaultWeightedEdge d: grafo.edgesOf(value)) {
			Integer peso = (int) grafo.getEdgeWeight(d);
			String vertice = Graphs.getOppositeVertex(grafo, d, value);
			temp.add(new porzioneAdiacente(vertice, peso));
		}

		return temp;
		
	}

	public double getPesoCammino() {
		return peso;
	}
	
	
	public ArrayList<String> init (Integer k, String origine) {
		ArrayList <String> parziale = new ArrayList <String> ();
		ArrayList <String> finale = new ArrayList <String> ();
		double pesoPaziale = 0;
		parziale.add(origine);
		
		//for perché abbiamo già l'origine ed è difficile
		//inserire quetsa informazione direttamente nella ricorsione 
		for(String startV : Graphs.neighborListOf(grafo, origine)) {
			recursive(k, parziale, finale, pesoPaziale, startV);
		}
		
		System.out.println(this.peso);
		
		return finale;
	}
	
	
	
	private void recursive(int k, ArrayList<String> parziale, ArrayList<String> finale, double pesoParziale, String startV) {


		if(parziale.size()==k) {
			
			if(pesoParziale >= this.peso) {
				System.out.println(parziale);
				System.out.println(peso);
				finale.clear();
				finale.addAll(parziale);
				this.peso = pesoParziale;
			}
			
		} else {
			for(String neigh : Graphs.neighborListOf(grafo, startV)) {
				if(!parziale.contains(neigh)) {
					DefaultWeightedEdge e = grafo.getEdge(startV, neigh);
					pesoParziale += grafo.getEdgeWeight(e);
					parziale.add(neigh);
						recursive(k, parziale, finale, pesoParziale, neigh);
					parziale.remove(neigh);
					pesoParziale -= grafo.getEdgeWeight(e);
				}
			}
		}
	}


	public void cancelPeso() {
		peso = 0;	
	}
}
