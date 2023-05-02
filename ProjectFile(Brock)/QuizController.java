package ProjectCode;

public class QuizController {
    private static String[] answers = new String[5];

    public static void setAnswer(int questionIndex, String answer) {
        answers[questionIndex] = answer;
    }

    public static String[] getAnswers() {
        return answers;
    }
}
