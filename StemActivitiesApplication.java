package ProjectCode;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;


public class StemActivitiesApplication extends Application
{
   @Override
   public void start(Stage stage) throws IOException
   {
      Parent root = FXMLLoader.load(getClass().getResource("StemStartPage.fxml"));
      Scene scene = new Scene(root, 600, 400);
      stage.setScene(scene);
      stage.setTitle("");
      stage.show();
   }
   
   public static void main(String[] args)
   {
      launch(args);
   }
}