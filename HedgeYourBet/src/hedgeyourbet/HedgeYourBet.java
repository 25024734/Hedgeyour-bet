/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hedgeyourbet;

/**
 *
 * @author DOLPHSSV
 */

import javax.swing.*;
import java.awt.*;

public class HedgeYourBet extends JFrame {

    private JLabel questionLabel;
    private JCheckBox answer1;
    private JCheckBox answer2;
    private JCheckBox answer3;
    private JButton submitButton;
    private JLabel scoreLabel;

    private int questionNumber = 0;
    private int score = 0;

    private String[] questions = {
        "Which programming language is mainly used with Android development?",
        "Which planet is known as the Red Planet?",
        "Which data structure uses FIFO?",
        "What does CPU stand for?",
        "Which language is commonly used for web page structure?"
    };

    private int[] correctAnswers = {
        0,  // Java
        1,  // Mars
        2,  // Queue
        0,  // Central Processing Unit
        1   // HTML
    };

    private String[][] answers = {
        {"Java", "Python", "C++"},
        {"Earth", "Mars", "Jupiter"},
        {"Stack", "Array", "Queue"},
        {"Central Processing Unit", "Computer Personal Unit",
         "Central Program Utility"},
        {"CSS", "HTML", "Java"}
    };

    public HedgeYourBet() {

        setTitle("Hedge Your Bet Quiz");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        questionLabel = new JLabel();
        questionLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        answer1 = new JCheckBox();
        answer2 = new JCheckBox();
        answer3 = new JCheckBox();

        answer1.setAlignmentX(Component.CENTER_ALIGNMENT);
        answer2.setAlignmentX(Component.CENTER_ALIGNMENT);
        answer3.setAlignmentX(Component.CENTER_ALIGNMENT);

        submitButton = new JButton("Submit Answer");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(20));
        panel.add(questionLabel);

        panel.add(Box.createVerticalStrut(20));
        panel.add(answer1);
        panel.add(answer2);
        panel.add(answer3);

        panel.add(Box.createVerticalStrut(20));
        panel.add(submitButton);

        panel.add(Box.createVerticalStrut(20));
        panel.add(scoreLabel);

        add(panel);

        submitButton.addActionListener(e -> checkAnswer());

        displayQuestion();
    }

    private void displayQuestion() {

        questionLabel.setText(
                "Question " + (questionNumber + 1)
                + ": " + questions[questionNumber]
        );

        answer1.setText(answers[questionNumber][0]);
        answer2.setText(answers[questionNumber][1]);
        answer3.setText(answers[questionNumber][2]);

        answer1.setSelected(false);
        answer2.setSelected(false);
        answer3.setSelected(false);
    }

    private void checkAnswer() {

        int selectedCount = 0;

        if (answer1.isSelected()) {
            selectedCount++;
        }

        if (answer2.isSelected()) {
            selectedCount++;
        }

        if (answer3.isSelected()) {
            selectedCount++;
        }

        boolean correctAnswerSelected = false;

        if (correctAnswers[questionNumber] == 0
                && answer1.isSelected()) {

            correctAnswerSelected = true;

        } else if (correctAnswers[questionNumber] == 1
                && answer2.isSelected()) {

            correctAnswerSelected = true;

        } else if (correctAnswers[questionNumber] == 2
                && answer3.isSelected()) {

            correctAnswerSelected = true;
        }

        // Scoring
        if (correctAnswerSelected && selectedCount == 1) {

            score += 5;

        } else if (correctAnswerSelected && selectedCount == 2) {

            score += 2;

        } else if (correctAnswerSelected && selectedCount == 3) {

            score += 1;
        }

        scoreLabel.setText("Score: " + score);

        questionNumber++;

        if (questionNumber < questions.length) {

            displayQuestion();

        } else {

            finishQuiz();
        }
    }

    private void finishQuiz() {

        String message;

        if (score > 21) {

            message = "Fantastic!";

        } else if (score > 15) {

            message = "Very good!";

        } else {

            message = "OK";
        }

        JOptionPane.showMessageDialog(
                this,
                "Quiz completed!\n\n"
                + "Your score: " + score + " / 25\n\n"
                + message,
                "Quiz Result",
                JOptionPane.INFORMATION_MESSAGE
        );

        submitButton.setEnabled(false);
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            HedgeYourBet quiz = new HedgeYourBet();

            quiz.setVisible(true);
        });
    }
}