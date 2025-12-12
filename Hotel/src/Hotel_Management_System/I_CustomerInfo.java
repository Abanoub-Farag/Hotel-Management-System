package Hotel_Management_System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class I_CustomerInfo extends JFrame {

    I_CustomerInfo(){
        super("Customer Information");

        // Main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Elegant gradient background
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(20, 35, 50),      // Dark blue-gray
                        getWidth(), getHeight(), new Color(45, 25, 55)  // Dark purple-gray
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout(0, 20));
        contentPanel.setOpaque(false);

        // Title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel("👤 CUSTOMER INFORMATION");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(255, 215, 0)); // Gold color
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        JLabel subtitleLabel = new JLabel("View all customer details and records");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(220, 220, 240)); // Light lavender
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(subtitleLabel, BorderLayout.SOUTH);

        contentPanel.add(titlePanel, BorderLayout.NORTH);

        // Table panel with elegant border
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setOpaque(false);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 215, 0, 100), 2), // Gold border with transparency
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Create table with modern design
        JTable table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };

        table.setDoubleBuffered(true);
        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setForeground(new Color(240, 240, 240));
        table.setBackground(new Color(255, 255, 255, 15)); // Very light transparency
        table.setGridColor(new Color(255, 215, 0, 30)); // Subtle gold grid
        table.setSelectionBackground(new Color(65, 125, 175, 150)); // Steel blue selection
        table.setSelectionForeground(Color.WHITE);
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(true);

        // Load customer data - UPDATED QUERY TO MATCH YOUR DATABASE
        try {
            Z_Con c = new Z_Con();
            // Updated query to select all fields from customer table according to your schema
            String q = "SELECT " +
                    "customer_id AS 'Customer ID', " +
                    "id_type AS 'ID Type', " +
                    "first_name AS 'First Name', " +
                    "last_name AS 'Last Name', " +
                    "email AS 'Email', " +
                    "phone AS 'Phone', " +
                    "gender AS 'Gender', " +
                    "country AS 'Country', " +
                    "DATE_FORMAT(checkin_date, '%Y-%m-%d %H:%i') AS 'Check-in Date', " +
                    "deposit AS 'Deposit ($)', " +
                    "room_number AS 'Room No' " +
                    "FROM customer " +
                    "ORDER BY checkin_date DESC, customer_id";

            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

            // Customize table header
            JTableHeader header = table.getTableHeader();
            header.setFont(new Font("Segoe UI", Font.BOLD, 14));
            header.setBackground(new Color(35, 65, 95)); // Dark blue-gray
            header.setForeground(new Color(255, 215, 0)); // Gold
            header.setPreferredSize(new Dimension(header.getWidth(), 45));

            // Set optimized column widths based on your database fields
            if (table.getColumnCount() >= 1) table.getColumnModel().getColumn(0).setPreferredWidth(50);   // Customer ID
            if (table.getColumnCount() >= 2) table.getColumnModel().getColumn(1).setPreferredWidth(80);   // ID Type
            if (table.getColumnCount() >= 3) table.getColumnModel().getColumn(2).setPreferredWidth(100);  // First Name
            if (table.getColumnCount() >= 4) table.getColumnModel().getColumn(3).setPreferredWidth(100);  // Last Name
            if (table.getColumnCount() >= 5) table.getColumnModel().getColumn(4).setPreferredWidth(150);  // Email
            if (table.getColumnCount() >= 6) table.getColumnModel().getColumn(5).setPreferredWidth(120);  // Phone
            if (table.getColumnCount() >= 7) table.getColumnModel().getColumn(6).setPreferredWidth(70);   // Gender
            if (table.getColumnCount() >= 8) table.getColumnModel().getColumn(7).setPreferredWidth(100);  // Country
            if (table.getColumnCount() >= 9) table.getColumnModel().getColumn(8).setPreferredWidth(150);  // Check-in Date
            if (table.getColumnCount() >= 10) table.getColumnModel().getColumn(9).setPreferredWidth(80);  // Deposit
            if (table.getColumnCount() >= 11) table.getColumnModel().getColumn(10).setPreferredWidth(70); // Room No

            // Custom cell renderer with zebra striping
            DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    // Get column name for special handling
                    String columnName = table.getColumnName(column);

                    // Set alignment based on column type
                    if (columnName.equals("Customer ID") || columnName.equals("Deposit ($)") ||
                            columnName.equals("Room No")) {
                        ((JLabel) c).setHorizontalAlignment(JLabel.RIGHT);
                    } else if (columnName.equals("ID Type") || columnName.equals("Gender") ||
                            columnName.equals("Country")) {
                        ((JLabel) c).setHorizontalAlignment(JLabel.CENTER);
                    } else {
                        ((JLabel) c).setHorizontalAlignment(JLabel.LEFT);
                    }

                    // Special styling for specific columns
                    if (columnName.equals("Deposit ($)")) {
                        if (value != null && !value.toString().isEmpty()) {
                            try {
                                double deposit = Double.parseDouble(value.toString());
                                if (deposit > 0) {
                                    c.setForeground(new Color(50, 205, 50)); // Green for positive deposit
                                } else {
                                    c.setForeground(new Color(255, 69, 0)); // Red for zero/negative
                                }
                            } catch (NumberFormatException e) {
                                c.setForeground(new Color(255, 215, 0)); // Gold for invalid
                            }
                        }
                        c.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    } else if (columnName.equals("Check-in Date")) {
                        c.setForeground(new Color(135, 206, 250)); // Light sky blue for dates
                    } else if (columnName.equals("Customer ID")) {
                        c.setForeground(new Color(255, 182, 193)); // Light pink for ID
                        c.setFont(new Font("Segoe UI", Font.BOLD, 12));
                    } else if (columnName.equals("Room No")) {
                        c.setForeground(new Color(144, 238, 144)); // Light green for room
                        c.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    } else {
                        c.setForeground(new Color(240, 240, 240));
                    }

                    // Apply selection colors or zebra striping
                    if (isSelected) {
                        c.setBackground(new Color(65, 125, 175, 150)); // Selection color
                        c.setForeground(Color.WHITE);
                    } else {
                        // Zebra striping for better readability
                        if (row % 2 == 0) {
                            c.setBackground(new Color(255, 255, 255, 10)); // Very light
                        } else {
                            c.setBackground(new Color(255, 255, 255, 5)); // Even lighter
                        }
                    }

                    return c;
                }
            };

            // Apply renderer to all columns
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error loading customer data: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(255, 255, 255, 5));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tablePanel, BorderLayout.CENTER);

        // Button panel - REMOVED EXPORT AND ADD CUSTOMER BUTTONS
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Create styled buttons - ONLY BACK AND REFRESH
        JButton back = new JButton("← BACK TO DASHBOARD");
        styleButton(back, new Color(70, 130, 180)); // Steel blue

        JButton refresh = new JButton("🔄 REFRESH");
        styleButton(refresh, new Color(60, 179, 113)); // Medium sea green

        // NEW: Search button (kept since it's useful for this view)
        JButton search = new JButton("🔍 SEARCH");
        styleButton(search, new Color(72, 61, 139)); // Dark slate blue

        buttonPanel.add(back);
        buttonPanel.add(refresh);
        buttonPanel.add(search);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add all components
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Frame settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700); // Increased size for more columns
        centerWindow();
        setResizable(true);
        setVisible(true);

        // Back button action
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // If you have a reception/dashboard class, uncomment below:
                // new C_Reception();
            }
        });

        // Refresh button action
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable(table);
            }
        });

        // Search button action
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSearchDialog(table);
            }
        });
    }

    // Method to refresh table data - UPDATED
    private void refreshTable(JTable table) {
        try {
            Z_Con c = new Z_Con();
            // Same query as initial load
            String q = "SELECT " +
                    "customer_id AS 'Customer ID', " +
                    "id_type AS 'ID Type', " +
                    "first_name AS 'First Name', " +
                    "last_name AS 'Last Name', " +
                    "email AS 'Email', " +
                    "phone AS 'Phone', " +
                    "gender AS 'Gender', " +
                    "country AS 'Country', " +
                    "DATE_FORMAT(checkin_date, '%Y-%m-%d %H:%i') AS 'Check-in Date', " +
                    "deposit AS 'Deposit ($)', " +
                    "room_number AS 'Room No' " +
                    "FROM customer " +
                    "ORDER BY checkin_date DESC, customer_id";

            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

            // Show success message
            int rowCount = table.getRowCount();
            JOptionPane.showMessageDialog(this,
                    "Customer data refreshed successfully!\n" +
                            "Total Customers: " + rowCount,
                    "Refresh Complete",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error refreshing customer data: " + e.getMessage(),
                    "Refresh Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to show search dialog
    private void showSearchDialog(JTable table) {
        String[] options = {"Customer ID", "First Name", "Last Name", "Room Number", "Email", "Phone"};
        JComboBox<String> searchField = new JComboBox<>(options);
        JTextField searchValue = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(new JLabel("Search by:"));
        panel.add(searchField);
        panel.add(new JLabel("Search value:"));
        panel.add(searchValue);

        int result = JOptionPane.showConfirmDialog(this, panel, "Search Customer",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String field = searchField.getSelectedItem().toString();
            String value = searchValue.getText().trim();

            if (!value.isEmpty()) {
                searchCustomers(table, field, value);
            }
        }
    }

    // Method to search customers
    private void searchCustomers(JTable table, String field, String value) {
        try {
            Z_Con c = new Z_Con();
            String columnName = "";

            // Map UI field names to database column names
            switch(field) {
                case "Customer ID": columnName = "customer_id"; break;
                case "First Name": columnName = "first_name"; break;
                case "Last Name": columnName = "last_name"; break;
                case "Room Number": columnName = "room_number"; break;
                case "Email": columnName = "email"; break;
                case "Phone": columnName = "phone"; break;
            }

            String q = "SELECT " +
                    "customer_id AS 'Customer ID', " +
                    "id_type AS 'ID Type', " +
                    "first_name AS 'First Name', " +
                    "last_name AS 'Last Name', " +
                    "email AS 'Email', " +
                    "phone AS 'Phone', " +
                    "gender AS 'Gender', " +
                    "country AS 'Country', " +
                    "DATE_FORMAT(checkin_date, '%Y-%m-%d %H:%i') AS 'Check-in Date', " +
                    "deposit AS 'Deposit ($)', " +
                    "room_number AS 'Room No' " +
                    "FROM customer " +
                    "WHERE " + columnName + " LIKE '%" + value + "%' " +
                    "ORDER BY checkin_date DESC";

            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

            int rowCount = table.getRowCount();
            if (rowCount == 0) {
                JOptionPane.showMessageDialog(this, "No customers found for: " + value);
            } else {
                JOptionPane.showMessageDialog(this, "Found " + rowCount + " customer(s)");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching: " + e.getMessage());
        }
    }

    // Method to style buttons
    private void styleButton(JButton button, Color baseColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(baseColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 215, 0), 1),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(baseColor.brighter());
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(255, 215, 0, 200), 2),
                        BorderFactory.createEmptyBorder(10, 25, 10, 25)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(baseColor);
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(255, 215, 0), 1),
                        BorderFactory.createEmptyBorder(10, 25, 10, 25)
                ));
            }
        });
    }

    // Method to center the window on screen
    private void centerWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
    }

    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new I_CustomerInfo();
        });
    }
}