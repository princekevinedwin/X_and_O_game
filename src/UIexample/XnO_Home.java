import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class XnO_Home {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("XOverse");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.setLocationRelativeTo(null);

            BackgroundPanel panel = new BackgroundPanel("option1.png");
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(Box.createVerticalGlue());

            JPanel contentBox = new JPanel();
            contentBox.setOpaque(false);
            contentBox.setLayout(new BoxLayout(contentBox, BoxLayout.Y_AXIS));
            contentBox.setAlignmentX(Component.CENTER_ALIGNMENT);

            TitleLabel titleLabel = new TitleLabel();
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
            contentBox.add(titleLabel);

            String[] labels = {"Play", "Rules", "About"};
            for (String label : labels) {
                JButton btn = new JButton(label);
                styleButton(btn);

                if (label.equals("Play")) {
                    btn.addActionListener(e -> {
                        String p1 = JOptionPane.showInputDialog("Enter Player 1 Name:");
                        String p2 = JOptionPane.showInputDialog("Enter Player 2 Name:");
                        if (p1 != null && p2 != null && !p1.isBlank() && !p2.isBlank()) {
                            frame.dispose();
                            new XnO(p1.trim(), p2.trim()).drawGrid();
                        }
                    });
                } else if (label.equals("Rules")) {
                    btn.addActionListener(e -> {
                        frame.dispose();
                        XnO_Rules.showRulesPage();
                    });
                } else if (label.equals("About")) {
                    btn.addActionListener(e -> {
                        frame.dispose();
                        XnO_About.showAboutPage();
                    });
                }

                contentBox.add(btn);
                contentBox.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            panel.add(contentBox);
            panel.add(Box.createVerticalGlue());

            frame.setContentPane(panel);
            frame.setVisible(true);
        });
    }

    static class BackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = ImageIO.read(new File(imagePath));
            } catch (IOException e) {
                System.out.println("Failed to load background: " + imagePath);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    static class TitleLabel extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Font xoFont = new Font("Arial", Font.BOLD, 60);
            Font verseFont = new Font("Serif", Font.ITALIC, 55);

            String xoText = "XO";
            String verseText = "verse";

            FontMetrics fmXO = g2d.getFontMetrics(xoFont);
            FontMetrics fmVerse = g2d.getFontMetrics(verseFont);

            int xoWidth = fmXO.stringWidth(xoText);
            int totalWidth = xoWidth + fmVerse.stringWidth(verseText) + 10;
            int startX = (getWidth() - totalWidth) / 2;
            int baseY = 60;

            g2d.setFont(xoFont);
            TextLayout xoLayout = new TextLayout(xoText, xoFont, g2d.getFontRenderContext());
            Shape xoOutline = xoLayout.getOutline(AffineTransform.getTranslateInstance(startX, baseY));
            g2d.setStroke(new BasicStroke(3f));
            g2d.setColor(Color.RED);
            g2d.draw(xoOutline);
            g2d.setColor(Color.WHITE);
            g2d.fill(xoOutline);

            g2d.setFont(verseFont);
            TextLayout verseLayout = new TextLayout(verseText, verseFont, g2d.getFontRenderContext());
            Shape verseOutline = verseLayout.getOutline(AffineTransform.getTranslateInstance(startX + xoWidth + 10, baseY));
            g2d.setStroke(new BasicStroke(3f));
            g2d.setColor(Color.WHITE);
            g2d.draw(verseOutline);
            g2d.setColor(Color.RED);
            g2d.fill(verseOutline);

            g2d.dispose();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(500, 100);
        }
    }

    private static void styleButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.PLAIN, 18));
        btn.setForeground(Color.WHITE);
        btn.setBackground(Color.BLACK);
        btn.setOpaque(true);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        btn.setMargin(new Insets(8, 16, 8, 16));
        Dimension btnSize = new Dimension(220, 70);
        btn.setPreferredSize(btnSize);
        btn.setMaximumSize(btnSize);
        btn.setMinimumSize(btnSize);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setHorizontalAlignment(SwingConstants.CENTER);
    }
}