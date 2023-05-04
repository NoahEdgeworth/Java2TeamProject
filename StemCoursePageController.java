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
   private Label unLabel;
   
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
   
   public void setAnswer(){
      try(Connection connection = DriverManager.getConnection("jdbc:sqlite:ProjectCode/project.db")){
         Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);
         String myQuery = String.format("select Course from Users where email = '%s'"
            , unLabel.getText());
         ResultSet rs = statement.executeQuery(myQuery);
         while(rs.next()){
            String answer = rs.getString(1);
            System.out.println(answer);
            if(answer == null){
               break;
            }
            else if(answer.equals("S")){
               S3.setSelected(true);
            }
            else if(answer.equals("T")){
               T3.setSelected(true);
            }
            else if(answer.equals("E")){
               E3.setSelected(true);
            }
            else if(answer.equals("M")){
               M3.setSelected(true);
            }
         }
      }
         catch(SQLException e){
            System.out.printf("SQL ERROR: %s", e.getMessage());
         }
   }   

   @FXML
   void handleBackButton3(ActionEvent event) throws IOException
   {
      String username = unLabel.getText();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjectCode/StemActivitiesPage.fxml"));
      Parent parent = loader.load();
      StemActivitiesPageController sapc = loader.getController();
      sapc.setUsername(username);
      sapc.setAnswer();
      Scene scene = new Scene(parent, 600, 400);
      Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
   }

   @FXML
   void handleNextButton3(ActionEvent event) throws IOException
   {
      String username = unLabel.getText();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjectCode/StemImpactPage.fxml"));
      Parent parent = loader.load();
      StemImpactPageController sipc = loader.getController();
      sipc.setUsername(username);
      sipc.setAnswer();
      Scene scene = new Scene(parent, 600, 400);
      Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
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
   void handleS3(ActionEvent event) 
   {
      //QuizController.setAnswer(2,"Study of Earth, Animals,Plants");
      saveCourseToDatabase("S");
   }
   
   @FXML
   void handleT3(ActionEvent event) 
   {
      //QuizController.setAnswer(2,"Technology Related");
      saveCourseToDatabase("T");
   }
   
   @FXML
   void handleE3(ActionEvent event) 
   {
      //QuizController.setAnswer(2,"Mechanical Engineering or Building");
      saveCourseToDatabase("E");
   }

   @FXML
   void handleM3(ActionEvent event) 
   {
      //QuizController.setAnswer(2,"Statsitics, Problem Solving, Budgeting");
      saveCourseToDatabase("M");
   }
   
   private void saveCourseToDatabase(String course) {
      String url = "jdbc:sqlite:ProjectCode/project.db";
      String sql = String.format("UPDATE Users SET Course = ? WHERE email = '%s'",
               unLabel.getText());
      
      try (Connection conn = DriverManager.getConnection(url);
          PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setString(1, course);
          pstmt.executeUpdate();
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
   }

   public void setUsername(String username){
      unLabel.setText(username);
      System.out.println(username);
   }

}
