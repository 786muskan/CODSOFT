import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Question {
    String questionText;
    String[] options;
    int correctOption;

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

public class QuizWithTimer extends JFrame {
    private ArrayList<Question> questions;
    private int score = 0;
    private int questionIndex = 0;
    private int timeLeft = 10;
    private Timer timer;

    private JLabel questionLabel;
    private JButton[] optionButtons;
    private JLabel timerLabel;
    private JButton startButton, exitButton;

    public QuizWithTimer() {
        setTitle("Java Quiz with Timer");
        setSize(500, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Top Panel — Timer
        timerLabel = new JLabel("Time: 10", JLabel.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(timerLabel, BorderLayout.NORTH);

        // Center Panel — Question and Options
        JPanel centerPanel = new JPanel(new GridLayout(5, 1));
        questionLabel = new JLabel("Click 'Start Quiz' to begin", JLabel.CENTER);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        centerPanel.add(questionLabel);

        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setVisible(false);
            final int idx = i;
            optionButtons[i].addActionListener(e -> checkAnswer(idx));
            centerPanel.add(optionButtons[i]);
        }

        add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel — Start and Exit
        JPanel bottomPanel = new JPanel();
        startButton = new JButton("Start Quiz");
        exitButton = new JButton("Exit");
        bottomPanel.add(startButton);
        bottomPanel.add(exitButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Events
        startButton.addActionListener(e -> startQuiz());
        exitButton.addActionListener(e -> exitQuiz());

        // Prepare Questions
        prepareQuestions();

        setVisible(true);
    }

    private void prepareQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("Which company originally developed Java?",
                new String[]{"Microsoft", "Sun Microsystems", "Oracle", "Google"}, 1));

        questions.add(new Question("Which of these is a valid keyword in Java?",
                new String[]{"interface", "unsigned", "typeof", "delete"}, 0));

        questions.add(new Question("What is the extension of Java bytecode files?",
                new String[]{".java", ".class", ".js", ".exe"}, 1));

        questions.add(new Question("Which method is the entry point of a Java program?",
                new String[]{"start()", "main()", "run()", "execute()"}, 1));

        questions.add(new Question("Which of these is not a Java feature?",
                new String[]{"Object-Oriented", "Architecture Neutral", "Use of pointers", "Platform Independent"}, 2));
    }

    private void startQuiz() {
        score = 0;
        questionIndex = 0;
        startButton.setEnabled(false);
        for (JButton b : optionButtons) b.setVisible(true);
        askNextQuestion();
    }

    private void askNextQuestion() {
        if (questionIndex >= questions.size()) {
            showResult();
            return;
        }

        Question q = questions.get(questionIndex);
        questionLabel.setText("Q" + (questionIndex + 1) + ": " + q.questionText);
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(q.options[i]);
        }

        timeLeft = 10;
        timerLabel.setText("Time: " + timeLeft);

        if (timer != null && timer.isRunning())
            timer.stop();

        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time: " + timeLeft);
            if (timeLeft == 0) {
                timer.stop();
                questionIndex++;
                askNextQuestion();
            }
        });
        timer.start();
    }

    private void checkAnswer(int selectedOption) {
        Question q = questions.get(questionIndex);
        if (selectedOption == q.correctOption) {
            score++;
        }
        questionIndex++;
        timer.stop();
        askNextQuestion();
    }

    private void showResult() {
        if (timer != null && timer.isRunning())
            timer.stop();

        for (JButton b : optionButtons) b.setVisible(false);
        startButton.setEnabled(true);
        questionLabel.setText("Quiz Over! Your Score: " + score + "/" + questions.size());
        timerLabel.setText("Time: 0");

        JOptionPane.showMessageDialog(this, "Your final score: " + score + "/" + questions.size(),
                "Quiz Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exitQuiz() {
        if (timer != null && timer.isRunning())
            timer.stop();

        JOptionPane.showMessageDialog(this, "Your current score: " + score + "/" + questions.size(),
                "Quiz Exited", JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }

    public static void main(String[] args) {
        new QuizWithTimer();
    }
}
