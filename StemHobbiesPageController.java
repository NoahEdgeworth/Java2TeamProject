package ProjectCode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import java.sql.*;

public class StemHobbiesPageController
{

   @FXML
   private RadioButton S1;

   @FXML
   private RadioButton T1;

   @FXML
   private RadioButton E1;

   @FXML
   private RadioButton M1;

   @FXML
   private Button backButton;

   @FXML
   private Button nextButton;

   @FXML
   private Button restartButton;
   
   @FXML
   private Label unLabel;
   
   @FXML
   private ToggleGroup toggleGroup;
   
   private int[] selections = new int[5];

   
   @FXML
   public void intialize()
   {
      S1.setToggleGroup(toggleGroup);
      T1.setToggleGroup(toggleGroup);
      E1.setToggleGroup(toggleGroup);
      M1.setToggleGroup(toggleGroup);
   }
   
   public void setAnswer(){
      try(Connection connection = DriverManager.getConnection("jdbc:sqlite:ProjectCode/project.db")){
         Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);
         String myQuery = String.format("select Hobbies from Users where email = '%s'"
            , unLabel.getText());
         ResultSet rs = statement.executeQuery(myQuery);
         while(rs.next()){
            String answer = rs.getString(1);
            System.out.println(answer);
            if(answer.equals("S1")){
               S1.setSelected(true);
            }
            else if(answer.equals("T1")){
               T1.setSelected(true);
            }
            else if(answer.equals("E1")){
               E1.setSelected(true);
            }
            else if(answer.equals("M1")){
               M1.setSelected(true);
            }
         }
      }
         catch(SQLException e){
            System.out.printf("SQL ERROR: %s", e.getMessage());
         }
   }
   
   @FXML
   void handleBackButton(ActionEvent event) throws IOException
   {
      String username = unLabel.getText();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjectCode/StemStartPage.fxml"));
      Parent parent = loader.load();
      StemStartPageController sspc = loader.getController();
      sspc.setUsername(username);
      Scene scene = new Scene(parent, 600, 400);
      Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
   }
   
   @FXML
   void handleNextButton(ActionEvent event) throws IOException
   {
      
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("StemActivitiesPage.fxml"));
      Parent parent = loader.load();
      Scene scene = new Scene(parent);
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(scene);
      window.show();
   }
   
   @FXML
   void handleRestartButton(ActionEvent event) throws IOException
   {
      
      String username = unLabel.getText();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjectCode/StemStartPage.fxml"));
      Parent parent = loader.load();
      StemStartPageController sspc = loader.getController();
      sspc.setUsername(username);
      Scene scene = new Scene(parent, 600, 400);
      Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
   }

 @FXML
void handleS1(ActionEvent event) {
    if (S1.isSelected()) {
        selections[0] = 1;
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:ProjectCode/project.db");
            PreparedStatement stmt = conn.prepareStatement(String.format("UPDATE Users SET Hobbies = ? WHERE email = '%s'",
               unLabel.getText()));
            stmt.setString(1, "S1");
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

@FXML
void handleT1(ActionEvent event) {
    if (T1.isSelected()) {
        selections[0] = 2;
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:ProjectCode/project.db");
            PreparedStatement stmt = conn.prepareStatement(String.format("UPDATE Users SET Hobbies = ? WHERE email = '%s'",
               unLabel.getText()));
            stmt.setString(1, "T1");
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

@FXML
void handleE1(ActionEvent event) {
    if (E1.isSelected()) {
        selections[0] = 3;
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:ProjectCode/project.db");
            PreparedStatement stmt = conn.prepareStatement(String.format("UPDATE Users SET Hobbies = ? WHERE email = '%s'",
               unLabel.getText()));
            stmt.setString(1, "E1");
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

@FXML
void handleM1(ActionEvent event) {
    if (M1.isSelected()) {
        selections[0] = 4;
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:ProjectCode/project.db");
            PreparedStatement stmt = conn.prepareStatement(String.format("UPDATE Users SET Hobbies = ? WHERE email = '%s'",
               unLabel.getText()));
            stmt.setString(1, "M1");
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

   public void setUsername(String username){
      unLabel.setText(username);
      System.out.println(username);
   }
   
}
