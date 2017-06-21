/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;


public class MyDialog {
    Stage stage;
    
        public void MessageDialog(String Message){
            Dialogs.create()
            .owner(this.stage)
            .title("Information Message")
            .masthead("Information")
            .message(Message)
            .showInformation();

        }
    
        public void ExceptionDialog(String Message){
            Dialogs.create()
            .owner(this.stage)
            .title("Exception Message")
            .masthead("Problem has occured")
            .message(Message+"!")
            .showError();

        }
        
        public Action ConfirmDialog(String Message){
            Action action=Dialogs.create()
            .owner(this.stage)
            .title("Confirmation Message")
            .masthead("Confirmation Required")
            .message(Message)
            .showConfirm();
            return action;
        }
        
    }
    
