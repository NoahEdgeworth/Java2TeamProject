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


public class StemActivitiesPageController
{

   @FXML
   private RadioButton S2;
   
   @FXML
   private RadioButton T2;
   
   @FXML
   private RadioButton E2;

   @FXML
   private RadioButton M2;

   @FXML
   private Button backButton;

   @FXML
   private Button nextButton;

   @FXML
   private Button restartButton;
   
   @FXML
   private ToggleGroup toggleGroup;
   
   private int[] selections = new int[5];
   
   @FXML
   public void intialize()
   {
      toggleGroup = new ToggleGroup();
      S2.setToggleGroup(toggleGroup);
      T2.setToggleGroup(toggleGroup);
      E2.setToggleGroup(toggleGroup);
      M2.setToggleGroup(toggleGroup);
   }

   @FXML
   void handleBackButton(ActionEvent event) throws IOException
   {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("StemHobbiesPage.fxml"));
      Parent parent = loader.load();
      Scene scene = new Scene(parent);
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(scene);
      window.show(); 
   }

   @FXML
   void handleNextButton(ActionEvent event) throws IOException
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
void handleS2(ActionEvent event) {
    selections[1] = 1;
    saveActivityToDatabase("S2");
}

@FXML
void handleT2(ActionEvent event) {
    selections[1] = 2;
    saveActivityToDatabase("T2");
}

@FXML
void handleE2(ActionEvent event) {
    selections[1] = 3;
    saveActivityToDatabase("E2");
}

@FXML
void handleM2(ActionEvent event) {
    selections[1] = 4;
    saveActivityToDatabase("M2");
}

private void saveActivityToDatabase(String activity) {
    String url = "jdbc:sqlite:ProjectCode/project.db";
    String sql = "INSERT INTO Users (Activities) VALUES (?)";

    try (Connection conn = DriverManager.getConnection(url);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, activity);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}
}
