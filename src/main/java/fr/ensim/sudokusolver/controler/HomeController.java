/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensim.sudokusolver.controler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class HomeController implements Initializable{
    
    @FXML
    private Button enterGridButton;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        enterGridButton.setOnAction(enterGridClick);
    }
    
        
    public EventHandler<ActionEvent> enterGridClick = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event){
            try {
                Stage stage;
                Parent enterGrid;
                Button b= (Button) event.getSource();
                stage = (Stage) b.getScene().getWindow();
                enterGrid = FXMLLoader.load(getClass().getResource("/fxml/EntrerGrille.fxml"));
                Scene scene = new Scene(enterGrid);
                stage.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(ViewControler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
   
}
