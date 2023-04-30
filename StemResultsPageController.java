package ProjectCode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.scene.Node;

import java.io.IOException;

public class StemResultsPageController {

    @FXML
    private TextArea stemArea;

    @FXML
    private TextField stemText;
    
    @FXML 
    private Button restartButton;


    @FXML
    void initialize() throws IOException {
        showResults(QuizController.getAnswers());
    }


    public void showResults(int[] selections) {
        int sCount = 0;
        int tCount = 0;
        int eCount = 0;
        int mCount = 0;

        for (int i = 0; i < selections.length; i++) {
            if (selections[i] == 1) {
                sCount++;
            } else if (selections[i] == 2) {
                tCount++;
            } else if (selections[i] == 3) {
                eCount++;
            } else if (selections[i] == 4) {
                mCount++;
            }
        }

        String result = "";
        String name = "";

        if (sCount >= tCount && sCount >= eCount && sCount >= mCount) {
            result = "You have a strong interest in Science. Consider pursuing a career in fields such as Biology, Chemistry, or Physics.";
            name = "Science";
        } else if (tCount >= sCount && tCount >= eCount && tCount >= mCount) {
            result = "You have a strong interest in Technology. Consider pursuing a career in fields such as Computer Science, Information Technology, or Engineering.";
            name = "Technology";
        } else if (eCount >= sCount && eCount >= tCount && eCount >= mCount) {
            result = "You have a strong interest in Engineering. Consider pursuing a career in fields such as Civil Engineering, Mechanical Engineering, or Electrical Engineering.";
            name = "Engineering";
        } else if (mCount >= sCount && mCount >= tCount && mCount >= eCount) {
            result = "You have a strong interest in Math. Consider pursuing a career in fields such as Actuarial Science, Financial Analysis, or Mathematics Research.";
            name = "Mathematics";
        }

        stemArea.setText(result);
        stemText.setText(name);
    }


    public void handleStemText(ActionEvent actionEvent) 
    {

    }

    public void handleStemArea(MouseEvent event) 
    {
    }
    
    public void handleRestartButton(ActionEvent event) throws IOException
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("StemStartPage.fxml"));
      Parent parent = loader.load();
      Scene scene = new Scene(parent);
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(scene);
      window.show();
    }
}

