package Hotel_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class E_NewCustomer extends JFrame implements ActionListener {
    JComboBox<String> comboBox;
    JTextField textFieldNumber, TextName, TextCountry, TextDeposite;
    JRadioButton r1, r2;
    Choice c1;
    JLabel dateLabel;
    JButton add, back;

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
        mainPanel.setBounds(0, 0, 1000, 650); // زيادة الحجم لاستيعاب المحتوى

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
        formPanel.setPreferredSize(new Dimension(950, 550)); // حجم أكبر للنموذج

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
        // ID Type
        JLabel labelID = new JLabel("ID Type:");
        labelID.setBounds(100, 120, 180, 25);
        labelID.setForeground(new Color(240, 240, 240));
        labelID.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelID);

        comboBox = new JComboBox<>(new String[]{"Passport", "Voter Id", "Driving License"});
        comboBox.setBounds(320, 120, 250, 35);
        comboBox.setBackground(new Color(255, 255, 255, 30));
        comboBox.setForeground(Color.WHITE);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 215, 0, 100), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(comboBox);

        // ID Number
        JLabel labelNumber = new JLabel("ID Number:");
        labelNumber.setBounds(100, 170, 180, 25);
        labelNumber.setForeground(new Color(240, 240, 240));
        labelNumber.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelNumber);

        textFieldNumber = createStyledTextField();
        textFieldNumber.setBounds(320, 170, 250, 35);
        formPanel.add(textFieldNumber);

        // Name
        JLabel labelNameField = new JLabel("Full Name:");
        labelNameField.setBounds(100, 220, 180, 25);
        labelNameField.setForeground(new Color(240, 240, 240));
        labelNameField.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelNameField);

        TextName = createStyledTextField();
        TextName.setBounds(320, 220, 250, 35);
        formPanel.add(TextName);

        // Gender
        JLabel labelGender = new JLabel("Gender:");
        labelGender.setBounds(100, 270, 180, 25);
        labelGender.setForeground(new Color(240, 240, 240));
        labelGender.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelGender);

        JPanel genderPanel = new JPanel();
        genderPanel.setBounds(320, 270, 250, 35);
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

        // Country
        JLabel labelCountry = new JLabel("Country:");
        labelCountry.setBounds(100, 320, 180, 25);
        labelCountry.setForeground(new Color(240, 240, 240));
        labelCountry.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelCountry);

        TextCountry = createStyledTextField();
        TextCountry.setBounds(320, 320, 250, 35);
        formPanel.add(TextCountry);

        // Room number
        JLabel labelRoom = new JLabel("Allocated Room:");
        labelRoom.setBounds(100, 370, 180, 25);
        labelRoom.setForeground(new Color(240, 240, 240));
        labelRoom.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelRoom);

        c1 = new Choice();
        try {
            Z_Con c = new Z_Con();
            ResultSet rs = c.statement.executeQuery("SELECT roomnumber FROM room WHERE availability='Available'");
            while (rs.next()) {
                c1.add(rs.getString("roomnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(320, 370, 250, 35);
        c1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        c1.setForeground(Color.WHITE);
        c1.setBackground(new Color(255, 255, 255, 30));
        formPanel.add(c1);

        // Column 2 - اليمين (المعلومات الإضافية)
        // Check-in date
        JLabel labelCIS = new JLabel("Check-in Time:");
        labelCIS.setBounds(620, 120, 180, 25);
        labelCIS.setForeground(new Color(240, 240, 240));
        labelCIS.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelCIS);

        JPanel datePanel = new JPanel();
        datePanel.setBounds(770, 120, 200, 35);
        datePanel.setLayout(new BorderLayout());
        datePanel.setBackground(new Color(255, 255, 255, 30));
        datePanel.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0, 100), 1));

        dateLabel = new JLabel(currentDateTime);
        dateLabel.setForeground(new Color(255, 215, 0));
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dateLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        datePanel.add(dateLabel, BorderLayout.CENTER);
        formPanel.add(datePanel);

        // Deposit
        JLabel labelDeposit = new JLabel("Deposit ($):");
        labelDeposit.setBounds(620, 170, 180, 25);
        labelDeposit.setForeground(new Color(240, 240, 240));
        labelDeposit.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(labelDeposit);

        TextDeposite = createStyledTextField();
        TextDeposite.setBounds(770, 170, 200, 35);
        formPanel.add(TextDeposite);

        // Info panel على اليمين - أعلى لتجنب التداخل مع الأزرار
        JPanel infoPanel = new JPanel();
        infoPanel.setBounds(620, 220, 350, 180); // زيادة العرض والموقع
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
                "• Contact reception for assistance\n" +
                "• All fields marked with * are required");
        infoText.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        infoText.setForeground(new Color(220, 220, 255));
        infoText.setBackground(new Color(255, 255, 255, 0));
        infoText.setEditable(false);
        infoText.setLineWrap(true);
        infoText.setWrapStyleWord(true);

        infoPanel.add(infoText, BorderLayout.CENTER);
        formPanel.add(infoPanel);

        // Buttons Panel - في الأسفل مع مساحة أكبر
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(250, 450, 500, 60); // توسيع المساحة للأزرار
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0)); // زيادة المسافة بين الأزرار
        buttonPanel.setOpaque(false);

        add = createStyledButton("➕ ADD CUSTOMER", new Color(60, 179, 113), 200); // Green - عرض أكبر
        back = createStyledButton("↩️ BACK TO DASHBOARD", new Color(70, 130, 180), 200); // Blue - عرض أكبر

        buttonPanel.add(add);
        buttonPanel.add(back);

        formPanel.add(buttonPanel);

        // إضافة formPanel إلى JScrollPane
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBounds(20, 20, 960, 610);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(0, 0, 0, 0));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // إخفاء شريط التمرير إذا لم يكن ضرورياً
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(scrollPane);

        // إضافة جميع المكونات
        add(mainPanel);

        setLayout(null);
        setSize(1000, 650); // أولاً: تعيين الحجم
        setLocationRelativeTo(null); // ثانياً: التوسيط بعد معرفة الحجم
        setResizable(false); // ثالثاً: إعداد قابلية تغيير الحجم
        setVisible(true); // أخيراً: إظهار النافذة
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

        // تخصيص الأيقونة
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

                // تدرج لوني للزر
                GradientPaint gradient = new GradientPaint(
                        0, 0, bgColor,
                        0, getHeight(), bgColor.darker()
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

                // حدود ذهبية
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
        button.setPreferredSize(new Dimension(width, 45)); // عرض مخصص
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            // التحقق من الحقول المطلوبة
            if (!validateForm()) {
                return;
            }

            Z_Con c = new Z_Con();
            String gender = r1.isSelected() ? "Male" : r2.isSelected() ? "Female" : null;

            String idType = (String) comboBox.getSelectedItem();
            String idNumber = textFieldNumber.getText();
            String name = TextName.getText();
            String country = TextCountry.getText();
            String roomNumber = c1.getSelectedItem();
            String checkinTime = dateLabel.getText();
            String deposit = TextDeposite.getText();

            try {
                String insertQuery = "INSERT INTO customer(id_type, id_number, name, gender, country, roomnumber, checkin_time, deposit) " +
                        "VALUES ('" + idType + "', '" + idNumber + "', '" + name + "', '" + gender + "', '" + country + "', '" + roomNumber + "', '" + checkinTime + "', '" + deposit + "')";
                String updateRoom = "UPDATE room SET availability='Occupied' WHERE roomnumber=" + roomNumber;

                c.statement.executeUpdate(insertQuery);
                c.statement.executeUpdate(updateRoom);

                JOptionPane.showMessageDialog(this,
                        "✅ Customer Added Successfully!\n" +
                                "Name: " + name + "\n" +
                                "Room: " + roomNumber + "\n" +
                                "Check-in: " + checkinTime,
                        "Registration Complete",
                        JOptionPane.INFORMATION_MESSAGE);

                // إعادة تعيين النموذج
                resetForm();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "❌ Error adding customer!\n" + ex.getMessage(),
                        "Registration Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == back) {
            dispose();
            // العودة للوحة الاستقبال
            new C_Reception();
        }
    }

    private boolean validateForm() {
        if (textFieldNumber.getText().trim().isEmpty()) {
            showError("Please enter ID Number");
            textFieldNumber.requestFocus();
            return false;
        }
        if (TextName.getText().trim().isEmpty()) {
            showError("Please enter Full Name");
            TextName.requestFocus();
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
        if (c1.getSelectedItem() == null) {
            showError("No rooms available. Please contact reception.");
            return false;
        }
        if (TextDeposite.getText().trim().isEmpty()) {
            showError("Please enter Deposit amount");
            TextDeposite.requestFocus();
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
        TextCountry.setText("");
        TextDeposite.setText("");

        // إلغاء تحديد الجنس
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);
        genderGroup.clearSelection();

        // تحديث قائمة الغرف المتاحة
        c1.removeAll();
        try {
            Z_Con c = new Z_Con();
            ResultSet rs = c.statement.executeQuery("SELECT roomnumber FROM room WHERE availability='Available'");
            while (rs.next()) {
                c1.add(rs.getString("roomnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // تحديث الوقت
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateLabel.setText(formatter.format(new Date()));
    }

    public static void main(String[] args) {
        // إعداد Look and Feel
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