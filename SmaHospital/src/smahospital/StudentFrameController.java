/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smahospital;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import manipulater.Commonalit;
import manipulater.StudentManipulator;
import objects.Student;

/**
 * FXML Controller class
 *
 * @author Eng.Frank
 */
public class StudentFrameController extends Commonalit implements Initializable {

    @FXML
    private TableView<Student> table_students;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            StudentManipulator.FormulateStudentDetailsTable(table_students);
            StudentManipulator.studentTableMenu(table_students);
            StudentManipulator.getStudentDataFromDatabase("SELECT * FROM student", table_students);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        }    
    
}
