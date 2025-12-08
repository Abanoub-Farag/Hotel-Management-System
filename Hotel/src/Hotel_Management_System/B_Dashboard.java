package Hotel_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class B_Dashboard extends JFrame implements ActionListener {
    private JButton receptionBtn, adminBtn;
    private JPanel mainPanel, buttonPanel, titlePanel;
    private JLabel titleLabel, subtitleLabel;
    private Image backgroundImage;

    B_Dashboard() {
        super("HOTEL MANAGEMENT SYSTEM");

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("/media/abanoub/Storage1/My-Github/Hotel-Management-System/Hotel/src/images/hotel.jpeg"));
            // Scale the image to fit the screen
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            backgroundImage = backgroundImage.getScaledInstance(
                    screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.out.println("Background image not found. Using gradient background instead.");
            backgroundImage = null;
        }

        // Set up the main panel with background
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                if (backgroundImage != null) {
                    // Draw the image with overlay for better text visibility
                    g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

                    // Add dark overlay for better text visibility
                    g2d.setColor(new Color(0, 0, 0, 120)); // Semi-transparent black overlay
                    g2d.fillRect(0, 0, getWidth(), getHeight());

                    // Add subtle gradient overlay
                    GradientPaint gradientOverlay = new GradientPaint(
                            0, 0, new Color(30, 60, 90, 100), // Deep blue with transparency
                            getWidth(), getHeight(), new Color(90, 40, 90, 100) // Purple with transparency
                    );
                    g2d.setPaint(gradientOverlay);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    // Fallback gradient background - more elegant colors
                    GradientPaint gradient = new GradientPaint(
                            0, 0, new Color(30, 60, 90), // Deep Navy Blue
                            getWidth() * 0.7f, getHeight(), new Color(90, 40, 90) // Royal Purple
                    );
                    g2d.setPaint(gradient);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Title Panel with elegant design
        titlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Elegant background with subtle gradient
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(255, 255, 255, 15),
                        0, getHeight(), new Color(255, 255, 255, 5)
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // Subtle border
                g2d.setColor(new Color(255, 215, 0, 60)); // Soft gold border
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            }
        };
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        // Main Title - More elegant font
        titleLabel = new JLabel("HOTEL MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 46));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add subtle shadow to title
        titleLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 8, 0),
                BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 215, 0, 80))
        ));

        // Subtitle - Smaller and more elegant
        subtitleLabel = new JLabel("Premium Hospitality Management");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        subtitleLabel.setForeground(new Color(230, 230, 250)); // Lavender color
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        // Elegant icon
        JLabel iconLabel = new JLabel("🏨");
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 50));
        iconLabel.setForeground(new Color(255, 215, 0, 200)); // Transparent gold
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        titlePanel.add(iconLabel);
        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        // Button Panel - Centered with better spacing
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 100, 100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 50, 30, 50); // Increased spacing for bigger buttons
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        // Reception Button - Elegant blue theme (BIGGER)
        receptionBtn = createElegantButton("RECEPTION",
                new Color(70, 130, 180),    // Steel Blue
                new Color(245, 245, 220));  // Beige text
        receptionBtn.setPreferredSize(new Dimension(280, 75)); // BIGGER: 280x75
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(receptionBtn, gbc);

        // Admin Button - Elegant green theme (BIGGER)
        adminBtn = createElegantButton("ADMIN LOGIN",
                new Color(60, 179, 113),    // Medium Sea Green
                new Color(255, 250, 240));  // Floral White text
        adminBtn.setPreferredSize(new Dimension(280, 75)); // BIGGER: 280x75
        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(adminBtn, gbc);

        // Add decorative panel between buttons (optional)
        JPanel centerDecorPanel = new JPanel();
        centerDecorPanel.setOpaque(false);
        centerDecorPanel.setPreferredSize(new Dimension(100, 75));
        centerDecorPanel.setLayout(new BorderLayout());

        JLabel decorLabel = new JLabel("✧", SwingConstants.CENTER);
        decorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        decorLabel.setForeground(new Color(255, 215, 0, 120));
        centerDecorPanel.add(decorLabel, BorderLayout.CENTER);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        // Add components to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Elegant footer
        JPanel footerPanel = new JPanel();
        footerPanel.setOpaque(false);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        JLabel footerLabel = new JLabel("© 2025 Luxury Hotel Management | Excellence in Hospitality");
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerLabel.setForeground(new Color(255, 255, 255, 140)); // More transparent
        footerPanel.add(footerLabel);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Frame settings
        add(mainPanel);

        // Set to fullscreen or maximized
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set window opacity for smooth appearance
        try {
            setOpacity(0.98f);
        } catch (Exception e) {
            // If opacity not supported
        }

        setVisible(true);
    }

    private JButton createElegantButton(String text, Color baseColor, Color textColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                // Get button state
                boolean isHovered = getModel().isRollover();
                boolean isPressed = getModel().isPressed();

                // Calculate colors based on state
                Color bgColor, borderColor;
                float shadowOpacity;

                if (isPressed) {
                    bgColor = baseColor.darker().darker();
                    borderColor = textColor.brighter();
                    shadowOpacity = 0.5f;
                } else if (isHovered) {
                    bgColor = baseColor.brighter();
                    borderColor = textColor;
                    shadowOpacity = 0.4f;
                } else {
                    bgColor = baseColor;
                    borderColor = textColor.darker();
                    shadowOpacity = 0.3f;
                }

                // Draw shadow (subtle but bigger for larger button)
                g2d.setColor(new Color(0, 0, 0, (int)(shadowOpacity * 255)));
                g2d.fillRoundRect(4, 4, getWidth()-8, getHeight()-8, 30, 30); // Bigger shadow

                // Draw main button with gradient
                GradientPaint gradient = new GradientPaint(
                        0, 0, bgColor.brighter(),
                        0, getHeight(), bgColor.darker()
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Bigger radius

                // Draw elegant border (thicker for bigger button)
                g2d.setColor(borderColor);
                g2d.setStroke(new BasicStroke(2.0f)); // Thicker border
                g2d.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 30, 30);

                // Draw inner highlight for pressed state
                if (isPressed) {
                    g2d.setColor(new Color(255, 255, 255, 40)); // More visible highlight
                    g2d.fillRoundRect(3, 3, getWidth()-6, getHeight()/2, 28, 28);
                }

                // Draw inner glow for hover state
                if (isHovered && !isPressed) {
                    g2d.setColor(new Color(255, 255, 255, 30));
                    g2d.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, 28, 28);
                }

                // Draw text with better styling for bigger button
                g2d.setFont(getFont());
                g2d.setColor(textColor);
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getHeight();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() - textHeight) / 2 + fm.getAscent();

                // Text shadow for depth (more pronounced for bigger button)
                g2d.setColor(new Color(0, 0, 0, 60));
                g2d.drawString(getText(), x+1, y+1);

                // Main text
                g2d.setColor(textColor);
                g2d.drawString(getText(), x, y);

                // Optional: Add subtle icon next to text
                if (isHovered) {
                    g2d.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
                    String icon = text.contains("RECEPTION") ? "💁" : "🔑";
                    g2d.drawString(icon, x - 25, y);
                    g2d.drawString(icon, x + textWidth + 10, y);
                }
            }
        };

        // Bigger font for bigger button
        button.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20)); // Bigger font: 20px
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);

        // Add hover effects
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Increase font size on hover for bigger button
                button.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22)); // Even bigger on hover
                button.repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Return to normal font size
                button.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
                button.repaint();
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.repaint();
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.repaint();
            }
        });

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == receptionBtn) {
            // Enhanced animation for bigger button
            animateButtonClick(receptionBtn, true);
            new C_Reception();
            dispose();
        } else if (e.getSource() == adminBtn) {
            animateButtonClick(adminBtn, true);
            new O_Login2();
            dispose();
        }
    }

    private void animateButtonClick(JButton button, boolean isReception) {
        // Enhanced animation effect for bigger buttons
        Timer timer = new Timer(15, new ActionListener() {
            float scale = 1.0f;
            int count = 0;
            Color originalBg = isReception ? new Color(70, 130, 180) : new Color(60, 179, 113);
            Color flashColor = isReception ? new Color(100, 160, 210) : new Color(90, 199, 143);

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count < 6) {
                    // Scale down
                    scale = 0.92f;
                    button.setForeground(Color.WHITE);
                } else if (count < 12) {
                    // Scale up with color flash
                    scale = 1.08f;
                    button.setForeground(flashColor);
                } else if (count < 18) {
                    // Return to normal
                    scale = 1.0f;
                    button.setForeground(isReception ? new Color(245, 245, 220) : new Color(255, 250, 240));
                } else {
                    ((Timer)e.getSource()).stop();
                    return;
                }

                // Apply scale
                Dimension originalSize = button.getPreferredSize();
                button.setSize(
                        (int)(originalSize.width * scale),
                        (int)(originalSize.height * scale)
                );

                // Center the button after scaling
                button.setLocation(
                        button.getParent().getWidth()/2 - button.getWidth()/2,
                        button.getParent().getHeight()/2 - button.getHeight()/2
                );

                button.revalidate();
                button.repaint();
                count++;
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        // Set look and feel for better appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Custom UI settings for bigger buttons
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 16));
            UIManager.put("Button.margin", new Insets(12, 24, 12, 24)); // Bigger padding
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new B_Dashboard();
        });
    }
}