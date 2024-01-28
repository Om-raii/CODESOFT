
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctOption;

    public QuizQuestion(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class Quiz {
    private QuizQuestion[] questions;
    private int score;
    private int currentQuestionIndex;
    private Timer timer;

    public Quiz(QuizQuestion[] questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.timer = new Timer();
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");

        for (QuizQuestion question : questions) {
            displayQuestion(question);
            startTimer();
            getUserAnswer(question);
            stopTimer();

            System.out.println();
        }

        displayResult();
    }

    private void displayQuestion(QuizQuestion question) {
        System.out.println("Question: " + question.getQuestion());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up! Moving to the next question.");
                nextQuestion();
            }
        }, 10000); // Set timer for 10 seconds (adjust as needed)
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private void getUserAnswer(QuizQuestion question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your answer (1-" + question.getOptions().length + "): ");
        int userAnswer = scanner.nextInt();

        if (userAnswer == question.getCorrectOption()) {
            System.out.println("Correct! You earned a point.");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer was option " + question.getCorrectOption());
        }

        nextQuestion();
    }

    private void nextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            displayQuestion(questions[currentQuestionIndex]);
            startTimer();
        } else {
            displayResult();
        }
    }

    private void displayResult() {
        System.out.println("Quiz completed!");
        System.out.println("Your final score: " + score + "/" + questions.length);
    }
}

public class QuizApp {
    public static void main(String[] args) {
        // Sample quiz questions
        QuizQuestion[] quizQuestions = {
                new QuizQuestion("Who is the father of Computer?", new String[]{"Allen Turing",
                        " Charles Babbage",
                        " Simur Cray",
                        "Augusta Adaming"}, 2),
                new QuizQuestion("The basic operations performed by a computer are__________?", new String[]{ "Arithmetic operation",
                         "Logical operation",
                         "Storage and relative",
                         "All the above"
                }, 4),
                new QuizQuestion("Which of these components are used in a Java program for compilation, debugging, and execution?",new String[]{" JDK","JVM","JRE","JIT"},1),
                new QuizQuestion("What is BigDecimal.ONE?",new String[]
                        {"it is a custom-defined statement", "it is a wrong statement","it is a static variable that has a value of 1 on a scale of 0","it is a static variable that has a value of 1 on a scale of 10",}, 4),
                new QuizQuestion("When an expression consists of int, double, long, float, then the entire expression will get promoted into a data type that is:",new String[]{"int","Float","Double","Long"},3),
                new QuizQuestion("What is it called when the child object also gets killed when the parent object is killed in the program?",new String[]{"Encapsulation",
                        " Association",
                        " Aggregation",
                        " Composition"},4),
                new QuizQuestion("Out of these methods of the String class, which one can be used for testing the strings for equality?",new String[]{"isequals()",
                        "isequal()",
                        "equals()",
                        "equal()"},3),
                new QuizQuestion("What would happen to the thread whenever the garbage collection kicks off?",new String[]{" The garbage collection won’t happen until the running of the thread",
                        "The thread would continue its operation",
                        "The garbage collection and the thread don’t interfere with each other",
                        "The thread would be paused while the running of the garbage collection"},4),
                new QuizQuestion(" Out of these methods of the Object class, which one can clone an object?",new String[]{"Object clone()",
                        "clone()",
                        "Object copy()",
                        "copy()"},1),
                new QuizQuestion("Out of these methods, which one can be used for converting all the characters present in a String into an Array of characters?",new String[]{" both getChars() & toCharArray()",
                        "both charAt() & getChars()",
                        "charAt()",
                        "all of the mentioned"},1)
        };

        Quiz quiz = new Quiz(quizQuestions);
        quiz.startQuiz();
    }
}
