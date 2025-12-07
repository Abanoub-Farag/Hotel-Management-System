package Hotel_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {
    private JButton receptionBtn, adminBtn;
    private JPanel mainPanel, buttonPanel;
    private JLabel titleLabel, subtitleLabel;

    Dashboard() {
        super("HOTEL MANAGEMENT SYSTEM");

        // Set up the main panel with background
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Create gradient background
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(30, 60, 114),
                        getWidth(), getHeight(), new Color(10, 30, 60)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 100, 0));

        titleLabel = new JLabel("HOTEL MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        subtitleLabel = new JLabel("Welcome to Premium Hotel Management");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        subtitleLabel.setForeground(new Color(200, 220, 255));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 40, 20, 40);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Reception Button
        receptionBtn = createStyledButton("RECEPTION", new Color(52, 152, 219));
        receptionBtn.setPreferredSize(new Dimension(220, 60));
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(receptionBtn, gbc);

        // Admin Button
        adminBtn = createStyledButton("ADMIN LOGIN", new Color(46, 204, 113));
        adminBtn.setPreferredSize(new Dimension(220, 60));
        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(adminBtn, gbc);

        // Add components to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Frame settings
        add(mainPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createStyledButton(String text, Color baseColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(baseColor.darker());
                } else if (getModel().isRollover()) {
                    g.setColor(baseColor.brighter());
                } else {
                    g.setColor(baseColor);
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                super.paintComponent(g);
            }
        };

        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == receptionBtn) {
            new Reception();
            setVisible(false);
        } else if (e.getSource() == adminBtn) {
            new Login2();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        // Set look and feel for better appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new Dashboard();
        });
    }
}