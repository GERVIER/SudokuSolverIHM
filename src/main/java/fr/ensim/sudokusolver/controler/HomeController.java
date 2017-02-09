/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensim.sudokusolver.controler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ninja
 */
public class HomeController implements Initializable {

	@FXML
	private Button enterGridButton;
	@FXML
	private Button scanGridButton;

	/**
	 * Initializes the controller class.
	 * 
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		enterGridButton.setOnAction(enterGridClick);
		scanGridButton.setOnAction(goToScan);
	}

	public EventHandler<ActionEvent> enterGridClick = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			try{
				Stage stage;
				Button b = (Button) event.getSource();
				stage = (Stage) b.getScene().getWindow();

				switchToView("/fxml/EntrerGrille.fxml", stage);
			}catch (IOException ex) {
			}
		}
	};
	
	public EventHandler<ActionEvent> goToScan = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			try{
				Stage stage;
				Button b = (Button) event.getSource();
				stage = (Stage) b.getScene().getWindow();

				switchToView("/fxml/ScanGrille.fxml", stage);
			}catch (IOException ex) {
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
