// XnO_Rules.java
import java.awt.*;
import javax.swing.*;

public class XnO_Rules {

    public static void showRulesPage() {
        JFrame frame = new JFrame("Game Rules - XOverse");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new BorderLayout());
        rulesPanel.setBackground(Color.LIGHT_GRAY);

        JLabel headerLabel = new JLabel("How to Play XOverse", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(Color.RED);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerLabel.setOpaque(false);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel underlinePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.fillRect(100, 0, getWidth() - 200, 3);
            }
        };
        underlinePanel.setPreferredSize(new Dimension(0, 5));
        underlinePanel.setOpaque(false);

        JTextArea rulesArea = new JTextArea();
        rulesArea.setText("""
                          
                          
                          1. The game is played on a 3x3 grid.
                          2. Player 1 uses 'X', Player 2 uses 'O'.
                          3. Players take turns placing their symbol in empty cells.
                          4. The first player to get 3 in a row (horizontally, vertically, or diagonally) wins.
                          5. The looser from every round, gets the opportunity to start in the next round. 
                          5. If all cells are filled and no one wins, it's a draw.
                          
                          Have fun!""");
        rulesArea.setWrapStyleWord(true);
        rulesArea.setLineWrap(true);
        rulesArea.setEditable(false);
        rulesArea.setFont(new Font("Arial", Font.PLAIN, 16));
        rulesArea.setBackground(Color.LIGHT_GRAY);
        rulesArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.setBackground(Color.LIGHT_GRAY);
        textPanel.add(headerLabel, BorderLayout.NORTH);
        textPanel.add(underlinePanel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(rulesArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton backButton = new JButton("Back to Home");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        backButton.setPreferredSize(new Dimension(150, 40));

        backButton.addActionListener(e -> {
            frame.dispose();
            XnO_Home.main(null);
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.add(backButton);

        rulesPanel.add(textPanel, BorderLayout.NORTH);
        rulesPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(rulesPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
