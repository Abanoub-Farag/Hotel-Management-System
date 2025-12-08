package Hotel_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class C_Reception extends JFrame {
    private Image backgroundImage;

    C_Reception(){
        super("Reception Dashboard");

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("/media/abanoub/Storage1/My-Github/Hotel-Management-System/Hotel/src/images/hotel.jpeg"));
        } catch (IOException e) {
            System.out.println("Background image not found. Using gradient background instead.");
            backgroundImage = null;
        }

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create main panel with background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                if (backgroundImage != null) {
                    // Draw the background image with blur effect
                    g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

                    // Add dark overlay for better readability
                    g2d.setColor(new Color(0, 0, 0, 120));
                    g2d.fillRect(0, 0, getWidth(), getHeight());

                    // Add gradient overlay
                    GradientPaint gradient = new GradientPaint(
                            0, 0, new Color(15, 30, 45, 180), // Dark blue
                            getWidth(), getHeight(), new Color(60, 20, 60, 180) // Purple
                    );
                    g2d.setPaint(gradient);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    // Fallback gradient background
                    GradientPaint gradient = new GradientPaint(
                            0, 0, new Color(15, 30, 45),
                            getWidth(), getHeight(), new Color(60, 20, 60)
                    );
                    g2d.setPaint(gradient);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Header Panel with decorative elements
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Header background with gradient
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(255, 215, 0, 40), // Gold with transparency
                        0, getHeight(), new Color(255, 215, 0, 10)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // Bottom border
                g2d.setColor(new Color(255, 215, 0, 80));
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
            }
        };
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        JLabel titleLabel = new JLabel("🏨 RECEPTION DASHBOARD", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));
        titleLabel.setForeground(new Color(255, 215, 0));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel subtitleLabel = new JLabel("Premium Hotel Management System - Front Desk Operations", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        subtitleLabel.setForeground(new Color(230, 230, 250));

        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);

        // Main content panel - now using GridBagLayout for better control
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.weightx = 1.0;
        gbcMain.weighty = 1.0;

        // Left Panel - Menu Buttons
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Glass effect background
                g2d.setColor(new Color(255, 255, 255, 20));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                // Border with shadow effect
                g2d.setColor(new Color(255, 215, 0, 100));
                g2d.setStroke(new BasicStroke(2.5f));
                g2d.drawRoundRect(2, 2, getWidth()-4, getHeight()-4, 30, 30);
            }
        };
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        leftPanel.setPreferredSize(new Dimension(380, 0));

        // Title for left panel
        JLabel menuTitle = new JLabel("Quick Actions", SwingConstants.CENTER);
        menuTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        menuTitle.setForeground(new Color(255, 215, 0));
        menuTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        leftPanel.add(menuTitle, BorderLayout.NORTH);

        // Buttons panel with scroll if needed
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(12, 15, 12, 15);
        gbc.weightx = 1.0;

        // Create styled buttons
        String[][] buttonData = {
                {"👤", "New Customer Form", "Register new hotel guests"},
                {"🛏️", "Room Management", "Manage room availability & status"},
                {"🏢", "Department", "View hotel departments"},
                {"👥", "All Employee Info", "Access employee database"},
                {"📋", "Customer Info", "View guest information"},
                {"👨‍💼", "Manager Info", "Contact hotel management"},
                {"🏃‍♂️", "Check Out", "Process guest departures"},
                {"📝", "Update Check-In", "Modify check-in details"},
                {"🔄", "Update Room Status", "Change room availability"},
                {"🔍", "Search Room", "Find available rooms"}
        };

        for (int i = 0; i < buttonData.length; i++) {
            gbc.gridy = i;
            buttonsPanel.add(createEnhancedButton(buttonData[i][0], buttonData[i][1], buttonData[i][2], i), gbc);
        }

        // Wrap buttons panel in scroll pane
        JScrollPane scrollPane = new JScrollPane(buttonsPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(350, 600));

        leftPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom buttons panel for left side
        JPanel leftBottomPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        leftBottomPanel.setOpaque(false);
        leftBottomPanel.setBorder(BorderFactory.createEmptyBorder(25, 20, 0, 20));

        JButton logoutBtn = createActionButton("🚪 LOGOUT", new Color(220, 53, 69), new Color(255, 200, 200));
        JButton backBtn = createActionButton("↩️ BACK", new Color(70, 130, 180), new Color(200, 220, 255));

        leftBottomPanel.add(backBtn);
        leftBottomPanel.add(logoutBtn);

        leftPanel.add(leftBottomPanel, BorderLayout.SOUTH);

        // Right Panel - Dashboard Content
        JPanel rightPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Elegant background
                g2d.setColor(new Color(255, 255, 255, 15));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);

                // Decorative border
                g2d.setColor(new Color(255, 215, 0, 70));
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(2, 2, getWidth()-4, getHeight()-4, 35, 35);

                // Subtle pattern
                g2d.setColor(new Color(255, 215, 0, 10));
                for (int i = 0; i < getWidth(); i += 40) {
                    for (int j = 0; j < getHeight(); j += 40) {
                        g2d.fillOval(i, j, 3, 3);
                    }
                }
            }
        };
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Create a comprehensive dashboard
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setOpaque(false);
        dashboardPanel.setLayout(new BoxLayout(dashboardPanel, BoxLayout.Y_AXIS));

        // Welcome section
        JPanel welcomePanel = new JPanel();
        welcomePanel.setOpaque(false);
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));

        JLabel welcomeTitle = new JLabel("Welcome to Reception Control Center");
        welcomeTitle.setFont(new Font("Segoe UI", Font.BOLD, 34));
        welcomeTitle.setForeground(new Color(255, 215, 0));
        welcomeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welcomeText = new JLabel("<html><div style='text-align: center;'>Manage all hotel operations efficiently from this centralized dashboard.<br>"
                + "Access customer information, room status, employee details, and more.</div></html>");
        welcomeText.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        welcomeText.setForeground(new Color(240, 240, 255));
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeText.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 50));

        welcomePanel.add(welcomeTitle);
        welcomePanel.add(Box.createVerticalStrut(15));
        welcomePanel.add(welcomeText);

        // Stats panels
        JPanel statsPanel = new JPanel(new GridLayout(2, 3, 25, 25));
        statsPanel.setOpaque(false);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));

        Object[][] statsData = {
                {"📊", "Today's Check-ins", "12 Guests", new Color(70, 130, 180)},
                {"✅", "Available Rooms", "24 Rooms", new Color(60, 179, 113)},
                {"🔄", "Pending Check-outs", "8 Rooms", new Color(255, 193, 7)},
                {"👥", "Staff On Duty", "15 Employees", new Color(155, 89, 182)},
                {"⭐", "VIP Guests", "3 Guests", new Color(231, 76, 60)},
                {"🏆", "Occupancy Rate", "78%", new Color(52, 152, 219)}
        };

        for (Object[] stat : statsData) {
            statsPanel.add(createStatCard(
                    (String)stat[0],
                    (String)stat[1],
                    (String)stat[2],
                    (Color)stat[3]
            ));
        }
        // Quick tips section
        JPanel tipsPanel = new JPanel();
        tipsPanel.setOpaque(false);
        tipsPanel.setLayout(new BoxLayout(tipsPanel, BoxLayout.Y_AXIS));
        tipsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 0, 50));

        JLabel tipsTitle = new JLabel("💡 Quick Tips & Reminders");
        tipsTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        tipsTitle.setForeground(new Color(255, 215, 0));
        tipsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea tipsText = new JTextArea();
        tipsText.setText("• Always verify guest identification during check-in\n"
                + "• Update room status immediately after check-out\n"
                + "• Notify housekeeping of VIP arrivals in advance\n"
                + "• Double-check payment methods before finalizing\n"
                + "• Keep emergency contact numbers readily available\n"
                + "• Document special requests for returning guests");
        tipsText.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tipsText.setForeground(new Color(220, 220, 255));
        tipsText.setBackground(new Color(255, 255, 255, 30));
        tipsText.setEditable(false);
        tipsText.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        tipsText.setLineWrap(true);
        tipsText.setWrapStyleWord(true);

        tipsPanel.add(tipsTitle);
        tipsPanel.add(Box.createVerticalStrut(15));
        tipsPanel.add(tipsText);

        // Add all sections to dashboard
        dashboardPanel.add(welcomePanel);
        dashboardPanel.add(statsPanel);
        dashboardPanel.add(tipsPanel);

        // Add scroll to dashboard if needed
        JScrollPane dashboardScroll = new JScrollPane(dashboardPanel);
        dashboardScroll.setOpaque(false);
        dashboardScroll.getViewport().setOpaque(false);
        dashboardScroll.setBorder(BorderFactory.createEmptyBorder());
        dashboardScroll.getVerticalScrollBar().setUnitIncrement(16);

        rightPanel.add(dashboardScroll, BorderLayout.CENTER);

        // Add decorative footer to right panel
        JPanel rightFooter = new JPanel();
        rightFooter.setOpaque(false);
        rightFooter.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel footerLabel = new JLabel("💫 Premium Hotel Management System • Always at Your Service 💫", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        footerLabel.setForeground(new Color(255, 215, 0, 150));
        rightFooter.add(footerLabel);

        rightPanel.add(rightFooter, BorderLayout.SOUTH);

        // Add panels to main content
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.weightx = 0.3; // 30% width for left panel
        contentPanel.add(leftPanel, gbcMain);

        gbcMain.gridx = 1;
        gbcMain.weightx = 0.7; // 70% width for right panel
        contentPanel.add(rightPanel, gbcMain);

        // Add all to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Set content pane
        setContentPane(mainPanel);
        setVisible(true);
    }

    private JButton createEnhancedButton(String icon, String title, String description, int index) {
        JButton button = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color bgColor, borderColor;
                if (getModel().isPressed()) {
                    bgColor = new Color(60, 179, 113, 220);
                    borderColor = new Color(255, 215, 0);
                } else if (getModel().isRollover()) {
                    bgColor = new Color(70, 130, 180, 180);
                    borderColor = new Color(255, 215, 0, 200);
                } else {
                    bgColor = new Color(255, 255, 255, 50);
                    borderColor = new Color(255, 215, 0, 80);
                }

                // Draw button background
                g2d.setColor(bgColor);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                // Draw border
                g2d.setColor(borderColor);
                g2d.setStroke(new BasicStroke(1.8f));
                g2d.drawRoundRect(2, 2, getWidth()-4, getHeight()-4, 25, 25);

                // Draw icon and text
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
                FontMetrics fm = g2d.getFontMetrics();
                int iconWidth = fm.stringWidth(icon);
                g2d.drawString(icon, 20, getHeight()/2 - 15);

                g2d.setFont(new Font("Segoe UI", Font.BOLD, 16));
                g2d.drawString(title, 60, getHeight()/2 - 10);

                g2d.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                g2d.setColor(new Color(240, 240, 255, 200));
                g2d.drawString(description, 60, getHeight()/2 + 15);

                super.paintComponent(g);
            }
        };

        button.setPreferredSize(new Dimension(320, 70));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    switch (index) {
                        case 0: new E_NewCustomer(); break;
                        case 1: new F_Room(); break;
                        case 2: new G_Department(); break;
                        case 3: new H_Employee(); break;
                        case 4: new I_CustomerInfo(); break;
                        case 5: new J_ManagerInfo(); break;
                        case 6: new K_CheckOut(); break;
                        case 7: new L_UpdateCheck(); break;
                        case 8: new M_UpdateRoom(); break;
                        case 9: new N_SearchRoom(); break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(C_Reception.this,
                            "Error opening window: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return button;
    }

    private JPanel createStatCard(String icon, String title, String value, Color color) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Card background
                g2d.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 40));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // Border
                g2d.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 120));
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 20, 20);
            }
        };
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        card.setPreferredSize(new Dimension(200, 150));

        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        iconLabel.setForeground(color.brighter());

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(240, 240, 255));

        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueLabel.setForeground(color.brighter());

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(iconLabel);
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(valueLabel);

        card.add(contentPanel, BorderLayout.CENTER);
        return card;
    }

    private JButton createActionButton(String text, Color baseColor, Color hoverColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color bgColor = getModel().isRollover() ? hoverColor : baseColor;

                // Draw button with gradient
                GradientPaint gradient = new GradientPaint(
                        0, 0, bgColor,
                        0, getHeight(), bgColor.darker()
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // Draw border
                g2d.setColor(new Color(255, 215, 0));
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 20, 20);

                super.paintComponent(g);
            }
        };

        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(150, 50));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.contains("LOGOUT")) {
                    int confirm = JOptionPane.showConfirmDialog(C_Reception.this,
                            "Are you sure you want to logout?",
                            "Confirm Logout",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                } else if (text.contains("BACK")) {
                    dispose();
                    new B_Dashboard();
                }
            }
        });

        return button;
    }

    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new C_Reception();
        });
    }
}