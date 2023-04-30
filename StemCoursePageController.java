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

public class StemCoursePageController
{

   @FXML
   private RadioButton S3;
   
   @FXML
   private RadioButton T3;
   
   @FXML
   private RadioButton E3;

   @FXML
   private RadioButton M3;

   @FXML
   private Button backButton3;

   @FXML
   private Button nextButton3;

   @FXML
   private Button restartButton;
   
   @FXML
   private ToggleGroup toggleGroup;
   
   private int[] selections = new int[5];
   
   @FXML
   public void initialize() 
   {
      toggleGroup = new ToggleGroup();
      S3.setToggleGroup(toggleGroup);
      T3.setToggleGroup(toggleGroup);
      E3.setToggleGroup(toggleGroup);
      M3.setToggleGroup(toggleGroup);
      
   }

   @FXML
   void handleBackButton3(ActionEvent event) throws IOException
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
   void handleNextButton3(ActionEvent event) throws IOException
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
   void handleS3(ActionEvent event) 
   {
      QuizController.setAnswer(2,1);
      saveCourseToDatabase("S3");
   }
   
   @FXML
   void handleT3(ActionEvent event) 
   {
      QuizController.setAnswer(2,2);
      saveCourseToDatabase("T3");
   }
   
   @FXML
   void handleE3(ActionEvent event) 
   {
      QuizController.setAnswer(2,3);
      saveCourseToDatabase("E3");
   }

   @FXML
   void handleM3(ActionEvent event) 
   {
      QuizController.setAnswer(2,4);
      saveCourseToDatabase("M3");
   }
   
   private void saveCourseToDatabase(String course) {
    String url = "jdbc:sqlite:ProjectCode/project.db";
    String sql = "INSERT INTO Users (Course) VALUES (?)";

    try (Connection conn = DriverManager.getConnection(url);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, course);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

}
