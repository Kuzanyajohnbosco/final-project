/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


public class MyDialog {
    Stage stage;
    
    public void informationAlert(String information){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, Information Dialog");
        alert.setContentText(information);
        alert.showAndWait();   
    }
    
    public boolean ConfirmationAlert(String information){
        boolean isok;
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, Confirmation Dialog");
        alert.setContentText(information);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            isok = true;
        } else {
            isok = false;
        }
        return isok;
    }  
    
    
    public static void ExceptionAlert(String information){

    
    }
    
}
    
