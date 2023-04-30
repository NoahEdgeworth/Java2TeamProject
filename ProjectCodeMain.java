package ProjectCode;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Node;


public class ProjectCodeMain extends Application{

   private static QuizController quizController;
   
   public void start(Stage stage) throws IOException{
      Parent root = FXMLLoader.load(getClass().getResource("LoginPageFXML.fxml"));
      

      Scene scene = new Scene(root, 600, 400);
      stage.setScene(scene);
      stage.setTitle("ProjectCode");
      stage.show();
      
      quizController = new QuizController();
      

       
   }
   public static void main(String[] args){
      launch(args);
   }
   
   public static QuizController getQuizController()
   {
      return quizController;
   }
}