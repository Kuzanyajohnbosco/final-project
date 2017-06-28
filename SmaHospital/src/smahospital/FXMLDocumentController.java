/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smahospital;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import manipulater.Commonalit;
import objects.DigitalClock;


public class FXMLDocumentController extends Commonalit implements Initializable {
    
    private Label label;
    @FXML
    private Label dateLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Hyperlink BTN_Logout;
    @FXML
    private Pane mainpanel;
    @FXML
    private Button btn_showDoctor;
    @FXML
    private Button btn_showStudent;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {            
            //set the system time
            DigitalClock digitalClock = new DigitalClock(timeLabel);
            
            //set the system date
            digitalClock.setDate(dateLabel);
            
            //start the doctor frame/window
            mainpanel.getChildren().clear();        
            mainpanel.getChildren().add(FXMLLoader.load(getClass().getResource("DoctorFrame.fxml")));   
        } catch (IOException ex) {
            dialog.informationAlert("Failed to load Screen");
        }
    }    

    @FXML
    private void btn_showDoctorClicked(ActionEvent event) {
        try {
            mainpanel.getChildren().clear();        
            mainpanel.getChildren().add(FXMLLoader.load(getClass().getResource("DoctorFrame.fxml")));   
        } catch (IOException ex) {
            dialog.informationAlert("Failed to load Screen");
        }
    }

    @FXML
    private void btn_showStudentClicked(ActionEvent event) {
        try {
            mainpanel.getChildren().clear();        
            mainpanel.getChildren().add(FXMLLoader.load(getClass().getResource("StudentFrame.fxml")));   
        } catch (IOException ex) {
            dialog.informationAlert("Failed to load Screen");
        }
    }

    @FXML
    private void BTN_LogoutClicked(ActionEvent event) {
        try {
            //close this window where logout button is.(Main window)
            Stage stage=(Stage) BTN_Logout.getScene().getWindow();
            stage.close();
            
            //immediately innitailise and start the login window
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);           
            Stage stag=new Stage();
            stag.setScene(scene); 
            Image icon = new Image(getClass().getResourceAsStream("/images/users-icon.png"));
            stag.getIcons().add(icon);
            stag.setResizable(false);
            stag.setTitle("SMA");
            stag.show();
        } catch (IOException ex) {
        }
    }
    
}
