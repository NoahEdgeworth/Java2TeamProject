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

public class SignupPageController {

    @FXML
    private Button cancelButton;

    @FXML
    private Label emailEx1;

    @FXML
    private Label emailEx2;

    @FXML
    private TextField emailField;

    @FXML
    private Label fieldEx1;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordConfField;

    @FXML
    private Label passwordEx1;

    @FXML
    private Label passwordEx2;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    void cancelHandler(ActionEvent event)throws IOException {
      Parent parent = FXMLLoader.load(getClass().getResource("/ProjectCode/LoginPageFXML.fxml"));
      Scene scene = new Scene(parent, 600, 400);
      Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      
      stage.setScene(scene);
      stage.show();
    }

    @FXML
    void emailHandler(ActionEvent event) {

    }

    @FXML
    void nameHandler(ActionEvent event) {

    }

    @FXML
    void passwordConfHandler(ActionEvent event) {

    }

    @FXML
    void passwordHandler(ActionEvent event) {

    }

    @FXML
    void signUpHandler(ActionEvent event)throws IOException {
      if(allErrorCheck() == true){
      
         try(Connection connection = DriverManager.getConnection("jdbc:sqlite:ProjectCode/project.db")){
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(String.format("insert into Users values('%s', '%s', '%s'" +
               ", NULL, NULL, NULL, NULL, NULL,NULL)",
               nameField.getText(), emailField.getText(), passwordField.getText()));
         }
         catch(SQLException e){
            System.out.printf("SQL ERROR: %s", e.getMessage());
         }
           
         Parent parent = FXMLLoader.load(getClass().getResource("/ProjectCode/LoginPageFXML.fxml"));
         Scene scene = new Scene(parent, 600, 400);
         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      
         stage.setScene(scene);
         stage.show();
         
      }
      
    }
    
    //Checks password meets requirements
    public boolean passwordCheck1(){
      String password = passwordField.getText();
      boolean hasUppercase = !password.equals(password.toLowerCase());
      boolean hasLowercase = !password.equals(password.toUpperCase());
      boolean isAtLeast6 = password.length() >= 6;
      boolean hasNumber = password.matches(".*\\d.*");
            
      if(hasUppercase && hasLowercase && hasNumber && isAtLeast6 == true) {
         return true;
      }
      else{ 
         return false;
      }
    }
    
    //checks that passwords match
    public boolean passwordCheck2(){
      if(passwordField.getText().equals(passwordConfField.getText())){
         return true;
      }
      else{
         return false;
      }
    }
    
    //checks for empty fields
    public boolean fieldCheck1(){
      if(passwordField.getText().equals("") || passwordConfField.getText().equals("") 
                                            || nameField.getText().equals("") || emailField.getText().equals("")){
         return false;
      }
      return true;
    }
    
    //this will check if email in use
    public boolean emailCheck1(){
      try(Connection connection = DriverManager.getConnection("jdbc:sqlite:ProjectCode/project.db")){
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String myQuery = String.format("select Email from Users where Email like '%s'", emailField.getText());
            ResultSet rs = statement.executeQuery(myQuery);
            while(rs.next()){
               String email = rs.getString(1);
               if(email.equals(emailField.getText())){
                  return false;
               }
            }
         }
         catch(SQLException e){
            System.out.printf("SQL ERROR: %s", e.getMessage());
         }
      return true;
    }
    
    //this will check if it is an email
    public boolean emailCheck2(){
      return true;
    }
    
    //checks all errors
    public boolean allErrorCheck(){
      makeInvis();
      if(fieldCheck1() == false){
         fieldEx1.setVisible(true);
         return false;
      }
      else if(passwordCheck1() == false){
         passwordEx1.setVisible(true);
         return false;
      }
      else if(passwordCheck2() == false){
         passwordEx2.setVisible(true);
         return false;
      }
      else if(emailCheck1() == false){
         emailEx1.setVisible(true);
         return false;
      }
      else if(emailCheck2() == false){
         emailEx2.setVisible(true);
         return false;
      }
      else{
         return true;
      }         
    }
    
    //makes error labels invisible
    public void makeInvis(){
      passwordEx1.setVisible(false);
      passwordEx2.setVisible(false);
      fieldEx1.setVisible(false);
      emailEx1.setVisible(false);
      emailEx2.setVisible(false);
    }

}
