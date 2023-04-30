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

public class StemImpactPageController
{

   @FXML
   private RadioButton S4;
   
   @FXML
   private RadioButton T4;
   
   @FXML
   private RadioButton E4;

   @FXML
   private RadioButton M4;

   @FXML
   private Button backButton4;

   @FXML
   private Button nextButton4;

   @FXML
   private Button restartButton;
   
   @FXML
   private ToggleGroup toggleGroup;
   
   private int[] selections = new int[5];
   
   @FXML
   public void initialize() 
   {
      toggleGroup = new ToggleGroup();
      S4.setToggleGroup(toggleGroup);
      T4.setToggleGroup(toggleGroup);
      E4.setToggleGroup(toggleGroup);
      M4.setToggleGroup(toggleGroup);
      
   }


   @FXML
   void handleBackButton4(ActionEvent event) throws IOException
   {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("StemCoursePage.fxml"));
      Parent parent = loader.load();
      Scene scene = new Scene(parent);
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(scene);
      window.show();
   }

   @FXML
   void handleNextButton4(ActionEvent event) throws IOException
   {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("StemFuturePage.fxml"));
      Parent parent = loader.load();
      Scene scene = new Scene(parent);
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(scene);
      window.show();
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
   void handleS4(ActionEvent event) 
   {
      QuizController.setAnswer(3,1);
      saveImpactToDatabase("S4");
   }
   
   @FXML
   void handleT4(ActionEvent event) 
   {
      QuizController.setAnswer(3,2);
      saveImpactToDatabase("T4");
   }
   
   @FXML
   void handleE4(ActionEvent event) 
   {
      QuizController.setAnswer(3,3);
      saveImpactToDatabase("E4");
   }

   @FXML
   void handleM4(ActionEvent event) 
   {
      QuizController.setAnswer(3,4);
      saveImpactToDatabase("M4");
   }
   
   private void saveImpactToDatabase(String impact) {
    String url = "jdbc:sqlite:ProjectCode/project.db";
    String sql = "INSERT INTO Users (Impact) VALUES (?)";

    try (Connection conn = DriverManager.getConnection(url);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, impact);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

}