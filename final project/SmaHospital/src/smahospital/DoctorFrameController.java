/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smahospital;

import databaseworks.DatabaseCreate;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import manipulater.Commonalit;
import static manipulater.Commonalit.sql;
import manipulater.DoctorManipulator;
import objects.Doctor;


public class DoctorFrameController extends Commonalit implements Initializable {

    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_service;
    @FXML
    private PasswordField tf_passwordconfirm;
    @FXML
    private PasswordField tf_password;
    @FXML
    private Button btn_saveDoctor;
    @FXML
    private TableView<Doctor> table_doctors;
    @FXML
    private Button btn_UpdateDoctor;
    @FXML
    private Label doctorId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try{
           Image imag= new Image(getClass().getResourceAsStream("/images/edit1.png"));
           btn_UpdateDoctor.setGraphic(new ImageView(imag));
           Image imag2= new Image(getClass().getResourceAsStream("/images/savestud.png"));
           btn_saveDoctor.setGraphic(new ImageView(imag2));
           
            
            
           //doctorId.setVisible(false);
           DoctorManipulator.FormulateDoctorDetailsTable(table_doctors);
           DoctorManipulator.doctorTableMenu(table_doctors);
           DoctorManipulator.doctorTableClicked(table_doctors, doctorId, tf_username, tf_service, tf_password, tf_passwordconfirm);
           DoctorManipulator.getDoctorDataFromDatabase("SELECT * FROM Doctor ORDER BY Id ASC", table_doctors); 
       }catch(Exception ex){}
    }    

    @FXML
    private void btn_saveUserClicked(ActionEvent event) {
        try{
            
            String username=tf_username.getText().trim();
            String password=tf_password.getText().trim();
            String confirmpassword=tf_passwordconfirm.getText().trim();
            String service = tf_service.getText().trim();
            
            boolean isempty = DoctorManipulator.checkEmptyFields(tf_username, tf_service, tf_password, tf_passwordconfirm);
            if(isempty == false){
                if(!password.equals(confirmpassword)){
                    JOptionPane.showMessageDialog(null, "Password don't match");
                }else{
                     ResultSet rst = new DatabaseCreate().getDb("SELECT * FROM Doctor WHERE UserName='"+username+"' ");
                     if(rst.next()){
                             JOptionPane.showMessageDialog(null, "User Name "+username+" was already taken, user another one");
                     }else{                
                        sql="INSERT INTO Doctor(UserName,Password,Service) VALUES (?,?,?)";
                        Doctor doctor =new Doctor(username, password, service);
                        if(DoctorManipulator.insertDoctorToDB(doctor,sql)){
                            JOptionPane.showMessageDialog(null, "Doctor registered successfully");
                            DoctorManipulator.clearFields(tf_username, tf_service, tf_password, tf_passwordconfirm);
                            DoctorManipulator.getDoctorDataFromDatabase("SELECT * FROM Doctor ORDER BY Id ASC", table_doctors);        
                        }else{
                            //dialog.MessageDialog("Doctor registration failed. Try again");
                             JOptionPane.showMessageDialog(null, "Doctor registration failed. Try again");
                        }
                     }
                }
            }else{
                //dialog.MessageDialog("All fields are required");
                JOptionPane.showMessageDialog(null, "All fields are required");
                
            }            
        }catch(SQLException ex){}
    }

    @FXML
    private void btn_UpdateDoctorClicked(ActionEvent event) {
        try{            
            String username,password,service;
            int docId=0;
            
            if(!doctorId.getText().equals("") && doctorId.getText()!=null){                
                docId = Integer.parseInt(doctorId.getText().trim());            
                boolean isempty = DoctorManipulator.checkEmptyFields(tf_username, tf_service, tf_password, tf_passwordconfirm);
                if(isempty == false){
                    if(!tf_password.getText().trim().equals(tf_passwordconfirm.getText().trim())){
                        JOptionPane.showMessageDialog(null, "Password don't match");
                    }else{
                        username=tf_username.getText().trim();
                        password=tf_password.getText().trim();
                        service = tf_service.getText().trim();

                        sql="UPDATE Doctor SET UserName=?,Password=?,Service=? WHERE Id=?";
                        Doctor doctor = new Doctor(docId,username, password, service);
                        if(DoctorManipulator.updateDoctor(doctor,sql)){
                            JOptionPane.showMessageDialog(null, "Doctor updated successfully");
                            doctorId.setText("");
                            DoctorManipulator.clearFields(tf_username, tf_service, tf_password, tf_passwordconfirm);
                            DoctorManipulator.getDoctorDataFromDatabase("SELECT * FROM Doctor ORDER BY Id ASC", table_doctors);        
                        }else{
                            //dialog.MessageDialog("Doctor registration failed. Try again");
                             JOptionPane.showMessageDialog(null, "update failed. Try again");
                        }

                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "double click on the table to get doctor to edit");
            }
        }catch(Exception ex){
        
        }
              
    
    }
    
}
