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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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


    public void showResults(String[] selections) {
    
         
    String url = "jdbc:sqlite:ProjectCode/project.db";
    String sql = "INSERT INTO Users (Hobbies,Activities,Course, Impact, Future) VALUES (?, ?, ?, ?, ?)";

    try {
         Connection conn = DriverManager.getConnection(url);
         PreparedStatement pstmt = conn.prepareStatement(sql);
         
         for (int i = 0; i < selections.length; i++) {
                pstmt.setString(i+1, selections[i]);
            } 
         
        
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }

    
        int sCount = 0;
        int tCount = 0;
        int eCount = 0;
        int mCount = 0;

        for (int i = 0; i < selections.length; i++) {
            if (selections[i].equals("S" + (i + 1))) {
                sCount++;
            } else if (selections[i].equals("T" + (i + 1))) {
                tCount++;
            } else if (selections[i].equals("E" + (i + 1))) {
                eCount++;
            } else if (selections[i].equals("M" + (i + 1))) {
                mCount++;
            }
        }

        String result = "";
        String name = "";

        if (sCount >= tCount && sCount >= eCount && sCount >= mCount) {
            result = "Biologists:\n\nBiologists study humans, plants, animals, and the environments in which they live. They may conduct their studies--human medical research,"+
                     "plant research, animal research, environmental system research--at the cellular level or the ecosystem level or anywhere in between." +
                     "\n\nZoologist:\nZoologist study animals and other wildlife and how they interact with their ecosystems. They study the physical characteristics of animals," +
                     "animal behaviors, and the impacts humans have on wildlife and natural habitats." + 
                     "\n\nGeoscientist:\nGeoscientists study the physical aspects of the Earth, such as its composition, structure, and processes, to learn about its past and" + 
                     "present and to predict future events.";
            name = "Science";
        } else if (tCount >= sCount && tCount >= eCount && tCount >= mCount) {
            result = "Software Engineer: \nSoftware engineers design, develop, and maintain software applications and systems. Programming languages and computer science " +
                     "principles to create programs that are efficient, reliable, and user-friendly." + 
                     "\n\nData Scientist: \nData scientists are professionals who analyze and interpret complex data using statistical and machine learning models to uncover" + 
                     "insights and solve problems. They work with large sets of structured and unstructured data, programming languages like Python and R, and data visualization" +
                     "tools to make data-driven decisions." + 
                     "\n\nFull Stack Developer: \nFull stack developers are software engineers who work on both the front-end and back-end of web applications. They are responsible" + 
                     "for designing, developing, and maintaining web applications from start to finish. This includes tasks such as writing code in programming languages like" +
                     " HTML, CSS, and JavaScript, configuring servers and databases, and creating user interfaces.";
            name = "Technology";
        } else if (eCount >= sCount && eCount >= tCount && eCount >= mCount) {
            result = "Civil Engineer: \nCivil engineers design, build, and maintain infrastructure. They use their knowledge of physics, materials science, and mathematics." + 
                     "\n\nMechanical Engineer: \nMechanical engineers design, develop, and test mechanical devices physics, materials science, and mathematics to create solutions to " +
                     "problems in various industries." +
                     "\n\nElectrical Engineer: \nElectrical engineers design, develop distribution systems, electric motors, and communication systems. ";
            name = "Engineering";
        } else if (mCount >= sCount && mCount >= tCount && mCount >= eCount) {
            result = "Mathematicians: \nMathematicians help organizations by applying mathematical theories and techniques to solve practical problems. They collect data, analyze this" +
                     "data and present their findings with the aim of solving practical problems in business, government, engineering and science." + 
                     "\n\nMath Teacher: \nA math teacher instructs students in the broad field of mathematics. This can include general math and/or any of the fields within mathematics such" +
                     "as algebra, geometry, statistics, and calculus." + 
                     "\n\nEconomists: \nEconomists prepare reports, tables, and charts. Economists study the production and distribution of resources, goods, and services by collecting and analyzing " +
                     "data, researching trends, and evaluating economic issues.";
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

