/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulater;

import databaseworks.DatabaseCreate;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import objects.Doctor;


public class DoctorManipulator extends Commonalit{
    static ObservableList<Doctor> doctorList=FXCollections.observableArrayList(); 
    
     public static boolean checkEmptyFields(TextField tf_username, ComboBox<String>service, PasswordField tf_password, PasswordField tf_passwordconfirm,TextField tf_loginname) {
        boolean isempty = false;
        if(tf_username.getText().equals("") || service.getSelectionModel().getSelectedItem().equals("") || tf_passwordconfirm.getText().equals("") || tf_password.getText().equals("") || tf_username.getText().equals("") ){
            isempty = true;
        }
        return isempty;
    }
    
    
    public static boolean insertDoctorToDB(Doctor doctor,String sql){
        boolean inserted=false;
        PreparedStatement pst;
        try{
            pst = new DatabaseCreate().connectToDB().prepareStatement(sql);
            pst.setString(1, doctor.getUserName());
            pst.setString(2, doctor.getPassword());
            pst.setString(3, doctor.getService());
            pst.setString(4, doctor.getImgPath());
            pst.setString(5, doctor.getloginname());
            pst.execute();     
            pst.closeOnCompletion();
            inserted=true;
        }catch(SQLException ex){            
        }
        return inserted;
    }
    
    public static boolean updateDoctor(Doctor doctor,String sql){
        boolean updated=false;
        PreparedStatement pst;
        try{
            pst = new DatabaseCreate().connectToDB().prepareStatement(sql);
            pst.setString(1, doctor.getUserName());
            pst.setString(2, doctor.getPassword());
            pst.setString(3, doctor.getService());            
            pst.setString(4, doctor.getloginname());
            pst.setInt(5, doctor.getId());
            pst.execute();     
            pst.closeOnCompletion();
            updated=true;
        }catch(SQLException ex){            
        }
        return updated;
    }

    public static void clearFields(TextField tf_username, ComboBox<String>serviceCombo, PasswordField tf_password, PasswordField tf_passwordconfirm, TextField tf_imageUrl, ImageView doc_img, TextField tf_loginname) {
        try{
            tf_username.clear();
            serviceCombo.getSelectionModel().clearSelection();
            tf_password.clear();
            tf_passwordconfirm.clear();
            tf_imageUrl.clear();
            tf_loginname.clear();
            doc_img.setImage(null);
        }catch(Exception ex){}
    }

    public static void FormulateDoctorDetailsTable(TableView<Doctor>doctorTable){
        try{
            TableColumn userName= new TableColumn("USER NAME");
            userName.setMinWidth(230);
            userName.setCellValueFactory(new PropertyValueFactory<>("userName"));

            TableColumn service= new TableColumn("SERVICE");
            service.setMinWidth(230);
            service.setCellValueFactory(new PropertyValueFactory<>("service"));
            
            TableColumn loginname= new TableColumn("LOGIN NAME");
            loginname.setMinWidth(230);
            loginname.setCellValueFactory(new PropertyValueFactory<>("loginname"));

            doctorTable.getColumns().addAll(userName,service,loginname);
        }catch(Exception ex){
        }        
    }
     
     private static Doctor fetchDoctorDataFromDb(ResultSet rst){
            Doctor obj=null;
        try {          
            int id=rst.getInt("d.Id");
            String username=rst.getString("d.UserName");
            String service_id=rst.getString("s.id");
            String service=rst.getString("s.name");
            String password=rst.getString("d.Password");
            String photo=rst.getString("d.PhotoUrl");
            String loginname=rst.getString("d.loginname");
            obj=new Doctor(id, username, password, service_id+"-"+service, photo, loginname);
            return obj;
        } catch (SQLException ex) {  
            ex.printStackTrace();
            return obj;
        }
        
    }
    
    private static void setDoctorToTable(ResultSet rst,TableView<Doctor>doctorTable){
       doctorList.clear();
       try {
           while (rst.next()) {              
               doctorList.add(fetchDoctorDataFromDb(rst));               
           }
           doctorTable.setItems(doctorList);
       } catch (SQLException ex) {
           ex.printStackTrace();
       }            
    }
    
        
    public static void getDoctorDataFromDatabase(String sql,TableView<Doctor>doctorTable) {
        ResultSet rst;
        PreparedStatement pst;        
        try {
            pst = new DatabaseCreate().connectToDB().prepareStatement(sql);            
            rst = pst.executeQuery(); 
            setDoctorToTable(rst,doctorTable);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }         
    }
    
    public static void doctorTableMenu(TableView<Doctor>doctorTable){
       doctorTable.setRowFactory((TableView<Doctor> param) -> {
           final TableRow<Doctor> row = new TableRow<>();
           final ContextMenu rowMenu = new ContextMenu();
           
           MenuItem removeHouse = new MenuItem("Remove doctor");
           
            removeHouse.setOnAction((ActionEvent event) -> {
                int returnedVal;
               try {
                  
                    boolean isok = dialog.ConfirmationAlert("Are you sure you want to remove this doctor?");
                    if(isok == true){
                        doctorTable.getItems().remove(row.getItem());
                        int doctorId = row.getItem().getId();
                        DatabaseCreate dbcreate1 = new DatabaseCreate();
                        returnedVal = dbcreate1.querryDb("DELETE FROM Doctor WHERE Id="+doctorId+" ");
                        if(returnedVal==1){
                            dialog.informationAlert("Removed Successfully");
                        }else{
                            dialog.informationAlert("Failed to remove: Try again");
                        }  
                    }                                    
               }catch (HeadlessException ex) {
                   dialog.informationAlert("connection failure"+ex);
               }
           });           
           rowMenu.getItems().addAll(removeHouse);
           // only display context menu for non-null items:
           row.contextMenuProperty().bind(
                   Bindings.when(Bindings.isNotNull(row.itemProperty()))
                           .then(rowMenu)
                           .otherwise((ContextMenu)null));
           return row;
       });
       
   }
    
    public static void doctorTableClicked(TableView<Doctor>doctorTable,Label doctorId, TextField tf_username, ComboBox<String>serviceCombo, PasswordField tf_password, PasswordField tf_passwordconfirm, ImageView doc_img, TextField tf_loginname){
        doctorTable.setOnMouseClicked((MouseEvent event) -> {            
            if(event.getClickCount()==2){
                Doctor doctor=doctorTable.getSelectionModel().getSelectedItem();
                doctorId.setText(doctor.getId()+"");
                tf_username.setText(doctor.getUserName());
                
                serviceCombo.getSelectionModel().select(doctor.getService());
                
                tf_password.setText(doctor.getPassword());
                tf_passwordconfirm.setText(doctor.getPassword()); 
                tf_loginname.setText(doctor.getloginname());
                Image ssLogo = new Image("file:"+Commonalit.imageTargetPath + doctor.getImgPath(), 148, 150, true, true);
                doc_img.setImage(ssLogo);
                
            }
        });
    }
    
    public static void browseDoctorImage(ImageView doc_img, TextField tf_imageUrl){            
           
            FileChooser fileChooser = new FileChooser(); 
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);              
            File selectedfile = fileChooser.showOpenDialog(null); 
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
    
    public static void setServiceCombo(String querry, ComboBox<String>service_combo){
        ResultSet rst;
        try {
            rst= new DatabaseCreate().getDb(querry);
            while(rst.next()){
                service_combo.getItems().add(rst.getString("Id")+"-"+rst.getString("name"));           
            } 
        } catch (SQLException ex) {
            dialog.informationAlert("Database error! "+ex);
           
        }
    
    }
   
}
