package ProjectCode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.sql.*;

public class LoginPageController {

    @FXML
    private Label errorLabel;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signupButton;

    @FXML
    private TextField usernameField;

    @FXML
    void loginHandler(ActionEvent event)throws IOException {
      if(loginCheck() == true){
         Parent parent = FXMLLoader.load(getClass().getResource("/ProjectCode/StemStartPage.fxml"));
         Scene scene = new Scene(parent, 600, 400);
         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      
         stage.setScene(scene);
         stage.show();
      }
    }

    @FXML
    void passwordHandler(ActionEvent event) {

    }

    @FXML
    void signupHandler(ActionEvent event)throws IOException {
      Parent parent = FXMLLoader.load(getClass().getResource("/ProjectCode/SignupPageFXML.fxml"));
      Scene scene = new Scene(parent, 600, 500);
      Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      
      stage.setScene(scene);
      stage.show();
    }

    @FXML
    void usernameHandler(ActionEvent event) {

    }
    
    //this will later check to see if username and passwords are correct
    public boolean loginCheck(){
      errorLabel.setVisible(false);
      try(Connection connection = DriverManager.getConnection("jdbc:sqlite:ProjectCode/project.db")){
         Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);
         String myQuery = String.format("select email, password from Users where email like '%s' and"
            + " password like '%s'", usernameField.getText(), passwordField.getText());
         ResultSet rs = statement.executeQuery(myQuery);
         while(rs.next()){
            String email = rs.getString(1);
            String password = rs.getString(2);
            if(email.equals(usernameField.getText()) && password.equals(passwordField.getText())){
               return true;
               }
            }
         }
         catch(SQLException e){
            System.out.printf("SQL ERROR: %s", e.getMessage());
         }
      errorLabel.setVisible(true);
      return false;
    }

}
