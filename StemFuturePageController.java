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
   private Label unLabel;
   
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
   
   public void setAnswer(){
      try(Connection connection = DriverManager.getConnection("jdbc:sqlite:ProjectCode/project.db")){
         Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);
         String myQuery = String.format("select Future from Users where email = '%s'"
            , unLabel.getText());
         ResultSet rs = statement.executeQuery(myQuery);
         while(rs.next()){
            String answer = rs.getString(1);
            System.out.println(answer);
            if(answer == null){
               break;
            }
            else if(answer.equals("S")){
               S5.setSelected(true);
            }
            else if(answer.equals("T")){
               T5.setSelected(true);
            }
            else if(answer.equals("E")){
               E5.setSelected(true);
            }
            else if(answer.equals("M")){
               M5.setSelected(true);
            }
         }
      }
         catch(SQLException e){
            System.out.printf("SQL ERROR: %s", e.getMessage());
         }
   } 
   

   @FXML
   void handleBackButton4(ActionEvent event) throws IOException
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
   void handleResultsButton(ActionEvent event) throws IOException 
   {     
      String username = unLabel.getText();
      System.out.println("this should pass username " + username);
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjectCode/StemResultsPage.fxml"));
      Parent parent = loader.load();
      StemResultsPageController srpc = loader.getController();
      srpc.setUsername(username);
      srpc.showResults();
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
   void handleS5(ActionEvent event) 
   {
      //QuizController.setAnswer(4,"Exploring the world around us");
      saveFutureToDatabase("S");
   }
   
   @FXML
   void handleT5(ActionEvent event) 
   {
      //QuizController.setAnswer(4,"Creating/Working on technology");
      saveFutureToDatabase("T");
   }
   
   @FXML
   void handleE5(ActionEvent event) 
   {
      //QuizController.setAnswer(4,"Building/Working on anything mechanical");
      saveFutureToDatabase("E");
   }

   @FXML
   void handleM5(ActionEvent event) 
   {
      //QuizController.setAnswer(4,"Teaching or applying mathematical theories");
      saveFutureToDatabase("M");
   }
   
   private void saveFutureToDatabase(String future) {
      String url = "jdbc:sqlite:ProjectCode/project.db";
      String sql = String.format("UPDATE Users SET Future = ? WHERE email = '%s'",
               unLabel.getText());
      
      try (Connection conn = DriverManager.getConnection(url);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
         pstmt.setString(1, future);
         pstmt.executeUpdate();
      } 
      catch (SQLException e) {
         System.out.println(e.getMessage());
      }
   }

   public void setUsername(String username){
      unLabel.setText(username);
      System.out.println(username);
   }
   
}