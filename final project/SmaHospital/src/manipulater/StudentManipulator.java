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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import objects.Student;

public class StudentManipulator {
    static ObservableList<Student> studentList=FXCollections.observableArrayList();
    
    public static void FormulateStudentDetailsTable(TableView<Student>studentTable){
        try{
            TableColumn studentid= new TableColumn("STUDENT ID");
            studentid.setMinWidth(100);
            studentid.setCellValueFactory(new PropertyValueFactory<>("studentId"));

            TableColumn surname= new TableColumn("SUR NAME");
            surname.setMinWidth(200);
            surname.setCellValueFactory(new PropertyValueFactory<>("surName")); 
            
            TableColumn othername= new TableColumn("OTHER NAME");
            othername.setMinWidth(200);
            othername.setCellValueFactory(new PropertyValueFactory<>("otherName"));

            TableColumn gender= new TableColumn("GENDER");
            gender.setMinWidth(150);
            gender.setCellValueFactory(new PropertyValueFactory<>("gender")); 
            
            TableColumn residence= new TableColumn("RESIDENT OF");
            residence.setMinWidth(200);
            residence.setCellValueFactory(new PropertyValueFactory<>("residence")); 
            
            studentTable.getColumns().addAll(studentid,surname,othername,gender,residence);
        }catch(Exception ex){
        }        
    }
    
    private static Student fetchStudentDataFromDb(ResultSet rst){
            Student obj=null;
        try {            
            String studentId = rst.getString("StudentNo");
            String surname = rst.getString("SurName");
            String othername = rst.getString("OtherName");  
            String gender = rst.getString("Gender");
            String residence = rst.getString("Residence");  
            
            obj=new Student(studentId, surname, othername, gender, residence);
            return obj;
        } catch (SQLException ex) {            
            return obj;
        }
        
    }
    
    private static void setStudentToTable(ResultSet rst,TableView<Student>studentTable){
       studentList.clear();
       try {
           while (rst.next()) {              
               studentList.add(fetchStudentDataFromDb(rst));               
           }
           studentTable.setItems(studentList);
       } catch (SQLException ex) {    
           ex.printStackTrace();
       }            
    }
    
        
    public static void getStudentDataFromDatabase(String sql,TableView<Student>studentTable) {
        ResultSet rst;
        PreparedStatement pst;        
        try {
            pst = new DatabaseCreate().connectToDB().prepareStatement(sql);            
            rst = pst.executeQuery(); 
            setStudentToTable(rst,studentTable);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }         
    }
    
    public static void studentTableMenu(TableView<Student>studentTable){
       studentTable.setRowFactory((TableView<Student> param) -> {
           final TableRow<Student> row = new TableRow<>();
           final ContextMenu rowMenu = new ContextMenu();
           
           MenuItem removeHouse = new MenuItem("Remove Student");
           
            removeHouse.setOnAction((ActionEvent event) -> {
                int returnedVal;
               try {
                   int response;
                    response=JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this student?", "Confirmation Dialog", JOptionPane.YES_OPTION);
                    if(response==JOptionPane.YES_OPTION){
                        studentTable.getItems().remove(row.getItem());
                        String studentId = row.getItem().getStudentId();
                        DatabaseCreate dbcreate1 = new DatabaseCreate();
                        returnedVal = dbcreate1.querryDb("DELETE FROM Student WHERE StudentNo='"+studentId+"' ");
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
}
