package ProjectCode;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class ProjectCodeMain extends Application{

   
   public void start(Stage stage) throws IOException{
      Parent root = FXMLLoader.load(getClass().getResource("LoginPageFXML.fxml"));
      Scene scene = new Scene(root, 600, 400);
      stage.setScene(scene);
      stage.setTitle("ProjectCode");
      stage.show();
   }
   
   public static void main(String[] args){
      launch(args);
   }
}