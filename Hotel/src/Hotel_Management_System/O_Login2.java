package Hotel_Management_System;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class O_Login2 extends JFrame implements ActionListener {

    JTextField textField1;
    JPasswordField passwordField1;
    JButton b1,b2;
    JLabel titleLabel;

    public O_Login2(){
        // Set window properties first
        setTitle("Hotel Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                Color color1 = new Color(15, 32, 39);  // Dark blue
                Color color2 = new Color(32, 58, 67);  // Medium blue
                Color color3 = new Color(44, 83, 100); // Light blue
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);

        // Title label
        titleLabel = new JLabel("HOTEL LOGIN", SwingConstants.CENTER);
        titleLabel.setBounds(0, 30, 600, 40);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(255, 215, 0)); // Gold color
        mainPanel.add(titleLabel);

        // Username label
        JLabel label1 = new JLabel("Username:");
        label1.setBounds(150, 100, 100, 30);
        label1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label1.setForeground(Color.WHITE);
        mainPanel.add(label1);

        // Password label
        JLabel label2 = new JLabel("Password:");
        label2.setBounds(150, 160, 100, 30);
        label2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label2.setForeground(Color.WHITE);
        mainPanel.add(label2);

        // Username field
        textField1 = new JTextField();
        textField1.setBounds(250, 100, 200, 35);
        textField1.setForeground(Color.WHITE);
        textField1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        textField1.setBackground(new Color(255, 255, 255, 30)); // Semi-transparent
        textField1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 215, 0, 150), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        textField1.setCaretColor(Color.WHITE);
        mainPanel.add(textField1);

        // Password field
        passwordField1 = new JPasswordField();
        passwordField1.setBounds(250, 160, 200, 35);
        passwordField1.setForeground(Color.WHITE);
        passwordField1.setBackground(new Color(255, 255, 255, 30));
        passwordField1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 215, 0, 150), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        passwordField1.setCaretColor(Color.WHITE);
        mainPanel.add(passwordField1);

        // Login button
        b1 = new JButton("LOGIN");
        b1.setBounds(180, 230, 100, 40);
        b1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b1.setBackground(new Color(255, 215, 0)); // Gold
        b1.setForeground(new Color(15, 32, 39)); // Dark blue
        b1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        b1.setFocusPainted(false);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.addActionListener(this);

        // Add hover effect for login button
        b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b1.setBackground(new Color(255, 225, 50)); // Lighter gold
                b1.setForeground(Color.BLACK);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b1.setBackground(new Color(255, 215, 0)); // Original gold
                b1.setForeground(new Color(15, 32, 39));
            }
        });
        mainPanel.add(b1);

        // Cancel button
        b2 = new JButton("CANCEL");
        b2.setBounds(320, 230, 100, 40);
        b2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b2.setBackground(new Color(220, 53, 69)); // Red color
        b2.setForeground(Color.WHITE);
        b2.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        b2.setFocusPainted(false);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.addActionListener(this);

        // Add hover effect for cancel button
        b2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b2.setBackground(new Color(200, 35, 51)); // Darker red
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b2.setBackground(new Color(220, 53, 69)); // Original red
            }
        });
        mainPanel.add(b2);

        // Add decorative line
        JSeparator separator = new JSeparator();
        separator.setBounds(50, 80, 500, 2);
        separator.setForeground(new Color(255, 215, 0, 100));
        mainPanel.add(separator);

        // Footer label
        JLabel footerLabel = new JLabel("© 2025 Hotel Management System", SwingConstants.CENTER);
        footerLabel.setBounds(0, 290, 600, 20);
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerLabel.setForeground(new Color(255, 255, 255, 150)); // Semi-transparent white
        mainPanel.add(footerLabel);

        // Set main panel as content pane
        setContentPane(mainPanel);

        // Center the window on screen
        setSize(600, 350);
        centerWindow();
        setResizable(false);
        setVisible(true);
    }

    // Method to center the window on screen
    private void centerWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1){
            try {
                Z_Con c = new Z_Con();
                String user = textField1.getText();
                String pass = new String(passwordField1.getPassword());

                String q = "select * from login2 where username = '"+user+"' and password = '"+pass+"'";
                ResultSet resultSet = c.statement.executeQuery(q);
                if (resultSet.next()){
                    new D_admin();
                    setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(this,
                            "Invalid username or password!",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                }

            }catch (Exception E){
                E.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Database connection error!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }else if (e.getSource() == b2){
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        // Use SwingUtilities for thread safety
        SwingUtilities.invokeLater(() -> {
            try {
                // Set Look and Feel to system default for better appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new O_Login2();
        });
    }
}