// XnO_About.java
import java.awt.*;
import javax.swing.*;

public class XnO_About {

    public static void showAboutPage() {
        JFrame frame = new JFrame("About - XOverse");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel aboutPanel = new JPanel();
        aboutPanel.setLayout(new BorderLayout());
        aboutPanel.setBackground(Color.LIGHT_GRAY);

        JLabel headerLabel = new JLabel("About XOverse", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.BLUE);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea aboutArea = new JTextArea();
        aboutArea.setText("""
                          
                          
                          XOverse: A Journey Beyond the Grid
                          
                          XOverse was born out of a passion for classic games and a desire to give them new life. In late 2025, a group of five enthusiastic tech minds came together with one mission: "Reimagine X and O".
                          
                          The game features a sleek interface, modern sound, and automatic score tracking, while keeping the nostalgic thrill of the original tic-tac-toe experience.
                          
                          How It Works:
                          - Two players take turns on a 3x3 grid.
                          - The first to align three symbols wins the round.
                          - The game resets after each round, and scores are tracked automatically.
                          
                          Credits:
                          Prince Kevin Edwin - Lead Developer
                          Kenneth Andrew - UI/UX Design
                          Upeyi Jibrin - Game Logic & Mechanics
                          Kevin Katuka - Audio & Media Integration
                          Bemdoo Maor - Testing & Quality Assurance
                          
                          Thank you for being part of our XOverse.""");
        aboutArea.setWrapStyleWord(true);
        aboutArea.setLineWrap(true);
        aboutArea.setEditable(false);
        aboutArea.setFont(new Font("Serif", Font.PLAIN, 15));
        aboutArea.setBackground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(aboutArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton backButton = new JButton("Back to Home");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        backButton.setPreferredSize(new Dimension(150, 40));

        backButton.addActionListener(e -> {
            frame.dispose();
            XnO_Home.main(null);
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.add(backButton);

        aboutPanel.add(headerLabel, BorderLayout.NORTH);
        aboutPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(aboutPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
