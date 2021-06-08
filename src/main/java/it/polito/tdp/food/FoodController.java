/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.food.model.Model;
import it.polito.tdp.food.model.porzioneAdiacente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	txtResult.clear();

    	txtResult.clear();
    	txtResult.appendText("Cerco cammino peso massimo...");
    	
    	
    	Integer k;
    	if(txtPassi.getText().compareTo("") == 0) {
    		txtResult.setText("inserisci un numero di passi");
    		return;
    	}
    	try {
    		k =Integer.parseInt(txtPassi.getText());
    	} catch (NumberFormatException n) {
    		txtResult.setText("il numero di passi per il cammino deve essere composto da cifre");
    		return;
    	}
    	
    	if(boxPorzioni.getValue() == null) {
    		txtResult.setText("Selezione un tipo di porzione");
    		return;
    	}
    	
    	txtResult.clear();
    	

    	
    	for(String s : model.init(k, boxPorzioni.getValue()))
    		txtResult.appendText(s + '\n');
    	if(model.getPesoCammino() == 0) {
    		txtResult.appendText("Non ho trovato un cammino di lunghezza N\n");
    	} else {
    		txtResult.appendText(model.getPesoCammino() + "");
    	}
    	model.cancelPeso();
    	
    	
    	
    	
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco porzioni correlate...");
    	txtResult.clear();

    	if(boxPorzioni.getValue() == null) {
    		txtResult.setText("Selezione un tipo di porzione");
    		return;
    	}

    	for(porzioneAdiacente p : model.getCorrelate(boxPorzioni.getValue())) {
    		txtResult.appendText(p.toString());
    	}


    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	boxPorzioni.getItems().clear();

    	double k = 0;
    	if(txtCalorie.getText().compareTo("") == 0) {
    		txtResult.setText("inserisci un numero di calorie");
    		return;
    	}
    	try {
    		k =Double.parseDouble(txtCalorie.getText());
    	} catch (NumberFormatException n) {
    		txtResult.setText("il numero di calorie deve essere indicato con delle cifre");
    		return;
    	}
    	
    	txtResult.clear();
    	txtResult.appendText("Creazione grafo...");
    	model.creaGrafo(k);
    	boxPorzioni.getItems().addAll(model.getPorzioni());
    	txtResult.setText("Grafo creato");
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;

    }
}
