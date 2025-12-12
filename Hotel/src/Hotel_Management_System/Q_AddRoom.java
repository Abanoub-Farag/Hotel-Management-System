package Hotel_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Q_AddRoom extends JFrame implements ActionListener {
    JTextField t2, t4;
    JComboBox<String> t3, t5, t6;
    JButton b1, b2;

    Q_AddRoom() {
        // Set frame properties first
        setUndecorated(true);
        setSize(885, 500);

        // Center the window on screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 875, 490);
        panel.setBackground(new Color(40, 40, 60)); // Modern dark blue-gray
        panel.setLayout(null);
        add(panel);

        // Main title with better styling
        JLabel l1 = new JLabel("Add New Room", SwingConstants.CENTER);
        l1.setBounds(200, 20, 475, 40);
        l1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        l1.setForeground(new Color(255, 215, 0)); // Gold color
        panel.add(l1);

        // Decorative separator line
        JSeparator separator = new JSeparator();
        separator.setBounds(200, 65, 475, 2);
        separator.setForeground(new Color(255, 215, 0, 100));
        panel.add(separator);

        // Room Number
        JLabel l2 = new JLabel("Room Number");
        l2.setBounds(100, 100, 152, 25);
        l2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        l2.setForeground(new Color(220, 220, 240));
        panel.add(l2);

        t2 = new JTextField();
        t2.setBounds(300, 100, 200, 35);
        t2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        t2.setForeground(Color.WHITE);
        t2.setBackground(new Color(60, 60, 80));
        t2.setCaretColor(Color.WHITE);
        t2.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 150), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(t2);

        // Availability
        JLabel l3 = new JLabel("Availability");
        l3.setBounds(100, 155, 152, 25);
        l3.setFont(new Font("Segoe UI", Font.BOLD, 16));
        l3.setForeground(new Color(220, 220, 240));
        panel.add(l3);

        t3 = new JComboBox<>(new String[] {"Available", "Occupied"});
        t3.setBounds(300, 155, 200, 35);
        t3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        t3.setForeground(Color.WHITE);
        t3.setBackground(new Color(60, 60, 80));
        t3.setFocusable(false);
        t3.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 150), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(t3);

        // Cleaning Status
        JLabel l5 = new JLabel("Cleaning Status");
        l5.setBounds(100, 210, 152, 25);
        l5.setFont(new Font("Segoe UI", Font.BOLD, 16));
        l5.setForeground(new Color(220, 220, 240));
        panel.add(l5);

        t5 = new JComboBox<>(new String[]{"Clean", "Dirty"});
        t5.setBounds(300, 210, 200, 35);
        t5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        t5.setForeground(Color.WHITE);
        t5.setBackground(new Color(60, 60, 80));
        t5.setFocusable(false);
        t5.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 150), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(t5);

        // Bed Type
        JLabel l6 = new JLabel("Bed Type");
        l6.setBounds(100, 265, 152, 25);
        l6.setFont(new Font("Segoe UI", Font.BOLD, 16));
        l6.setForeground(new Color(220, 220, 240));
        panel.add(l6);

        t6 = new JComboBox<>(new String[]{"Single", "Double", "King", "Queen"});
        t6.setBounds(300, 265, 200, 35);
        t6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        t6.setForeground(Color.WHITE);
        t6.setBackground(new Color(60, 60, 80));
        t6.setFocusable(false);
        t6.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 150), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(t6);

        // Price
        JLabel l4 = new JLabel("Price ($)");
        l4.setBounds(100, 320, 152, 25);
        l4.setFont(new Font("Segoe UI", Font.BOLD, 16));
        l4.setForeground(new Color(220, 220, 240));
        panel.add(l4);

        t4 = new JTextField();
        t4.setBounds(300, 320, 200, 35);
        t4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        t4.setForeground(Color.WHITE);
        t4.setBackground(new Color(60, 60, 80));
        t4.setCaretColor(Color.WHITE);
        t4.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 150), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(t4);

        // Right side decorative panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(550, 100, 275, 300);
        rightPanel.setBackground(new Color(50, 50, 70));
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 215, 0), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel roomIcon = new JLabel("🏨", SwingConstants.CENTER);
        roomIcon.setFont(new Font("Arial", Font.PLAIN, 80));
        roomIcon.setForeground(new Color(255, 215, 0));

        JLabel roomTips = new JLabel("<html><center><b>Room Management Tips:</b><br><br>" +
                "• Ensure unique room numbers<br>" +
                "• Update status regularly<br>" +
                "• Set competitive pricing<br>" +
                "• Track cleaning schedules</center></html>", SwingConstants.CENTER);
        roomTips.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        roomTips.setForeground(new Color(200, 200, 220));

        rightPanel.add(roomIcon, BorderLayout.CENTER);
        rightPanel.add(roomTips, BorderLayout.SOUTH);
        panel.add(rightPanel);

        // Add button with modern styling
        b1 = new JButton("Add Room");
        b1.setBounds(150, 390, 150, 40);
        b1.setBackground(new Color(70, 130, 180)); // Steel blue
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 149, 237), 1),
                BorderFactory.createEmptyBorder(5, 20, 5, 20)
        ));
        b1.setFocusPainted(false);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.addActionListener(this);
        panel.add(b1);

        // Back button with modern styling
        b2 = new JButton("Back");
        b2.setBounds(350, 390, 150, 40);
        b2.setBackground(new Color(220, 20, 60)); // Crimson red
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b2.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(178, 34, 34), 1),
                BorderFactory.createEmptyBorder(5, 20, 5, 20)
        ));
        b2.setFocusPainted(false);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.addActionListener(this);
        panel.add(b2);

        // Input validation hint
        JLabel validationHint = new JLabel("All fields are required", SwingConstants.CENTER);
        validationHint.setBounds(150, 440, 350, 20);
        validationHint.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        validationHint.setForeground(new Color(180, 180, 200));
        panel.add(validationHint);

        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            // Validate inputs
            String room = t2.getText().trim();
            String price = t4.getText().trim();

            if (room.isEmpty() || price.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all required fields",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validate room number is numeric
            try {
                Integer.parseInt(room);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Room number must be a numeric value",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate price is numeric
            try {
                Double.parseDouble(price);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Price must be a valid number",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Z_Con c = new Z_Con();
                String availability = (String) t3.getSelectedItem();
                String cleaningStatus = (String) t5.getSelectedItem();
                String bedType = (String) t6.getSelectedItem();

                // Check if room already exists
                String checkQuery = "SELECT * FROM room WHERE room_number = '" + room + "'";
                var resultSet = c.statement.executeQuery(checkQuery);

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this,
                            "Room number " + room + " already exists!",
                            "Duplicate Room",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // FIXED: Match the exact column order from database schema
                // room (room_number, availability, cleaning_status, bed_type, price)
                String q = "INSERT INTO room VALUES('" + room + "', '" + availability + "', '" +
                        cleaningStatus + "', '" + bedType + "', '" + price + "')";
                c.statement.executeUpdate(q);

                JOptionPane.showMessageDialog(this,
                        "Room " + room + " successfully added!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                // Clear fields for next entry
                t2.setText("");
                t4.setText("");
                t3.setSelectedIndex(0);
                t5.setSelectedIndex(0);
                t6.setSelectedIndex(0);

            } catch (Exception E) {
                E.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error adding room: " + E.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            setVisible(false);
            new D_admin(); // Assuming this opens the admin dashboard
        }
    }

    public static void main(String[] args) {
        // Use SwingUtilities for thread safety
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                // Optional: Set button hover effects
                UIManager.put("Button.select", new Color(255, 215, 0, 50));

            } catch (Exception e) {
                e.printStackTrace();
            }
            new Q_AddRoom();
        });
    }
}