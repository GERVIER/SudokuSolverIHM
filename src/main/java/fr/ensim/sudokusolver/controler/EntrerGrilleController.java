/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensim.sudokusolver.controler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Ninja
 */
public class EntrerGrilleController implements Initializable {

    @FXML
    GridPane sudokuGrid;

    @FXML
    Pane gridContainer;

    ArrayList<Label> labelList;
    
    Label selectedCase;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
			GridPane grdSelect = FXMLLoader.load(getClass().getResource("/fxml/selectChiffre.fxml"));
			gridContainer.getChildren().add(grdSelect);
			//grdSelect.setVisible(false);
			
	        labelList = new ArrayList<>();

	        int taille = sudokuGrid.getColumnConstraints().size();

	        for (int i = 0; i < taille; i++) {
	            for (int j = 0; j < taille; j++) {
	            	GridPane sousGrille = FXMLLoader.load(getClass().getResource("/fxml/sousGrille.fxml"));
	            	sudokuGrid.add(sousGrille, i, j);
	            	sousGrille.getChildren().forEach((nd) -> {
	            		nd.setOnMouseClicked(changeLabelText);
	            	});
	            	
	            }
	        }
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        gridContainer.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if ((double) newValue < gridContainer.getHeight()) {
                	sudokuGrid.setPrefHeight(newValue.doubleValue());
                	sudokuGrid.setPrefWidth(newValue.doubleValue());
                }
            }
        });

        gridContainer.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if ((double) newValue < gridContainer.getWidth()) {
                	sudokuGrid.setPrefHeight(newValue.doubleValue());
                	sudokuGrid.setPrefWidth(newValue.doubleValue());
                }

            }
        });

    }

    public EventHandler<MouseEvent> changeLabelText = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Label l = (Label) event.getSource();
            if (event.getButton() == MouseButton.PRIMARY) {
            	if(selectedCase != null){
            		selectedCase.setStyle("");
            	}
            	selectedCase = l;
            	selectedCase.setStyle("-fx-background-color: #d7ab7a;");
            } else if (event.getButton() == MouseButton.SECONDARY) {
                l.setText(" ");
            }

        }
    };

}
