/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulater;

import databaseworks.DatabaseCreate;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import objects.Doctor;


public class DoctorManipulator {
    static ObservableList<Doctor> doctorList=FXCollections.observableArrayList(); 
    
     public static boolean checkEmptyFields(TextField tf_username, TextField tf_service, PasswordField tf_password, PasswordField tf_passwordconfirm) {
        boolean isempty = false;
        if(tf_username.getText().equals("") || tf_service.getText().equals("") || tf_passwordconfirm.getText().equals("") || tf_password.getText().equals("")){
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
            pst.setInt(4, doctor.getId());
            pst.execute();     
            pst.closeOnCompletion();
            updated=true;
        }catch(SQLException ex){            
        }
        return updated;
    }

    public static void clearFields(TextField tf_username, TextField tf_service, PasswordField tf_password, PasswordField tf_passwordconfirm) {
        try{
            tf_username.clear();
            tf_service.clear();
            tf_password.clear();
            tf_passwordconfirm.clear();
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

            doctorTable.getColumns().addAll(userName,service);
        }catch(Exception ex){
        }        
    }
     
     private static Doctor fetchDoctorDataFromDb(ResultSet rst){
            Doctor obj=null;
        try {          
            int id=rst.getInt("Id");
            String username=rst.getString("UserName");
            String service=rst.getString("Service");
            String password=rst.getString("Password");            
            obj=new Doctor(id, username, password, service);
            return obj;
        } catch (SQLException ex) {            
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
       }            
    }
    
        
    public static void getDoctorDataFromDatabase(String sql,TableView<Doctor>doctorTable) {
        ResultSet rst;
        PreparedStatement pst;        
        try {
            pst = new DatabaseCreate().connectToDB().prepareStatement(sql);            
            rst = pst.executeQuery(); 
            setDoctorToTable(rst,doctorTable);
        } catch (SQLException e) {
            
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
                   int response;
                    response=JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this doctor?", "Confirmation Dialog", JOptionPane.YES_OPTION);
                    if(response==JOptionPane.YES_OPTION){
                        doctorTable.getItems().remove(row.getItem());
                        int doctorId = row.getItem().getId();
                        DatabaseCreate dbcreate1 = new DatabaseCreate();
                        returnedVal = dbcreate1.querryDb("DELETE FROM Doctor WHERE Id="+doctorId+" ");
                        if(returnedVal==1){
                            JOptionPane.showMessageDialog(null, "Removed Successfully");
                        }else{
                            JOptionPane.showMessageDialog(null, "Failed to remove: Try again");
                        }   
                    }                   
               }catch (HeadlessException ex) {
                   JOptionPane.showMessageDialog(null, "connection failure"+ex);
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
    
    public static void doctorTableClicked(TableView<Doctor>doctorTable,Label doctorId, TextField tf_username, TextField tf_service, PasswordField tf_password, PasswordField tf_passwordconfirm){
        doctorTable.setOnMouseClicked((MouseEvent event) -> {            
            if(event.getClickCount()==2){
                Doctor doctor=doctorTable.getSelectionModel().getSelectedItem();
                doctorId.setText(doctor.getId()+"");
                tf_username.setText(doctor.getUserName());
                tf_service.setText(doctor.getService());
                tf_password.setText(doctor.getPassword());
                tf_passwordconfirm.setText(doctor.getPassword());            
            }
        });
    }
   
}
