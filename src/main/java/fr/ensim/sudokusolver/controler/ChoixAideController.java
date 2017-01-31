/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensim.sudokusolver.controler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Ninja
 */

public class ChoixAideController implements ChangeListener, Initializable {
    
    private double width;
    private double height;
    private int fontSize=10;
    
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table.setText("Information here");
        fontSizeModification(button1);
        fontSizeModification(button2);
        fontSizeModification(button3);
        fontSizeModification(button4);
        fontSizeModification(button5);
        fontSizeModification(button6);
        fontSizeModification(buttonValider);
        
        
    }
    
    public void fontSizeModification(final Button button){

        button.widthProperty().addListener(new ChangeListener<Number>() {
            @Override 
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                System.out.println("Width: " + newSceneWidth);
                setWidth((double)newSceneWidth);
                setFontSize((int)((getWidth()*getHeight())/1000));
                System.out.println("Font Size : "  + getFontSize());
                button.styleProperty().bind(Bindings.concat("-fx-font-size: ", getFontSizeString() , ";"));
            }
        });
        
        button.heightProperty().addListener(new ChangeListener<Number>() {
            @Override 
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                System.out.println("Height: " + newSceneHeight);
                setHeight((double)newSceneHeight);
                setFontSize((int)((getWidth()*getHeight())/1000));
                System.out.println("Font Size : "  + getFontSize());
                button.styleProperty().bind(Bindings.concat("-fx-font-size: ", getFontSizeString(), ";"));
            }
        });
        
        
    }
        
    public int getFontSize() {
        return fontSize;
    }
    
     public String getFontSizeString() {
        return fontSize+"px";
    }

    public void setFontSize(int fontSize) {
        if(fontSize>10){
            this.fontSize = fontSize;
        }
        else{
            this.fontSize=10;
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
    public void handleButton1(ActionEvent event){
          table.setText("Button1");
    }
    
    @FXML
    public void handleButton2(ActionEvent event){
          table.setText("Button2");
    }
    
    @FXML
    public void handleButton3(ActionEvent event){
          table.setText("Button3");
    }
    
    @FXML
    public void handleButton4(ActionEvent event){
          table.setText("Button4");
    }
    
    @FXML
    public void handleButton5(ActionEvent event){
          table.setText("Button5");
    }
    
    @FXML
    public void handleButton6(ActionEvent event){
          table.setText("Button6");
    }
    
     @FXML
    public void handleButtonValider(ActionEvent event){
          table.setText("ButtonValider");
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
