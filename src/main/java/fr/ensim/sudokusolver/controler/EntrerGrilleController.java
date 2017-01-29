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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
	GridPane grdSelect;
	Label selectedCase;
	
	@FXML
	Button bt_valid;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {
			bt_valid.setOnAction(Valid);
			grdSelect = FXMLLoader.load(getClass().getResource("/fxml/selectChiffre.fxml"));
			gridContainer.getChildren().add(grdSelect);
			grdSelect.setVisible(false);
			grdSelect.getChildren().forEach((nd) -> {
				nd.setOnMouseClicked(clickOnSelect);
			});

			labelList = new ArrayList<>();

			int taille = sudokuGrid.getColumnConstraints().size();

			for (int i = 0; i < taille; i++) {
				for (int j = 0; j < taille; j++) {
					GridPane sousGrille = FXMLLoader.load(getClass().getResource("/fxml/sousGrille.fxml"));
					sudokuGrid.add(sousGrille, i, j);
					sousGrille.getChildren().forEach((nd) -> {
						nd.setOnMouseClicked(relocateSelectGrid);
					});

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		gridContainer.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if ((double) newValue < gridContainer.getHeight()) {
					sudokuGrid.setPrefHeight(newValue.doubleValue());
					sudokuGrid.setPrefWidth(newValue.doubleValue());
					
					if(selectedCase != null){
						Point2D pt = getGridCoord(gridContainer, selectedCase);
						double x = pt.getX() - (grdSelect.getWidth() - selectedCase.getWidth()) / 2;
						double y = pt.getY() - (grdSelect.getHeight() - selectedCase.getHeight()) / 2;
						grdSelect.relocate(x, y);
					}

				}
			}
		});

		gridContainer.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if ((double) newValue < gridContainer.getWidth()) {
					sudokuGrid.setPrefHeight(newValue.doubleValue());
					sudokuGrid.setPrefWidth(newValue.doubleValue());
					
					if(selectedCase != null){
						Point2D pt = getGridCoord(gridContainer, selectedCase);
						double x = pt.getX() - (grdSelect.getWidth() - selectedCase.getWidth()) / 2;
						double y = pt.getY() - (grdSelect.getHeight() - selectedCase.getHeight()) / 2;
						grdSelect.relocate(x, y);
					}

				}

			}
		});

	}

	public EventHandler<MouseEvent> relocateSelectGrid = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Label l = (Label) event.getSource();
			if (event.getButton() == MouseButton.PRIMARY) {
				if (selectedCase != null) {
					selectedCase.setStyle("");
				}
				selectedCase = l;
				selectedCase.setStyle("-fx-background-color: #9BBFB4;");
			} else if (event.getButton() == MouseButton.SECONDARY) {
				l.setText(" ");
			}
			
			Point2D pt = getGridCoord(gridContainer, selectedCase);
			double x = pt.getX() - (grdSelect.getWidth() - selectedCase.getWidth()) / 2;
			double y = pt.getY() - (grdSelect.getHeight() - selectedCase.getHeight()) / 2;
			grdSelect.relocate(x, y);
			grdSelect.setVisible(true);

		}
	};

	public EventHandler<MouseEvent> clickOnSelect = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			grdSelect.setVisible(false);
			if (selectedCase != null)
				selectedCase.setStyle("");
			Label s = (Label) event.getSource();
			selectedCase.setText(s.getText());
		}
	};

	private Point2D getGridCoord(Node p, Node t) {
		double x = 0;
		double y = 0;
		Node cur = t;
		do {
			x += cur.getLayoutX();
			y += cur.getLayoutY();
			cur = cur.getParent();
		} while (cur != null && cur != p);

		return new Point2D(x, y);
	}
	
	private EventHandler<ActionEvent> Valid = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			try{
				Stage stage;
				Button b = (Button) event.getSource();
				stage = (Stage) b.getScene().getWindow();

				switchToView("/fxml/Resolution.fxml", stage);
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
