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

public class StemStartPageController
{
   
   @FXML
   private Button aboutButton;

   @FXML
   private Button startButton;
   
   @FXML
   private Label un;
   
   @FXML
   void handleAboutButton(ActionEvent event) throws IOException 
   {  
      //System.out.println(username);
      //System.out.println(username);
      // FXMLLoader loader = new FXMLLoader();
//       loader.setLocation(getClass().getResource("StemHobbiesPage.fxml"));
//       Parent parent = loader.load();
//       Scene scene = new Scene(parent);
//       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//       window.setScene(scene);
//       window.show();
   }

   @FXML
   void handleStartButton(ActionEvent event) throws IOException
   {  
      
      String username = un.getText();
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
   
   public void setUsername(String username){
      un.setText(username);
   }

}
