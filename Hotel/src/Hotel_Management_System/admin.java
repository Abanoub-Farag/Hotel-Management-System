package Hotel_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin extends JFrame implements ActionListener {
    private JPanel mainPanel, buttonPanel, headerPanel, footerPanel;
    private JLabel titleLabel, subtitleLabel;
    private JButton addEmployeeBtn, addRoomBtn, addDriversBtn, logoutBtn, backBtn;

    admin() {
        super("Admin Dashboard - Hotel Management System");

        // Set up main panel with gradient background
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(13, 36, 56),
                        getWidth(), getHeight(), new Color(20, 50, 80)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Header Panel
        headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        titleLabel = new JLabel("ADMIN DASHBOARD");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        subtitleLabel = new JLabel("Manage Hotel Operations");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitleLabel.setForeground(new Color(200, 220, 255));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.CENTER);

        // Main Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 100, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(25, 25, 25, 25);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;

        // Create buttons with consistent styling
        addEmployeeBtn = createMenuButton("ADD EMPLOYEE", new Color(41, 128, 185), "👥");
        addRoomBtn = createMenuButton("ADD ROOM", new Color(39, 174, 96), "🏨");
        addDriversBtn = createMenuButton("MANAGE DRIVERS", new Color(230, 126, 34), "🚗");

        // First row
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(addEmployeeBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(addRoomBtn, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        buttonPanel.add(addDriversBtn, gbc);

        // Footer Panel with navigation buttons
        footerPanel = new JPanel();
        footerPanel.setOpaque(false);
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));

        backBtn = createFooterButton("← BACK", new Color(149, 165, 166));
        logoutBtn = createFooterButton("LOGOUT →", new Color(231, 76, 60));

        footerPanel.add(backBtn);
        footerPanel.add(logoutBtn);

        // Add all panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Frame settings
        add(mainPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createMenuButton(String text, Color color, String icon) {
        JButton button = new JButton("<html><div style='text-align:center;'>"
                + icon + "<br><br><b>" + text + "</b></div></html>");

        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.darker(), 2),
                BorderFactory.createEmptyBorder(40, 20, 40, 20)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(250, 150));
        button.setFocusPainted(false);

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(color.brighter(), 2),
                        BorderFactory.createEmptyBorder(40, 20, 40, 20)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(color.darker(), 2),
                        BorderFactory.createEmptyBorder(40, 20, 40, 20)
                ));
            }
        });

        button.addActionListener(this);
        return button;
    }

    private JButton createFooterButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.darker(), 1),
                BorderFactory.createEmptyBorder(10, 30, 10, 30)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });

        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEmployeeBtn) {
            new AddEmployee();
            this.setVisible(false);
        } else if (e.getSource() == addRoomBtn) {
            new AddRoom();
            this.setVisible(false);
        } else if (e.getSource() == addDriversBtn) {
            // You can create AddDrivers class later
            JOptionPane.showMessageDialog(this, "Drivers management feature coming soon!");
        } else if (e.getSource() == logoutBtn) {
            int response = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == backBtn) {
            new Dashboard();
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new admin();
        });
    }
}