package Hotel_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class P_AddEmployee extends JFrame implements ActionListener {

    JTextField firstNameText, lastNameText, ageText, salaryText, phoneText, emailText;
    JRadioButton radioButtonM, radioButtonF;
    JComboBox<String> departmentCombo;
    JButton add, back;
    Z_Con connection;

    P_AddEmployee() {
        // Initialize database connection
        try {
            connection = new Z_Con();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Set frame properties first
        setUndecorated(true);
        setSize(890, 540);

        // Center the window on screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 540);
        panel.setLayout(null);

        // Modern dark blue theme
        panel.setBackground(new Color(30, 30, 50));

        add(panel);

        // Main title
        JLabel AED = new JLabel("ADD EMPLOYEE DETAILS", SwingConstants.CENTER);
        AED.setBounds(450, 24, 445, 35);
        AED.setFont(new Font("Tahoma", Font.BOLD, 28));
        AED.setForeground(new Color(255, 215, 0)); // Gold color
        panel.add(AED);

        // Left side - Input fields
        JLabel firstName = new JLabel("FIRST NAME");
        firstName.setBounds(60, 30, 150, 27);
        firstName.setFont(new Font("Segoe UI", Font.BOLD, 16));
        firstName.setForeground(new Color(200, 200, 220));
        panel.add(firstName);

        firstNameText = new JTextField();
        firstNameText.setBounds(200, 30, 150, 32);
        firstNameText.setBackground(new Color(60, 60, 80));
        firstNameText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        firstNameText.setForeground(Color.WHITE);
        firstNameText.setCaretColor(Color.WHITE);
        firstNameText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 150), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(firstNameText);

        JLabel lastName = new JLabel("LAST NAME");
        lastName.setBounds(60, 80, 150, 27);
        lastName.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lastName.setForeground(new Color(200, 200, 220));
        panel.add(lastName);

        lastNameText = new JTextField();
        lastNameText.setBounds(200, 80, 150, 32);
        lastNameText.setBackground(new Color(60, 60, 80));
        lastNameText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lastNameText.setForeground(Color.WHITE);
        lastNameText.setCaretColor(Color.WHITE);
        lastNameText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 150), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(lastNameText);

        JLabel Age = new JLabel("AGE");
        Age.setBounds(60, 130, 150, 27);
        Age.setFont(new Font("Segoe UI", Font.BOLD, 16));
        Age.setForeground(new Color(200, 200, 220));
        panel.add(Age);

        ageText = new JTextField();
        ageText.setBounds(200, 130, 150, 32);
        ageText.setBackground(new Color(60, 60, 80));
        ageText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ageText.setForeground(Color.WHITE);
        ageText.setCaretColor(Color.WHITE);
        ageText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 150), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(ageText);

        JLabel gender = new JLabel("GENDER");
        gender.setBounds(60, 180, 150, 27);
        gender.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gender.setForeground(new Color(200, 200, 220));
        panel.add(gender);

        ButtonGroup genderGroup = new ButtonGroup();
        radioButtonM = new JRadioButton("MALE");
        radioButtonM.setBounds(200, 180, 80, 27);
        radioButtonM.setBackground(new Color(30, 30, 50));
        radioButtonM.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        radioButtonM.setForeground(new Color(200, 200, 220));
        radioButtonM.setFocusPainted(false);
        panel.add(radioButtonM);

        radioButtonF = new JRadioButton("FEMALE");
        radioButtonF.setBounds(280, 180, 100, 27);
        radioButtonF.setBackground(new Color(30, 30, 50));
        radioButtonF.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        radioButtonF.setForeground(new Color(200, 200, 220));
        radioButtonF.setFocusPainted(false);
        panel.add(radioButtonF);

        genderGroup.add(radioButtonM);
        genderGroup.add(radioButtonF);

        JLabel department = new JLabel("DEPARTMENT");
        department.setBounds(60, 230, 150, 27);
        department.setFont(new Font("Segoe UI", Font.BOLD, 16));
        department.setForeground(new Color(200, 200, 220));
        panel.add(department);

        // Load departments from database
        departmentCombo = new JComboBox<>();
        loadDepartments();
        departmentCombo.setBackground(new Color(60, 60, 80));
        departmentCombo.setBounds(200, 230, 150, 32);
        departmentCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        departmentCombo.setForeground(Color.WHITE);
        departmentCombo.setFocusable(false);
        departmentCombo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 150), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(departmentCombo);

        JLabel salary = new JLabel("SALARY");
        salary.setBounds(60, 280, 150, 27);
        salary.setFont(new Font("Segoe UI", Font.BOLD, 16));
        salary.setForeground(new Color(200, 200, 220));
        panel.add(salary);

        salaryText = new JTextField();
        salaryText.setBounds(200, 280, 150, 32);
        salaryText.setBackground(new Color(60, 60, 80));
        salaryText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        salaryText.setForeground(Color.WHITE);
        salaryText.setCaretColor(Color.WHITE);
        salaryText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 150), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(salaryText);

        JLabel phone = new JLabel("PHONE");
        phone.setBounds(60, 330, 150, 27);
        phone.setFont(new Font("Segoe UI", Font.BOLD, 16));
        phone.setForeground(new Color(200, 200, 220));
        panel.add(phone);

        phoneText = new JTextField();
        phoneText.setBounds(200, 330, 150, 32);
        phoneText.setBackground(new Color(60, 60, 80));
        phoneText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        phoneText.setForeground(Color.WHITE);
        phoneText.setCaretColor(Color.WHITE);
        phoneText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 150), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(phoneText);

        JLabel email = new JLabel("EMAIL");
        email.setBounds(60, 380, 150, 27);
        email.setFont(new Font("Segoe UI", Font.BOLD, 16));
        email.setForeground(new Color(200, 200, 220));
        panel.add(email);

        emailText = new JTextField();
        emailText.setBounds(200, 380, 150, 32);
        emailText.setBackground(new Color(60, 60, 80));
        emailText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailText.setForeground(Color.WHITE);
        emailText.setCaretColor(Color.WHITE);
        emailText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 150), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(emailText);

        // Right side - decorative element
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(450, 80, 400, 350);
        rightPanel.setBackground(new Color(40, 40, 60));
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 2));

        JLabel iconLabel = new JLabel("", SwingConstants.CENTER);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 100));
        iconLabel.setForeground(new Color(255, 215, 0));
        iconLabel.setText("👨‍💼");

        JLabel descLabel = new JLabel("<html><center>Add new employee<br>to the system</center></html>", SwingConstants.CENTER);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        descLabel.setForeground(new Color(200, 200, 220));

        rightPanel.add(iconLabel, BorderLayout.CENTER);
        rightPanel.add(descLabel, BorderLayout.SOUTH);
        panel.add(rightPanel);

        // Buttons with modern styling
        add = new JButton("ADD EMPLOYEE");
        add.setBounds(80, 440, 180, 40);
        add.setBackground(new Color(70, 130, 180)); // Steel blue
        add.setForeground(Color.WHITE);
        add.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 149, 237), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        add.setFocusPainted(false);
        add.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add.addActionListener(this);
        panel.add(add);

        back = new JButton("BACK");
        back.setBounds(280, 440, 180, 40);
        back.setBackground(new Color(220, 20, 60)); // Crimson red
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Segoe UI", Font.BOLD, 14));
        back.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(178, 34, 34), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        back.setFocusPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(this);
        panel.add(back);

        setLayout(null);
        setVisible(true);
    }

    private void loadDepartments() {
        try {
            String query = "SELECT department_id, department_name FROM department ORDER BY department_name";
            ResultSet rs = connection.statement.executeQuery(query);

            while (rs.next()) {
                int deptId = rs.getInt("department_id");
                String deptName = rs.getString("department_name");
                departmentCombo.addItem(deptId + " - " + deptName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading departments: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String firstName = firstNameText.getText().trim();
            String lastName = lastNameText.getText().trim();
            String age = ageText.getText().trim();
            String salary = salaryText.getText().trim();
            String phone = phoneText.getText().trim();
            String email = emailText.getText().trim();

            // Get selected department
            String selectedDept = (String) departmentCombo.getSelectedItem();
            int departmentId = 0;
            if (selectedDept != null && !selectedDept.isEmpty()) {
                try {
                    departmentId = Integer.parseInt(selectedDept.split(" - ")[0]);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid department selected",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String gender = null;
            if (radioButtonM.isSelected()) {
                gender = "Male";
            } else if (radioButtonF.isSelected()) {
                gender = "Female";
            } else {
                JOptionPane.showMessageDialog(this, "Please select gender", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validate required fields
            if (firstName.isEmpty() || lastName.isEmpty() || age.isEmpty() || salary.isEmpty() ||
                    phone.isEmpty() || email.isEmpty() || departmentId == 0) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validate numeric fields
            try {
                int ageValue = Integer.parseInt(age);
                if (ageValue < 18 || ageValue > 70) {
                    JOptionPane.showMessageDialog(this, "Age must be between 18 and 70",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                float salaryValue = Float.parseFloat(salary);
                if (salaryValue <= 0) {
                    JOptionPane.showMessageDialog(this, "Salary must be greater than 0",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age and Salary must be valid numbers",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // Get current timestamp for hire_date
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String hireDate = now.format(formatter);

                // Get next employee_id
                String maxIdQuery = "SELECT COALESCE(MAX(employee_id), 0) + 1 AS next_id FROM employee";
                ResultSet rs = connection.statement.executeQuery(maxIdQuery);
                int nextEmployeeId = 1;
                if (rs.next()) {
                    nextEmployeeId = rs.getInt("next_id");
                }

                // Prepare insert query (without manager_id)
                String query = "INSERT INTO employee(employee_id, first_name, last_name, age, gender, " +
                        "salary, phone, email, department_id, hire_date) " +
                        "VALUES (" + nextEmployeeId + ", '" + firstName + "', '" + lastName + "', " +
                        age + ", '" + gender + "', " + salary + ", '" + phone + "', '" + email + "', " +
                        departmentId + ", '" + hireDate + "')";

                int rowsAffected = connection.statement.executeUpdate(query);

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Employee Added Successfully\nEmployee ID: " + nextEmployeeId,
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add employee",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding employee: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            setVisible(false);
            new D_admin(); // Assuming this opens the admin dashboard
        }
    }

    private void clearFields() {
        firstNameText.setText("");
        lastNameText.setText("");
        ageText.setText("");
        salaryText.setText("");
        phoneText.setText("");
        emailText.setText("");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioButtonM);
        genderGroup.add(radioButtonF);
        genderGroup.clearSelection();
        departmentCombo.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        // Use SwingUtilities for thread safety
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new P_AddEmployee();
        });
    }
}