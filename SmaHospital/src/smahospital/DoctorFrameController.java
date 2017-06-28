/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smahospital;

import databaseworks.DatabaseCreate;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import manipulater.Commonalit;
import static manipulater.Commonalit.sql;
import manipulater.DoctorManipulator;
import objects.Doctor;


public class DoctorFrameController extends Commonalit implements Initializable {

    @FXML
    private TextField tf_username;
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
    @FXML
    private ImageView doc_img;
    @FXML
    private TextField tf_imageUrl;
    @FXML
    private Button btn_addImage;
    @FXML
    private TextField tf_loginname;

    
    File selectedfile = null;
    @FXML
    private ComboBox<String> service_combo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try{
           tf_imageUrl.setEditable(false);
           Image imag= new Image(getClass().getResourceAsStream("/images/edit1.png"));
           btn_UpdateDoctor.setGraphic(new ImageView(imag));
           Image imag2= new Image(getClass().getResourceAsStream("/images/savestud.png"));
           btn_saveDoctor.setGraphic(new ImageView(imag2));
           
            
            
           //doctorId.setVisible(false);
           DoctorManipulator.FormulateDoctorDetailsTable(table_doctors);
           DoctorManipulator.doctorTableMenu(table_doctors);
           DoctorManipulator.doctorTableClicked(table_doctors, doctorId, tf_username, service_combo, tf_password, tf_passwordconfirm,doc_img, tf_loginname);
           DoctorManipulator.getDoctorDataFromDatabase("SELECT d.Id, d.UserName, d.Password, d.PhotoUrl, d.Service_id, d.loginname, s.id, s.name  FROM Doctor d, Service s WHERE d.Service_id = s.id ORDER BY d.Id ASC", table_doctors);                     
           DoctorManipulator.setServiceCombo("SELECT * FROM service WHERE status=1", service_combo);
       }catch(Exception ex){
       ex.printStackTrace();
       }
    }    

    @FXML
    private void btn_saveUserClicked(ActionEvent event) {
        try{            
            String username=tf_username.getText().trim();
            String password=tf_password.getText().trim();
            String confirmpassword=tf_passwordconfirm.getText().trim();
            String[]servArray = service_combo.getSelectionModel().getSelectedItem().split("-");
            String service_id = servArray[0];  
            String loginname=tf_loginname.getText().trim();
            
            boolean isempty = DoctorManipulator.checkEmptyFields(tf_username, service_combo, tf_password, tf_passwordconfirm, tf_loginname);
            if(isempty == false){
                if(!password.equals(confirmpassword)){
                    dialog.informationAlert("Password don't match");
                }else{
                     ResultSet rst = new DatabaseCreate().getDb("SELECT * FROM Doctor WHERE UserName='"+username+"' ");
                     if(rst.next()){
                            dialog.informationAlert("User Name "+username+" was already taken, user another one");
                     }else{  
                         
                        sql="INSERT INTO Doctor(UserName,Password,Service_id,PhotoUrl,loginname) VALUES (?,?,?,?,?)";                        
                        if(selectedfile != null){
                            Path movefrom = FileSystems.getDefault().getPath(selectedfile.getPath());
                            Path target = FileSystems.getDefault().getPath(Commonalit.imageTargetPath + selectedfile.getName());
                            String fileName = movefrom.getFileName().toString();
                            Doctor doctor = new Doctor(username, password, service_id, fileName, loginname);
                            
                            if(DoctorManipulator.insertDoctorToDB(doctor,sql)){                            
                               Files.move(movefrom,target,StandardCopyOption.ATOMIC_MOVE); 

                               dialog.informationAlert("Doctor registered successfully");
                               DoctorManipulator.clearFields(tf_username, service_combo, tf_password, tf_passwordconfirm, tf_imageUrl,doc_img, tf_loginname);
                               DoctorManipulator.getDoctorDataFromDatabase("SELECT d.Id, d.UserName, d.Password, d.PhotoUrl, d.Service_id, d.loginname, s.id, s.name FROM doctor d, service s WHERE d.Service_id = s.id ORDER BY d.Id ASC", table_doctors);    
                               selectedfile = null;
                            }else{
                                 dialog.informationAlert("Doctor registration failed. Try again");
                            }
                        }else{
                            dialog.informationAlert("Browse for doctor's photo first");
                        }
                     }
                }
            }else{
                dialog.informationAlert("All fields are required");                
            }            
        }catch(HeadlessException | IOException | SQLException ex){}
    }

    @FXML
    private void btn_UpdateDoctorClicked(ActionEvent event) {
        try{            
            String username,password,service,loginname;
            int docId=0;
            
            if(!doctorId.getText().equals("") && doctorId.getText()!=null){                
                docId = Integer.parseInt(doctorId.getText().trim());            
                boolean isempty = DoctorManipulator.checkEmptyFields(tf_username, service_combo, tf_password, tf_passwordconfirm, tf_loginname);
                if(isempty == false){
                    if(!tf_password.getText().trim().equals(tf_passwordconfirm.getText().trim())){
                        dialog.informationAlert("Password don't match");
                    }else{
                        username=tf_username.getText().trim();
                        password=tf_password.getText().trim();
                        String []comboArray = service_combo.getSelectionModel().getSelectedItem().split("-");
                        service = comboArray[0];
                        loginname=tf_loginname.getText().trim();

                        sql="UPDATE Doctor SET UserName=?,Password=?,Service_id=?,loginname=? WHERE Id=?";
                        Doctor doctor = new Doctor(docId,username, password, service, loginname);
                        if(DoctorManipulator.updateDoctor(doctor,sql)){
                            dialog.informationAlert("Doctor updated successfully");
                            doctorId.setText("");
                            DoctorManipulator.clearFields(tf_username, service_combo, tf_password, tf_passwordconfirm, tf_imageUrl,doc_img, tf_loginname);
                            DoctorManipulator.getDoctorDataFromDatabase("SELECT d.Id, d.UserName, d.Password, d.PhotoUrl, d.Service_id, s.id, s.name, d.loginname  FROM Doctor d, Service s WHERE d.Service_id = s.id ORDER BY d.Id ASC", table_doctors);                           
                        }else{
                             dialog.informationAlert("update failed. Try again");
                        }
                    }
                }
            }else{
                dialog.informationAlert("double click on the table to get doctor to edit");
            }
        }catch(Exception ex){
        
        }
              
    
    }

    @FXML
    private void btn_addImage_clicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser(); 
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);              
        selectedfile = fileChooser.showOpenDialog(null); 
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedfile);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            doc_img.setImage(image);   

            String imagePath = selectedfile.getAbsolutePath();
            tf_imageUrl.setText(imagePath);  
        } catch (IOException ex) {
            dialog.informationAlert("A problem has occured. contact your system admin");
        }        
    }
    
}
