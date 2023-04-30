package ProjectCode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StemFuturePageController
{



   @FXML
   private RadioButton S5;
   
   @FXML
   private RadioButton T5;
   
   @FXML
   private RadioButton E5;

   @FXML
   private RadioButton M5;

   @FXML
   private Button backButton4;

   @FXML
   private Button resultsButton;

   @FXML
   private Button restartButton;
   
   @FXML
   private ToggleGroup toggleGroup;
   
   private int[] selections = new int[5];
   
   
   @FXML
   public void intialize()
   {
      toggleGroup = new ToggleGroup();
      S5.setToggleGroup(toggleGroup);
      T5.setToggleGroup(toggleGroup);
      E5.setToggleGroup(toggleGroup);
      M5.setToggleGroup(toggleGroup);
     
   }
   

   @FXML
   void handleBackButton4(ActionEvent event) throws IOException
   {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("StemImpactPage.fxml"));
      Parent parent = loader.load();
      
      Scene scene = new Scene(parent);
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(scene);
      window.show(); 
   }

   @FXML
   private void handleResultsButton(ActionEvent event) throws IOException 
   {
      
      FXMLLoader loader = new FXMLLoader(getClass().getResource("StemResultsPage.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
      
   }
   
   
   
   @FXML
   void handleRestartButton(ActionEvent event) throws IOException
   {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("StemStartPage.fxml"));
      Parent parent = loader.load();
      Scene scene = new Scene(parent);
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(scene);
      window.show(); 
   }
   
   @FXML
   void handleS5(ActionEvent event) 
   {
      QuizController.setAnswer(4,1);
      saveFutureToDatabase("S5");
   }
   
   @FXML
   void handleT5(ActionEvent event) 
   {
      QuizController.setAnswer(4,2);
      saveFutureToDatabase("T5");
   }
   
   @FXML
   void handleE5(ActionEvent event) 
   {
      QuizController.setAnswer(4,3);
      saveFutureToDatabase("E5");
   }

   @FXML
   void handleM5(ActionEvent event) 
   {
      QuizController.setAnswer(4,4);
      saveFutureToDatabase("M5");
   }
   
   private void saveFutureToDatabase(String future) {
    String url = "jdbc:sqlite:ProjectCode/project.db";
    String sql = "INSERT INTO Users (Future) VALUES (?)";

    try (Connection conn = DriverManager.getConnection(url);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, future);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

}