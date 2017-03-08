/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensim.sudokusolver.controler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ninja
 */

public class ChoixAideController implements ChangeListener<Number>, Initializable {

	private double width;
	private double height;
	private int fontSize = 10;

	private String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus lobortis magna massa. Vestibulum ut tempor justo. Curabitur massa mauris, tincidunt ut ultrices ultricies, placerat in est. Morbi felis eros, tristique lacinia gravida vel, efficitur ut lorem. Cras id condimentum justo. Sed in commodo ligula, hendrerit consequat ex. Maecenas eget euismod mauris. Mauris sed sem id orci consequat posuere. Integer finibus, erat nec molestie tristique, odio orci hendrerit velit, ut placerat ante eros vel urna. Nullam laoreet maximus fermentum. Pellentesque laoreet in velit non consequat. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla maximus venenatis elit, eu cursus sem semper eget.";

	@FXML
	private TextArea table;
	@FXML
	private Button button1;
	@FXML
	private Button button2;
	@FXML
	private Button button3;
	@FXML
	private Button button4;
	@FXML
	private Button button5;
	@FXML
	private Button button6;
	@FXML
	private Button buttonValider;

	/**
	 * Initializes the controller class.
	 * 
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		fontSizeModification(button1);
		fontSizeModification(button2);
		fontSizeModification(button3);
		fontSizeModification(button4);
		fontSizeModification(button5);
		fontSizeModification(button6);
		fontSizeModification(buttonValider);
	}

	public void fontSizeModification(final Button button) {
		button.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth,
					Number newSceneWidth) {
				// System.out.println("Width: " + newSceneWidth);
				setWidth((double) newSceneWidth);
				setFontSize((int) ((getWidth() * getHeight()) / 1000));
				// System.out.println("Font Size : " + getFontSize());
				button.styleProperty().bind(Bindings.concat("-fx-font-size: ", getFontSizeString(), ";"));
			}
		});

		button.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight,
					Number newSceneHeight) {
				// System.out.println("Height: " + newSceneHeight);
				setHeight((double) newSceneHeight);
				setFontSize((int) ((getWidth() * getHeight()) / 1000));
				// System.out.println("Font Size : " + getFontSize());
				button.styleProperty().bind(Bindings.concat("-fx-font-size: ", getFontSizeString(), ";"));
			}
		});
	}

	public int getFontSize() {
		return fontSize;
	}

	public String getFontSizeString() {
		return fontSize + "px";
	}

	public void setFontSize(int fontSize) {
		if (fontSize > 10) {
			this.fontSize = fontSize;
		} else {
			this.fontSize = 10;
		}
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@FXML
	public void handleButton1(ActionEvent event) {
		table.setText("Button1 " + lorem);
	}

	@FXML
	public void handleButton2(ActionEvent event) {
		table.setText("Button2 " + lorem);
	}

	@FXML
	public void handleButton3(ActionEvent event) {
		table.setText("Button3 " + lorem);
	}

	@FXML
	public void handleButton4(ActionEvent event) {
		table.setText("Button4 " + lorem);
	}

	@FXML
	public void handleButton5(ActionEvent event) {
		table.setText("Button5 " + lorem);
	}

	@FXML
	public void handleButton6(ActionEvent event) {
		table.setText("Button6 " + lorem);
	}

	@FXML
	public void handleButtonValider(ActionEvent event) {
		table.setText("ButtonValider");
		Stage stage;
		Button b = (Button) event.getSource();
		stage = (Stage) b.getScene().getWindow();

		try {
			switchToView("/fxml/Resolution.fxml", stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	}

}
