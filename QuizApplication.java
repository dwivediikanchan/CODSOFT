import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {

    private static int score = 0;
    private static int currentQuestionIndex = 0;

    private static String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "What is the largest mammal on Earth?",
            "In what year did Christopher Columbus reach the Americas?",
            "What is the chemical symbol for water?"
    };

    private static String[][] options = {
            {"Berlin", "Madrid", "Paris", "Rome"},
            {"Venus", "Mars", "Jupiter", "Saturn"},
            {"Elephant", "Blue Whale", "Giraffe", "Kangaroo"},
            {"1492", "1620", "1776", "1849"},
            {"H2O", "CO2", "O2", "N2"}
    };

    private static int[] correctAnswers = {2, 1, 1, 0, 0}; // Indices of correct options for each question

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz Application!");

        Timer timer = new Timer();

        // Set the timer task to display questions
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentQuestionIndex < questions.length) {
                    displayQuestion();
                    getUserAnswer(scanner);
                    currentQuestionIndex++;
                } else {
                    displayResult();
                    timer.cancel();
                    scanner.close();
                }
            }
        }, 0, 10000); // Display each question for 10 seconds

    }

    private static void displayQuestion() {
        System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + questions[currentQuestionIndex]);
        for (int i = 0; i < options[currentQuestionIndex].length; i++) {
            System.out.println((i + 1) + ". " + options[currentQuestionIndex][i]);
        }
    }

    private static void getUserAnswer(Scanner scanner) {
        System.out.print("Enter your answer (1-4): ");
        int userAnswer = scanner.nextInt() - 1; // Subtract 1 to get the index

        if (userAnswer >= 0 && userAnswer < options[currentQuestionIndex].length) {
            if (userAnswer == correctAnswers[currentQuestionIndex]) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " +
                        options[currentQuestionIndex][correctAnswers[currentQuestionIndex]]);
            }
        } else {
            System.out.println("Invalid choice. Skipping to the next question.");
        }
    }

    private static void displayResult() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score: " + score + " out of " + questions.length);

        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " +
                    (correctAnswers[i] + 1) + ". " + options[i][correctAnswers[i]]);
        }
    }
}
