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
import objects.Student;

public class StudentManipulator extends Commonalit{
    static ObservableList<Student> studentList=FXCollections.observableArrayList();
    
    
    //add here code
    public static void FormulateStudentDetailsTable(TableView<Student>studentTable){
        try{
            TableColumn studentid= new TableColumn("STUDENT ID");
            studentid.setMinWidth(100);
            studentid.setCellValueFactory(new PropertyValueFactory<>("studentId"));

            TableColumn name= new TableColumn("NAME");
            name.setMinWidth(200);
            name.setCellValueFactory(new PropertyValueFactory<>("name"));             

            TableColumn gender= new TableColumn("GENDER");
            gender.setMinWidth(150);
            gender.setCellValueFactory(new PropertyValueFactory<>("gender")); 
            
            TableColumn residence= new TableColumn("RESIDENT OF");
            residence.setMinWidth(200);
            residence.setCellValueFactory(new PropertyValueFactory<>("residence")); 
            
            TableColumn regNo= new TableColumn("REG NO");
            regNo.setMinWidth(200);
            regNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
            
             TableColumn course= new TableColumn("COURSE");
            course.setMinWidth(200);
            course.setCellValueFactory(new PropertyValueFactory<>("course"));
            
            TableColumn tel= new TableColumn("TELEPHONE NO");
            tel.setMinWidth(200);
            tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            
            TableColumn email= new TableColumn("EMAIL");
            email.setMinWidth(200);
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            
            TableColumn studentNo= new TableColumn("STUDENT NO");
            studentNo.setMinWidth(200);
            studentNo.setCellValueFactory(new PropertyValueFactory<>("studentNo"));
            
            studentTable.getColumns().addAll(studentid,name,gender,residence,regNo,course,tel,email,studentNo);
        }catch(Exception ex){
        }        
    }
    
    
    //Add here code
    private static Student fetchStudentDataFromDb(ResultSet rst){
            Student obj=null;
        try {            
            String studentId = rst.getString("StudentNo");
            String name = rst.getString("name");  
            String gender = rst.getString("Gender");
            String residence = rst.getString("Residence");  
            String regNo = rst.getString("regNo");
            String course = rst.getString("course");
            String tel = rst.getString("tel");
            String email = rst.getString("email");
            String studentNo = rst.getString("studentNo");
            
            obj=new Student(studentId, name, gender, residence, regNo, course, tel, email, studentNo);
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
                   boolean isok = dialog.ConfirmationAlert("Are you sure you want to remove this student?");
                   if(isok == true){
                        studentTable.getItems().remove(row.getItem());
                        String studentId = row.getItem().getStudentId();
                        DatabaseCreate dbcreate1 = new DatabaseCreate();
                        returnedVal = dbcreate1.querryDb("DELETE FROM Student WHERE StudentNo='"+studentId+"' ");
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
}
