/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hedgeyourbet;

/**
 *
 * @author DOLPHSSV
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class HedgeYourBetUsingFiles extends JFrame {

    private JLabel questionLabel;
    private JCheckBox answer1;
    private JCheckBox answer2;
    private JCheckBox answer3;
    private JButton submitButton;
    private JLabel scoreLabel;
    private JLabel previousScoreLabel;

    private int questionNumber = 0;
    private int score = 0;

    private final String FILE_NAME =
            System.getProperty("user.home") + File.separator + "previousScore.txt";

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
        {"Central Processing Unit", "Computer Personal Unit", "Central Program Utility"},
        {"CSS", "HTML", "Java"}
    };

    public HedgeYourBetUsingFiles() {

        setTitle("HEDGE YOUR BET - FILE VERSION");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Previous score
        previousScoreLabel = new JLabel(
                "Previous Score: " + readPreviousScore()
        );

        previousScoreLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        previousScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Question
        questionLabel = new JLabel();
        questionLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Answer checkboxes
        answer1 = new JCheckBox();
        answer2 = new JCheckBox();
        answer3 = new JCheckBox();

        answer1.setAlignmentX(Component.CENTER_ALIGNMENT);
        answer2.setAlignmentX(Component.CENTER_ALIGNMENT);
        answer3.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button
        submitButton = new JButton("Submit Answer");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Current score
        scoreLabel = new JLabel("Current Score: 0");
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components
        panel.add(Box.createVerticalStrut(15));
        panel.add(previousScoreLabel);

        panel.add(Box.createVerticalStrut(20));
        panel.add(questionLabel);

        panel.add(Box.createVerticalStrut(15));
        panel.add(answer1);
        panel.add(answer2);
        panel.add(answer3);

        panel.add(Box.createVerticalStrut(20));
        panel.add(submitButton);

        panel.add(Box.createVerticalStrut(15));
        panel.add(scoreLabel);

        add(panel);

        // Button action
        submitButton.addActionListener(e -> checkAnswer());

        // Show first question
        displayQuestion();
    }

    // Reads the previous score from the text file
    private int readPreviousScore() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return 0;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();

            if (line != null && !line.trim().isEmpty()) {
                return Integer.parseInt(line.trim());
            }

        } catch (IOException | NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not read the previous score."
            );
        }

        return 0;
    }

    // Saves the current score to the text file
    private void saveScore() {

        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(FILE_NAME))) {

            writer.println(score);

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not save the score."
            );
        }
    }

    // Displays the current question and answers
    private void displayQuestion() {

        questionLabel.setText(
                "Question " + (questionNumber + 1)
                + ": " + questions[questionNumber]
        );

        answer1.setText(answers[questionNumber][0]);
        answer2.setText(answers[questionNumber][1]);
        answer3.setText(answers[questionNumber][2]);

        // Clear previous selections
        answer1.setSelected(false);
        answer2.setSelected(false);
        answer3.setSelected(false);
    }

    // Checks the player's answer
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

        // Check if the correct answer was selected
        boolean correct = false;

        if (correctAnswers[questionNumber] == 0
                && answer1.isSelected()) {

            correct = true;
        }

        if (correctAnswers[questionNumber] == 1
                && answer2.isSelected()) {

            correct = true;
        }

        if (correctAnswers[questionNumber] == 2
                && answer3.isSelected()) {

            correct = true;
        }

        // Scoring system
        if (correct && selectedCount == 1) {

            score += 5;

        } else if (correct && selectedCount == 2) {

            score += 2;

        } else if (correct && selectedCount == 3) {

            score += 1;
        }

        // Update score
        scoreLabel.setText(
                "Current Score: " + score
        );

        // Move to next question
        questionNumber++;

        if (questionNumber < questions.length) {

            displayQuestion();

        } else {

            finishQuiz();
        }
    }

    // Finishes the quiz
    private void finishQuiz() {

        // Save score before displaying result
        saveScore();

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
                + "Your score: " + score + " / 25\n"
                + message
        );

        // Disable everything after the quiz
        submitButton.setEnabled(false);
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            HedgeYourBetUsingFiles quiz =
                    new HedgeYourBetUsingFiles();

            quiz.setVisible(true);
        });
    }
}