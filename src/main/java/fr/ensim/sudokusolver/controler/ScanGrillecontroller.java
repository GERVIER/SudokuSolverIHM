package fr.ensim.sudokusolver.controler;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Ninja
 */
public class ScanGrillecontroller implements Initializable {

	@FXML
	private AnchorPane anchorImg;

	@FXML
	private Button bt_Resolution;
	
	@FXML
	private ImageView img;

	@FXML
	private Pane gridContainer;

	@FXML
	private GridPane sudokuGrid;

	private double imgH, imgW;

	/**
	 * Initializes the controller class.
	 * 
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		bt_Resolution.setOnAction(goToReso);
		Image image = new Image("/img/Sudoku.png");
		img.setImage(image);
		imgSizeModification(anchorImg);

		int taille = sudokuGrid.getColumnConstraints().size();

		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				try {
					GridPane sousGrille = FXMLLoader.load(getClass().getResource("/fxml/SousGrille.fxml"));
					sudokuGrid.add(sousGrille, i, j);
				} catch (IOException ex) {
					Logger.getLogger(ScanGrillecontroller.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
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

	public void imgSizeModification(final AnchorPane anchorImg) {
		anchorImg.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth,
					Number newSceneWidth) {
				imgW = (double) newSceneWidth;
				if ((double) newSceneWidth < img.getFitHeight()) {
					img.setFitHeight(imgW-150);
					img.setFitWidth(imgW-150);
				}
				
				
				img.setFitWidth(imgW - 150);
				System.out.println(newSceneWidth);
				System.out.println(newSceneWidth);

			}
		});

		anchorImg.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight,
					Number newSceneHeight) {
				imgH = (double) newSceneHeight;
				if ((double) newSceneHeight < img.getFitWidth()) {
					img.setFitHeight(imgH-150);
					img.setFitWidth(imgH-150);
				}
			}
		});
	}
	
	public EventHandler<ActionEvent> goToReso = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			try{
				Stage stage;
				Button b = (Button) event.getSource();
				stage = (Stage) b.getScene().getWindow();
				switchToView("/fxml/ChoixAide.fxml", stage);
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	};
	
	
	
	/**
	 * Permet de changer de vue
	 *
	 * @param view
	 *            La nouvelle vue voulu
	 * @param stage
	 *            La fenetre pour la quelle la vue est à changer
	 * @throws IOException
	 */
	public void switchToView(String view, Stage stage) throws IOException {
		Parent newView;
		double h = stage.getHeight();
		double w = stage.getWidth();

		newView = FXMLLoader.load(getClass().getResource(view));
		Scene scene = new Scene(newView);

		stage.setScene(scene);
		stage.setHeight(h);
		stage.setWidth(w);
	}
}
