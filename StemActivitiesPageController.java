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
   private Label unLabel;
   
   @FXML
   private ToggleGroup toggleGroup;

   
   @FXML
   public void intialize()
   {
      toggleGroup = new ToggleGroup();
      S2.setToggleGroup(toggleGroup);
      T2.setToggleGroup(toggleGroup);
      E2.setToggleGroup(toggleGroup);
      M2.setToggleGroup(toggleGroup);
      
   }
   
   public void setAnswer(){
      try(Connection connection = DriverManager.getConnection("jdbc:sqlite:ProjectCode/project.db")){
         Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);
         String myQuery = String.format("select Activities from Users where email = '%s'"
            , unLabel.getText());
         ResultSet rs = statement.executeQuery(myQuery);
         while(rs.next()){
            String answer = rs.getString(1);
            System.out.println(answer);
            if(answer == null){
               break;
            }
            else if(answer.equals("S")){
               S2.setSelected(true);
            }
            else if(answer.equals("T")){
               T2.setSelected(true);
            }
            else if(answer.equals("E")){
               E2.setSelected(true);
            }
            else if(answer.equals("M")){
               M2.setSelected(true);
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
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjectCode/StemHobbiesPage.fxml"));
      Parent parent = loader.load();
      StemHobbiesPageController shpc = loader.getController();
      shpc.setUsername(username);
      shpc.setAnswer();
      Scene scene = new Scene(parent, 600, 400);
      Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
   }

   @FXML
   void handleNextButton(ActionEvent event) throws IOException
   {
      String username = unLabel.getText();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjectCode/StemCoursePage.fxml"));
      Parent parent = loader.load();
      StemCoursePageController scpc = loader.getController();
      scpc.setUsername(username);
      scpc.setAnswer();
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
   void handleS2(ActionEvent event) 
   {
      //QuizController.setAnswer(1,"Visit a Zoo/Aquarium");
      saveActivityToDatabase("S");
   }
   
   @FXML
   void handleT2(ActionEvent event) 
   {
      //QuizController.setAnswer(1,"Video Games at Home");
      saveActivityToDatabase("T");
   }

   @FXML
   void handleE2(ActionEvent event) 
   {
      //QuizController.setAnswer(1,"Woodwork/MetalWork");
      saveActivityToDatabase("E");
   }

   @FXML
   void handleM2(ActionEvent event) 
   {
      //QuizController.setAnswer(1,"Playing a game of Chess");
      saveActivityToDatabase("M");
   }
   
   private void saveActivityToDatabase(String activity) {
       String url = "jdbc:sqlite:ProjectCode/project.db";
       String sql = String.format("UPDATE Users SET Activities = ? WHERE email = '%s'",
               unLabel.getText());
 
       try (Connection conn = DriverManager.getConnection(url);
           PreparedStatement pstmt = conn.prepareStatement(sql)) {
           pstmt.setString(1, activity);
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
