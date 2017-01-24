/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensim.sudokusolver.controler;

import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
    Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        labelList = new ArrayList<>();

        int taille = sudokuGrid.getColumnConstraints().size();

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                label = new Label("1");
                label.setOnMouseClicked(changeLabelText);
                labelList.add(label);
                sudokuGrid.add(label, i, j);
            }
        }

        gridContainer.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double newV;
                if ((double) newValue < gridContainer.getHeight()) {
                    newV = (((double) newValue) - 40) / 9;
                } else {
                    newV = (gridContainer.getHeight() - 40) / 9;
                }

                for (int i = 0; i < 9; i++) {
                    sudokuGrid.getColumnConstraints().get(i).setMinWidth(newV);
                    sudokuGrid.getRowConstraints().get(i).setMinHeight(newV);
                }

            }
        });

        gridContainer.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double newV;
                if ((double) newValue < gridContainer.getWidth()) {
                    newV = (((double) newValue) - 40) / 9;
                } else {
                    newV = (gridContainer.getWidth() - 40) / 9;
                }

                for (int i = 0; i < 9; i++) {
                    sudokuGrid.getColumnConstraints().get(i).setMinWidth(newV);
                    sudokuGrid.getRowConstraints().get(i).setMinHeight(newV);
                }
            }
        });

    }

    public EventHandler<MouseEvent> changeLabelText = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Label l = (Label) event.getSource();
            if (event.getButton() == MouseButton.PRIMARY) {
                int i;
                if (l.getText().equals(" ")) {
                    i = 1;
                } else {
                    i = Integer.parseInt(l.getText()) + 1;
                    if (i == 10) {
                        i = 1;
                    }
                }
                l.setText(i + "");
            } else if (event.getButton() == MouseButton.SECONDARY) {
                l.setText(" ");
            }

        }
    };

}
