package ProjectCode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Node;

public class StemResultsPageController 
{

   

   @FXML
   private TextArea stemArea;

   @FXML
   private TextField stemText;

   @FXML
   void handleStemArea(String paragraph) 
   {
      stemArea.setText(paragraph);
   }

   @FXML
   void handleStemText(String text) 
   {
      stemText.setText(text);
   }
   
   
   public void showResults(int[] selections, StemResultsPageController controller) {
    int numSTEMFields = 4; // assuming there are four STEM fields
    int[] counts = new int[numSTEMFields];
    
    for (int i = 0; i < selections.length; i++) {
        counts[selections[i]-1]++;
    }
    
    int maxCount = 0;
    int maxIndex = -1;
    
    for (int i = 0; i < numSTEMFields; i++) {
        if (counts[i] > maxCount) {
            maxCount = counts[i];
            maxIndex = i;
        }
    }
    
    String[] STEMFields = {"Science", "Technology", "Engineering", "Mathematics"};
    String result = STEMFields[maxIndex];
    
    // update the results screen with the STEM result
    handleStemText(result);
    
    // get the paragraph for the STEM result
    String paragraph = "";
    
    switch (paragraph) {
        case "Science":
            paragraph = "Science is the study of the natural world through observation and experimentation. It encompasses a wide range of fields, including biology, chemistry, physics, and astronomy.";
            break;
        case "Technology":
            paragraph = "Technology is the application of scientific knowledge for practical purposes. It includes the development of tools, machines, and systems that make our lives easier and more efficient.";
            break;
        case "Engineering":
            paragraph = "Engineering is the application of scientific and mathematical principles to design and build structures, machines, and systems. It encompasses a wide range of fields, including mechanical, electrical, civil, and aerospace engineering.";
            break;
        case "Mathematics":
            paragraph = "Mathematics is the study of numbers, quantities, and shapes. It includes a wide range of fields, including algebra, geometry, calculus, and statistics.";
            break;
    }
    
    
    handleStemArea(paragraph);
   }




}
