package ProjectCode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Node;

public class StemStartPageController
{
   public GrabUsername gu = new GrabUsername();
   
   @FXML
   private Button aboutButton;

   @FXML
   private Button startButton;

   @FXML
   void handleAboutButton(ActionEvent event) throws IOException 
   {
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
      String username = gu.getUsername();
      System.out.println(username);
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("StemHobbiesPage.fxml"));
      Parent parent = loader.load();
      Scene scene = new Scene(parent);
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(scene);
      window.show();   
   }

}
