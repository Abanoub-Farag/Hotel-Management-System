package Hotel_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.PreparedStatement;

public class E_NewCustomer extends JFrame implements ActionListener {
    JComboBox<String> comboBox;
    JTextField textFieldNumber, TextName, TextCountry, TextDeposite, TextEmail, TextPhone;
    JRadioButton r1, r2;
    JComboBox<String> roomComboBox; // CHANGED: Use JComboBox instead of Choice
    JLabel dateLabel;
    JButton add, back;
    private JTextField lastNameField;

    public E_NewCustomer() {
        super("New Customer Registration");

        // تنسيق التاريخ المناسب لقاعدة البيانات
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = formatter.format(now);

        // إنشاء لوحة رئيسية مع خلفية متدرجة
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(15, 30, 45),      // Dark blue
                        getWidth(), getHeight(), new Color(60, 20, 60)  // Purple
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 1060, 720);

        // لوحة النموذج الرئيسية مع ScrollPane
        JPanel formPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // خلفية زجاجية للنموذج
                g2d.setColor(new Color(255, 255, 255, 20));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                // حدود ذهبية
                g2d.setColor(new Color(255, 215, 0, 80));
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 25, 25);
            }
        };
        formPanel.setLayout(null);
        formPanel.setPreferredSize(new Dimension(950, 650));

        // عنوان النموذج
        JLabel labelName = new JLabel("✨ NEW CUSTOMER REGISTRATION ✨");
        labelName.setBounds(220, 20, 510, 50);
        labelName.setFont(new Font("Segoe UI", Font.BOLD, 26));
        labelName.setForeground(new Color(255, 215, 0));
        labelName.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(labelName);

        // خط فاصل تحت العنوان
        JSeparator separator = new JSeparator();
        separator.setBounds(70, 80, 810, 2);
        separator.setForeground(new Color(255, 215, 0, 100));
        formPanel.add(separator);

        // Column 1 - اليسار (الحقول الأساسية)
        int yPos = 120;

        // ID Type
        JLabel labelID = new JLabel("ID Type:");
        labelID.setBounds(100, yPos, 180, 25);
        labelID.setForeground(new Color(240, 240, 240));
        labelID.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelID);

        comboBox = new JComboBox<>(new String[]{"Passport", "Driving License", "National ID"});
        comboBox.setBounds(320, yPos, 250, 35);
        comboBox.setBackground(new Color(255, 255, 255, 30));
        comboBox.setForeground(Color.WHITE);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 215, 0, 100), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(comboBox);

        yPos += 50;

        // ID Number (Customer ID)
        JLabel labelNumber = new JLabel("ID Number:");
        labelNumber.setBounds(100, yPos, 180, 25);
        labelNumber.setForeground(new Color(240, 240, 240));
        labelNumber.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelNumber);

        textFieldNumber = createStyledTextField();
        textFieldNumber.setBounds(320, yPos, 250, 35);
        formPanel.add(textFieldNumber);

        yPos += 50;

        // First Name
        JLabel labelFirstName = new JLabel("First Name:");
        labelFirstName.setBounds(100, yPos, 180, 25);
        labelFirstName.setForeground(new Color(240, 240, 240));
        labelFirstName.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelFirstName);

        TextName = createStyledTextField();
        TextName.setBounds(320, yPos, 250, 35);
        formPanel.add(TextName);

        yPos += 50;

        // Last Name
        JLabel labelLastName = new JLabel("Last Name:");
        labelLastName.setBounds(100, yPos, 180, 25);
        labelLastName.setForeground(new Color(240, 240, 240));
        labelLastName.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelLastName);

        lastNameField = createStyledTextField();
        lastNameField.setBounds(320, yPos, 250, 35);
        formPanel.add(lastNameField);

        yPos += 50;

        // Email
        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setBounds(100, yPos, 180, 25);
        labelEmail.setForeground(new Color(240, 240, 240));
        labelEmail.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelEmail);

        TextEmail = createStyledTextField();
        TextEmail.setBounds(320, yPos, 250, 35);
        formPanel.add(TextEmail);

        yPos += 50;

        // Phone
        JLabel labelPhone = new JLabel("Phone:");
        labelPhone.setBounds(100, yPos, 180, 25);
        labelPhone.setForeground(new Color(240, 240, 240));
        labelPhone.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelPhone);

        TextPhone = createStyledTextField();
        TextPhone.setBounds(320, yPos, 250, 35);
        formPanel.add(TextPhone);

        yPos += 50;

        // Gender
        JLabel labelGender = new JLabel("Gender:");
        labelGender.setBounds(100, yPos, 180, 25);
        labelGender.setForeground(new Color(240, 240, 240));
        labelGender.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelGender);

        JPanel genderPanel = new JPanel();
        genderPanel.setBounds(320, yPos, 250, 35);
        genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
        genderPanel.setOpaque(false);

        r1 = createStyledRadioButton("Male");
        r2 = createStyledRadioButton("Female");

        genderPanel.add(r1);
        genderPanel.add(r2);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);
        formPanel.add(genderPanel);

        yPos += 50;

        // Country
        JLabel labelCountry = new JLabel("Country:");
        labelCountry.setBounds(100, yPos, 180, 25);
        labelCountry.setForeground(new Color(240, 240, 240));
        labelCountry.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelCountry);

        TextCountry = createStyledTextField();
        TextCountry.setBounds(320, yPos, 250, 35);
        formPanel.add(TextCountry);

        yPos += 50;

        // Room number - FIXED: Use JComboBox instead of Choice
        JLabel labelRoom = new JLabel("Allocated Room:");
        labelRoom.setBounds(100, yPos, 180, 25);
        labelRoom.setForeground(new Color(240, 240, 240));
        labelRoom.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelRoom);

        // Create JComboBox for rooms
        roomComboBox = new JComboBox<>();
        roomComboBox.setBounds(320, yPos, 250, 35);
        roomComboBox.setBackground(new Color(255, 255, 255, 30));
        roomComboBox.setForeground(Color.WHITE);
        roomComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        roomComboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 215, 0, 100), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // Load available rooms
        loadAvailableRooms();

        formPanel.add(roomComboBox);

        // Column 2 - اليمين (المعلومات الإضافية)
        int yPosRight = 120;

        // Check-in date
        JLabel labelCIS = new JLabel("Check-in Date:");
        labelCIS.setBounds(620, yPosRight, 180, 25);
        labelCIS.setForeground(new Color(240, 240, 240));
        labelCIS.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelCIS);

        JPanel datePanel = new JPanel();
        datePanel.setBounds(770, yPosRight, 200, 35);
        datePanel.setLayout(new BorderLayout());
        datePanel.setBackground(new Color(255, 255, 255, 30));
        datePanel.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0, 100), 1));

        dateLabel = new JLabel(currentDateTime);
        dateLabel.setForeground(new Color(255, 215, 0));
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dateLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        datePanel.add(dateLabel, BorderLayout.CENTER);
        formPanel.add(datePanel);

        yPosRight += 50;

        // Deposit
        JLabel labelDeposit = new JLabel("Deposit ($):");
        labelDeposit.setBounds(620, yPosRight, 180, 25);
        labelDeposit.setForeground(new Color(240, 240, 240));
        labelDeposit.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelDeposit);

        TextDeposite = createStyledTextField();
        TextDeposite.setBounds(770, yPosRight, 200, 35);
        formPanel.add(TextDeposite);

        yPosRight += 50;

        // Info panel على اليمين
        JPanel infoPanel = new JPanel();
        infoPanel.setBounds(620, yPosRight, 350, 200);
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBackground(new Color(255, 255, 255, 15));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 215, 0, 50), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JTextArea infoText = new JTextArea();
        infoText.setText("📝 Registration Guidelines:\n\n" +
                "• Please fill all fields accurately\n" +
                "• ID verification is required\n" +
                "• Deposit is refundable at checkout\n" +
                "• Room allocation is final\n" +
                "• All fields are required\n" +
                "• Email and phone must be valid");
        infoText.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        infoText.setForeground(new Color(220, 220, 255));
        infoText.setBackground(new Color(255, 255, 255, 0));
        infoText.setEditable(false);
        infoText.setLineWrap(true);
        infoText.setWrapStyleWord(true);

        infoPanel.add(infoText, BorderLayout.CENTER);
        formPanel.add(infoPanel);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(250, 580, 500, 60);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
        buttonPanel.setOpaque(false);

        add = createStyledButton("➕ ADD CUSTOMER", new Color(60, 179, 113), 200);
        back = createStyledButton("↩️ BACK TO DASHBOARD", new Color(70, 130, 180), 200);

        buttonPanel.add(add);
        buttonPanel.add(back);

        formPanel.add(buttonPanel);

        // إضافة formPanel إلى JScrollPane
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBounds(20, 20, 960, 610);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(0, 0, 0, 0));
        scrollPane.setOpaque(false);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(scrollPane);

        add(mainPanel);

        setLayout(null);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void loadAvailableRooms() {
        try {
            Z_Con c = new Z_Con();

            // Clear existing items
            roomComboBox.removeAllItems();

            String query = "SELECT room_number FROM room WHERE availability='Available' AND cleaning_status='Clean'";
            System.out.println("Executing query: " + query);

            ResultSet rs = c.statement.executeQuery(query);

            int roomCount = 0;
            while (rs.next()) {
                String roomNumber = rs.getString("room_number");
                roomComboBox.addItem(roomNumber);
                roomCount++;
                System.out.println("Added room: " + roomNumber);
            }

            System.out.println("Total available rooms: " + roomCount);

            if (roomCount == 0) {
                System.out.println("No rooms available!");
                roomComboBox.addItem("No rooms available");
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading rooms: " + e.getMessage());

            roomComboBox.removeAllItems();
            roomComboBox.addItem("Error loading rooms");
        }
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(Color.WHITE);
        field.setBackground(new Color(255, 255, 255, 30));
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 215, 0, 100), 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return field;
    }

    private JRadioButton createStyledRadioButton(String text) {
        JRadioButton radio = new JRadioButton(text);
        radio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        radio.setForeground(Color.WHITE);
        radio.setBackground(new Color(255, 255, 255, 0));
        radio.setFocusPainted(false);
        radio.setCursor(new Cursor(Cursor.HAND_CURSOR));

        radio.setIcon(new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (radio.isSelected()) {
                    g2d.setColor(new Color(255, 215, 0));
                    g2d.fillOval(x, y, 16, 16);
                    g2d.setColor(Color.WHITE);
                    g2d.fillOval(x+4, y+4, 8, 8);
                } else {
                    g2d.setColor(new Color(255, 255, 255, 100));
                    g2d.drawOval(x, y, 16, 16);
                }
                g2d.dispose();
            }

            @Override
            public int getIconWidth() { return 20; }
            @Override
            public int getIconHeight() { return 20; }
        });

        return radio;
    }

    private JButton createStyledButton(String text, Color baseColor, int width) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color bgColor = getModel().isRollover() ? baseColor.brighter() : baseColor;

                GradientPaint gradient = new GradientPaint(
                        0, 0, bgColor,
                        0, getHeight(), bgColor.darker()
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

                g2d.setColor(new Color(255, 215, 0));
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 15, 15);

                super.paintComponent(g);
            }
        };

        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(width, 45));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            if (!validateForm()) {
                return;
            }

            Z_Con c = new Z_Con();
            String gender = r1.isSelected() ? "Male" : r2.isSelected() ? "Female" : null;

            String idType = (String) comboBox.getSelectedItem();
            String idNumber = textFieldNumber.getText();
            String firstName = TextName.getText();
            String lastName = lastNameField.getText();
            String email = TextEmail.getText();
            String phone = TextPhone.getText();
            String country = TextCountry.getText();
            String roomNumber = (String) roomComboBox.getSelectedItem(); // CHANGED
            String checkinDate = dateLabel.getText();
            String deposit = TextDeposite.getText();

            try {
                int customerId;
                try {
                    customerId = Integer.parseInt(idNumber);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Customer ID must be a number!",
                            "Invalid ID",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String checkQuery = "SELECT COUNT(*) FROM customer WHERE customer_id = ?";
                PreparedStatement checkStmt = c.connection.prepareStatement(checkQuery);
                checkStmt.setInt(1, customerId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this,
                            "Customer ID already exists! Please use a different ID.",
                            "Duplicate ID",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String insertQuery = "INSERT INTO customer (customer_id, id_type, first_name, last_name, " +
                        "email, phone, gender, country, checkin_date, deposit, room_number) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement pstmt = c.connection.prepareStatement(insertQuery);
                pstmt.setInt(1, customerId);
                pstmt.setString(2, idType);
                pstmt.setString(3, firstName);
                pstmt.setString(4, lastName);
                pstmt.setString(5, email);
                pstmt.setString(6, phone);
                pstmt.setString(7, gender);
                pstmt.setString(8, country);
                pstmt.setString(9, checkinDate);
                pstmt.setDouble(10, Double.parseDouble(deposit));
                pstmt.setInt(11, Integer.parseInt(roomNumber));

                int rowsInserted = pstmt.executeUpdate();

                if (rowsInserted > 0) {
                    String updateRoom = "UPDATE room SET availability='Occupied' WHERE room_number=" + roomNumber;
                    c.statement.executeUpdate(updateRoom);

                    try {
                        String bookingQuery = "SELECT IFNULL(MAX(booking_id), 0) + 1 AS new_id FROM bookings";
                        ResultSet bookingRs = c.statement.executeQuery(bookingQuery);
                        int bookingId = 1;
                        if (bookingRs.next()) {
                            bookingId = bookingRs.getInt("new_id");
                        }

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date checkin = dateFormat.parse(checkinDate);
                        Date checkout = new Date(checkin.getTime() + (24 * 60 * 60 * 1000));

                        String priceQuery = "SELECT price FROM room WHERE room_number=" + roomNumber;
                        ResultSet priceRs = c.statement.executeQuery(priceQuery);
                        double totalAmount = 0;
                        if (priceRs.next()) {
                            totalAmount = priceRs.getDouble("price");
                        }

                        String insertBooking = "INSERT INTO bookings (booking_id, customer_id, room_number, " +
                                "status, total_amount, checkin_date, checkout_date, booking_date) " +
                                "VALUES (?, ?, ?, 'Active', ?, ?, ?, ?)";

                        PreparedStatement bookingStmt = c.connection.prepareStatement(insertBooking);
                        bookingStmt.setInt(1, bookingId);
                        bookingStmt.setInt(2, customerId);
                        bookingStmt.setInt(3, Integer.parseInt(roomNumber));
                        bookingStmt.setDouble(4, totalAmount);
                        bookingStmt.setString(5, checkinDate);
                        bookingStmt.setString(6, dateFormat.format(checkout));
                        bookingStmt.setString(7, checkinDate);

                        bookingStmt.executeUpdate();

                    } catch (Exception bookingEx) {
                        bookingEx.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(this,
                            "✅ Customer Added Successfully!\n" +
                                    "Customer ID: " + customerId + "\n" +
                                    "Name: " + firstName + " " + lastName + "\n" +
                                    "Room: " + roomNumber + "\n" +
                                    "Check-in: " + checkinDate.substring(0, 16),
                            "Registration Complete",
                            JOptionPane.INFORMATION_MESSAGE);

                    resetForm();
                }

                pstmt.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "❌ Error adding customer!\n" + ex.getMessage(),
                        "Registration Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == back) {
            dispose();
            new C_Reception();
        }
    }

    private boolean validateForm() {
        if (textFieldNumber.getText().trim().isEmpty()) {
            showError("Please enter Customer ID (must be a number)");
            textFieldNumber.requestFocus();
            return false;
        }
        if (TextName.getText().trim().isEmpty()) {
            showError("Please enter First Name");
            TextName.requestFocus();
            return false;
        }
        if (lastNameField.getText().trim().isEmpty()) {
            showError("Please enter Last Name");
            lastNameField.requestFocus();
            return false;
        }
        if (TextEmail.getText().trim().isEmpty()) {
            showError("Please enter Email");
            TextEmail.requestFocus();
            return false;
        }
        if (TextPhone.getText().trim().isEmpty()) {
            showError("Please enter Phone Number");
            TextPhone.requestFocus();
            return false;
        }
        if (!r1.isSelected() && !r2.isSelected()) {
            showError("Please select Gender");
            return false;
        }
        if (TextCountry.getText().trim().isEmpty()) {
            showError("Please enter Country");
            TextCountry.requestFocus();
            return false;
        }

        String selectedRoom = (String) roomComboBox.getSelectedItem();
        if (selectedRoom == null || selectedRoom.equals("No rooms available") || selectedRoom.equals("Error loading rooms")) {
            showError("No rooms available. Please contact reception.");
            return false;
        }

        if (TextDeposite.getText().trim().isEmpty()) {
            showError("Please enter Deposit amount");
            TextDeposite.requestFocus();
            return false;
        }

        String email = TextEmail.getText().trim();
        if (!email.contains("@") || !email.contains(".")) {
            showError("Please enter a valid email address");
            TextEmail.requestFocus();
            return false;
        }

        return true;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
    }

    private void resetForm() {
        textFieldNumber.setText("");
        TextName.setText("");
        lastNameField.setText("");
        TextEmail.setText("");
        TextPhone.setText("");
        TextCountry.setText("");
        TextDeposite.setText("");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);
        genderGroup.clearSelection();

        roomComboBox.removeAllItems();
        loadAvailableRooms();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateLabel.setText(formatter.format(new Date()));
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new E_NewCustomer();
        });
    }
}