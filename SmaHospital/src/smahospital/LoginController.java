
package smahospital;

import databaseworks.DatabaseCreate;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import manipulater.Commonalit;

public class LoginController extends Commonalit implements Initializable {   
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private Button btnLogin;
    @FXML
    private Pane loginpane;
    @FXML
    private Label welcometext;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnLogin.visibleProperty().bind(Bindings.isNotEmpty(username.textProperty()));
        btnLogin.disableProperty().bind(Bindings.isEmpty(password.textProperty()));         
        Image imag= new Image(getClass().getResourceAsStream("/images/user-login-icon.png"));
        btnLogin.setGraphic(new ImageView(imag));
        btnLogin.setPadding(new Insets(1,35,1,0));
        
        //Reflection for gridPane
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        loginpane.setEffect(r);
       
        //focusing the cursor on the username textfield
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                username.requestFocus();
            }
        });      
        
    }   
    
        
    @FXML
    void loginClicked(ActionEvent event) {
        ResultSet rst;        
        try{
               if(username.getText().isEmpty() || password.getText().isEmpty()){
                    dialog.informationAlert("Both User name and Password are required");
                  }else{ 
                      String myusername=username.getText().trim();
                      String mypassword=password.getText().trim();
                      rst=new DatabaseCreate().getDb("SELECT * FROM User WHERE username='"+myusername+"'AND password='"+mypassword+"' ");

                      if(rst.next()){
                          if(rst.getString("Level").equalsIgnoreCase("admin")){
                            Stage stage=(Stage) btnLogin.getScene().getWindow();
                            stage.close();             
                            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); 
                            Scene scene = new Scene(root);
                            Stage staged=new Stage();
                            staged.setScene(scene);
                            Image icon = new Image(getClass().getResourceAsStream("/images/users-icon.png"));
                            staged.getIcons().add(icon);
                            staged.setMaxHeight(600);
                            staged.setMaxWidth(1050);
                            staged.setTitle("SMA");
                            staged.setResizable(false);                    
                            staged.show(); 
                          }else{
                              dialog.informationAlert("No administrator has these login credentials");
                          }
                      }else{
                          dialog.informationAlert("Wrong Login credentials used");
                      } 
                  } 
        } catch (SQLException | IOException  ex) {
                    dialog.informationAlert("A problem has occured! contact your system Administrator");
        }
        
    }
   
}
