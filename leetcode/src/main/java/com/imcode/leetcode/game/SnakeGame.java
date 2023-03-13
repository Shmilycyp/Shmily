package com.imcode.leetcode.game;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/3/6
 * @Description :
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakeGame extends JFrame implements ActionListener {
    private Timer timer;
    private JPanel panel;
    private int x, y, foodX, foodY, score;
    private int[] snakeX = new int[100];
    private int[] snakeY = new int[100];
    private int length, direction;

    public SnakeGame() {
        setTitle("Snake Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        add(panel);

        JButton startButton = new JButton("Start");
        startButton.setBounds(150, 300, 100, 30);
        startButton.addActionListener(this);
        panel.add(startButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == null) {
            return;
        }
        if (e.getActionCommand().equals("Start")) {
            startGame();
        } else if (e.getActionCommand().equals("Pause")) {
            pauseGame();
        } else if (e.getActionCommand().equals("Resume")) {
            resumeGame();
        } else if (e.getActionCommand().equals("Restart")) {
            restartGame();
        }
    }

    private void startGame() {
        timer = new Timer(100, this);
        timer.start();

        length = 3;
        direction = 1;
        score = 0;
        x = 100;
        y = 100;
        foodX = (int) (Math.random() * 20) * 20;
        foodY = (int) (Math.random() * 20) * 20;

        for (int i = 0; i < length; i++) {
            snakeX[i] = x - i * 20;
            snakeY[i] = y;
        }

        panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "left");
        panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "right");
        panel.getInputMap().put(KeyStroke.getKeyStroke("UP"), "up");
        panel.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "down");

        panel.getActionMap().put("left", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (direction != 1) {
                    direction = 3;
                }
            }
        });

        panel.getActionMap().put("right", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (direction != 3) {
                    direction = 1;
                }
            }
        });

        panel.getActionMap().put("up", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (direction != 2) {
                    direction = 4;
                }
            }
        });

        panel.getActionMap().put("down", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (direction != 4) {
                    direction = 2;
                }
            }
        });
    }

    private void pauseGame() {
        timer.stop();

        JButton resumeButton = new JButton("Resume");
        resumeButton.setBounds(150, 300, 100, 30);
        resumeButton.addActionListener(this);
        panel.add(resumeButton);

        JButton restartButton = new JButton("Restart");
        restartButton.setBounds(150, 350, 100, 30);
        restartButton.addActionListener(this);
        panel.add(restartButton);
    }

    private void resumeGame() {
        timer.start();

        panel.remove(panel.getComponentCount() - 1);
        panel.remove(panel.getComponentCount() - 1);
    }

    private void restartGame() {
        timer.stop();

        panel.removeAll();
        panel.repaint();

        JButton startButton = new JButton("Start");
        startButton.setBounds(150, 300, 100, 30);
        startButton.addActionListener(this);
        panel.add(startButton);

        setVisible(true);
    }

    private void moveSnake() {
        for (int i = length; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        switch (direction) {
            case 1:
                snakeX[0] += 20;
                break;
            case 2:
                snakeY[0] += 20;
                break;
            case 3:
                snakeX[0] -= 20;
                break;
            case 4:
                snakeY[0] -= 20;
                break;
        }

        if (snakeX[0] == foodX && snakeY[0] == foodY) {
            length++;
            score++;
            foodX = (int) (Math.random() * 20) * 20;
            foodY = (int) (Math.random() * 20) * 20;
        }
    }

    private void checkCollision() {
        if (snakeX[0] < 0 || snakeX[0] >= 400 || snakeY[0] < 0 || snakeY[0] >= 400) {
            gameOver();
        }

        for (int i = 1; i < length; i++) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                gameOver();
            }
        }
    }

    private void gameOver() {
        timer.stop();

        JLabel scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setBounds(180, 200, 100, 30);
        panel.add(scoreLabel);

        JButton restartButton = new JButton("Restart");
        restartButton.setBounds(150, 300, 100, 30);
        restartButton.addActionListener(this);
        panel.add(restartButton);

        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.BLACK);
        g.fillRect(foodX, foodY, 20, 20);

        g.setColor(Color.RED);
        g.fillRect(snakeX[0], snakeY[0], 20, 20);

        g.setColor(Color.GREEN);
        for (int i = 1; i < length; i++) {
            g.fillRect(snakeX[i], snakeY[i], 20, 20);
        }

        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 20);
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}