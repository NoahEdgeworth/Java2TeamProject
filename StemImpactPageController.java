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
   private Label unLabel;
   
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
   
   public void setAnswer(){
      try(Connection connection = DriverManager.getConnection("jdbc:sqlite:ProjectCode/project.db")){
         Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);
         String myQuery = String.format("select Impact from Users where email = '%s'"
            , unLabel.getText());
         ResultSet rs = statement.executeQuery(myQuery);
         while(rs.next()){
            String answer = rs.getString(1);
            System.out.println(answer);
            if(answer == null){
               break;
            }
            else if(answer.equals("S")){
               S4.setSelected(true);
            }
            else if(answer.equals("T")){
               T4.setSelected(true);
            }
            else if(answer.equals("E")){
               E4.setSelected(true);
            }
            else if(answer.equals("M")){
               M4.setSelected(true);
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
   void handleNextButton4(ActionEvent event) throws IOException
   {
      String username = unLabel.getText();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjectCode/StemFuturePage.fxml"));
      Parent parent = loader.load();
      StemFuturePageController sfpc = loader.getController();
      sfpc.setUsername(username);
      sfpc.setAnswer();
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
   void handleS4(ActionEvent event) 
   {
      //QuizController.setAnswer(3,"Study of Enviroment, plant, and animal life");
      saveImpactToDatabase("S");
   }
   
   @FXML
   void handleT4(ActionEvent event) 
   {
      //QuizController.setAnswer(3,"The advancements of technology");
      saveImpactToDatabase("T");
   }
   
   @FXML
   void handleE4(ActionEvent event) 
   {
      //QuizController.setAnswer(3,"Development of Engineering Machines");
      saveImpactToDatabase("E");
   }

   @FXML
   void handleM4(ActionEvent event) 
   {
      //QuizController.setAnswer(3,"Education of Mathematics");
      saveImpactToDatabase("M");
   }
   
   private void saveImpactToDatabase(String impact) {
      String url = "jdbc:sqlite:ProjectCode/project.db";
      String sql = String.format("UPDATE Users SET Impact = ? WHERE email = '%s'",
               unLabel.getText());
       
      try (Connection conn = DriverManager.getConnection(url);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
         pstmt.setString(1, impact);
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