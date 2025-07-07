import java.awt.*;
import javax.swing.*;

public class XnO {

    private String player1;
    private String player2;
    private int player1Wins = 0;
    private int player2Wins = 0;

    JFrame xandO = new JFrame("XOverse - Game");
    JLabel player1Label;
    JLabel player2Label;
    JPanel gridPanel;
    JButton[] buttons = new JButton[9];
    int flag = 0;

    public XnO(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void drawGrid() {
        xandO.getContentPane().removeAll();
        xandO.setLayout(new BorderLayout());

        player1Label = new JLabel("  " + player1 + " (Wins: " + player1Wins + ")", SwingConstants.LEFT);
        player2Label = new JLabel(player2 + " (Wins: " + player2Wins + ")  ", SwingConstants.RIGHT);
        player1Label.setFont(new Font("Arial", Font.BOLD, 16));
        player2Label.setFont(new Font("Arial", Font.BOLD, 16));
        player1Label.setForeground(Color.RED);
        player2Label.setForeground(Color.WHITE);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.DARK_GRAY);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // padding
        topPanel.add(player1Label, BorderLayout.WEST);
        topPanel.add(player2Label, BorderLayout.EAST);

        gridPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        gridPanel.setBackground(Color.DARK_GRAY);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            int pos = i + 1;
            styleButton(buttons[i]);
            buttons[i].addActionListener(e -> handleMove((JButton) e.getSource(), pos));
            gridPanel.add(buttons[i]);
        }

        // Bottom buttons
        JButton endBtn = new JButton("End Game");
        JButton homeBtn = new JButton("Back to Home");

        endBtn.addActionListener(e -> showFinalScores());
        homeBtn.addActionListener(e -> {
            xandO.dispose();
            XnO_Home.main(null);
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.add(homeBtn);
        bottomPanel.add(endBtn);

        styleNavButton(endBtn);
        styleNavButton(homeBtn);

        xandO.add(topPanel, BorderLayout.NORTH);
        xandO.add(gridPanel, BorderLayout.CENTER);
        xandO.add(bottomPanel, BorderLayout.SOUTH);

        xandO.setSize(600, 600);
        xandO.setLocationRelativeTo(null);
        xandO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        xandO.setVisible(true);
    }

    private void handleMove(JButton btn, int position) {
        btn.setEnabled(false);
        if (flag == 0) {
            btn.setText("X");
            btn.setForeground(Color.RED);
            player1Label.setText("  " + player1 + " (Wins: " + player1Wins + ")");
        } else {
            btn.setText("O");
            btn.setForeground(Color.WHITE);
            player2Label.setText(player2 + " (Wins: " + player2Wins + ")  ");
        }
        flag = 1 - flag;
        checkWin();
    }

    private void checkWin() {
        int[][] winCombos = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combo : winCombos) {
            String t1 = buttons[combo[0]].getText();
            String t2 = buttons[combo[1]].getText();
            String t3 = buttons[combo[2]].getText();

            if (!t1.isEmpty() && t1.equals(t2) && t2.equals(t3)) {
                if (t1.equals("X")) {
                    player1Wins++;
                    JOptionPane.showMessageDialog(xandO, player1 + " Wins!");
                } else {
                    player2Wins++;
                    JOptionPane.showMessageDialog(xandO, player2 + " Wins!");
                }
                restartGame();
                return;
            }
        }

        
        boolean draw = true;
        for (JButton b : buttons) {
            if (b.getText().isEmpty()) {
                draw = false;
                break;
            }
        }

        if (draw) {
            JOptionPane.showMessageDialog(xandO, "It's a draw!");
            restartGame();
        }
    }

    private void restartGame() {
        drawGrid();
    }

    private void showFinalScores() {
        String[][] data = {
            {player1, String.valueOf(player1Wins)},
            {player2, String.valueOf(player2Wins)}
        };
        String[] columns = {"Player", "Wins"};

        JTable table = new JTable(data, columns);
        table.setEnabled(false);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(24);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(250, 90));

        String result;
        if (player1Wins > player2Wins) {
            result = player1 + " is the overall winner!";
        } else if (player2Wins > player1Wins) {
            result = player2 + " is the overall winner!";
        } else {
            result = "It's a draw!";
        }

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(new JLabel(result, SwingConstants.CENTER), BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(xandO, panel, "Final Scores", JOptionPane.PLAIN_MESSAGE);

        System.exit(0);
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.BOLD, 36));
        btn.setBackground(Color.BLACK);
        btn.setOpaque(true);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
    }

    private void styleNavButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        btn.setPreferredSize(new Dimension(140, 40));
    }
}
