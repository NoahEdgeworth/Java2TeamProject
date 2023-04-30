package ProjectCode;

public class QuizController {
    private static int[] answers = new int[5];

    public static void setAnswer(int questionIndex, int answer) {
        answers[questionIndex] = answer;
    }

    public static int[] getAnswers() {
        return answers;
    }
}
