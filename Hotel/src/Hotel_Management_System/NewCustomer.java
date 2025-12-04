package Hotel_Management_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewCustomer extends JFrame implements ActionListener {
    JComboBox<String> comboBox;
    JTextField textFieldNumber, TextName, TextCountry, TextDeposite;
    JRadioButton r1, r2;
    Choice c1;
    JLabel dateLabel;

    JButton add, back;

    public NewCustomer() {
        // تنسيق التاريخ المناسب لقاعدة البيانات
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = formatter.format(now);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 540);
        panel.setLayout(null);
        panel.setBackground(new Color(3, 45, 48));
        add(panel);

        JLabel labelName = new JLabel("NEW CUSTOMER FORM");
        labelName.setBounds(118, 11, 260, 53);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelName.setForeground(Color.WHITE);
        panel.add(labelName);

        // ID Type
        JLabel labelID = new JLabel("ID :");
        labelID.setBounds(35, 76, 200, 14);
        labelID.setForeground(Color.WHITE);
        labelID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelID);

        comboBox = new JComboBox<>(new String[]{"Passport", "Voter Id", "Driving License"});
        comboBox.setBounds(271, 73, 150, 20);
        comboBox.setBackground(new Color(3, 45, 48));
        comboBox.setForeground(Color.WHITE);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(comboBox);

        // ID Number
        JLabel labelNumber = new JLabel("Number :");
        labelNumber.setBounds(35, 111, 200, 14);
        labelNumber.setForeground(Color.WHITE);
        labelNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelNumber);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(271, 111, 150, 20);
        panel.add(textFieldNumber);

        // Name
        JLabel labelNameField = new JLabel("Name :");
        labelNameField.setBounds(35, 151, 200, 14);
        labelNameField.setForeground(Color.WHITE);
        labelNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelNameField);

        TextName = new JTextField();
        TextName.setBounds(271, 151, 150, 20);
        panel.add(TextName);

        // Gender
        JLabel labelGender = new JLabel("Gender :");
        labelGender.setBounds(35, 191, 200, 14);
        labelGender.setForeground(Color.WHITE);
        labelGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Tahoma", Font.BOLD, 14));
        r1.setForeground(Color.WHITE);
        r1.setBackground(new Color(3, 45, 48));
        r1.setBounds(271, 191, 80, 12);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Tahoma", Font.BOLD, 14));
        r2.setForeground(Color.WHITE);
        r2.setBackground(new Color(3, 45, 48));
        r2.setBounds(350, 191, 80, 12);
        panel.add(r2);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);

        // Country
        JLabel labelCountry = new JLabel("Country :");
        labelCountry.setBounds(35, 231, 200, 14);
        labelCountry.setForeground(Color.WHITE);
        labelCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelCountry);

        TextCountry = new JTextField();
        TextCountry.setBounds(271, 231, 150, 20);
        panel.add(TextCountry);

        // Room number
        JLabel labelRoom = new JLabel("Allocated Room Number :");
        labelRoom.setBounds(35, 274, 200, 14);
        labelRoom.setForeground(Color.WHITE);
        labelRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelRoom);

        c1 = new Choice();
        try {
            Con c = new Con();
            ResultSet rs = c.statement.executeQuery("SELECT roomnumber FROM room WHERE availability='Available'");
            while (rs.next()) {
                c1.add(rs.getString("roomnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(271, 274, 150, 20);
        c1.setFont(new Font("Tahoma", Font.BOLD, 14));
        c1.setForeground(Color.WHITE);
        c1.setBackground(new Color(3, 45, 48));
        panel.add(c1);

        // Check-in date
        JLabel labelCIS = new JLabel("Checked-In :");
        labelCIS.setBounds(35, 316, 200, 14);
        labelCIS.setForeground(Color.WHITE);
        labelCIS.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelCIS);

        dateLabel = new JLabel(currentDateTime);
        dateLabel.setBounds(271, 316, 200, 14);
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(dateLabel);

        // Deposit
        JLabel labelDeposit = new JLabel("Deposit :");
        labelDeposit.setBounds(35, 359, 200, 14);
        labelDeposit.setForeground(Color.WHITE);
        labelDeposit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(labelDeposit);

        TextDeposite = new JTextField();
        TextDeposite.setBounds(271, 359, 150, 20);
        panel.add(TextDeposite);

        // Buttons
        add = new JButton("ADD");
        add.setBounds(100, 430, 120, 30);
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.addActionListener(this);
        panel.add(add);

        back = new JButton("BACK");
        back.setBounds(260, 430, 120, 30);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        panel.add(back);

        setLayout(null);
        setLocation(500, 150);
        setSize(850, 550);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            Con c = new Con();
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

                JOptionPane.showMessageDialog(null, "Added Successfully");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}
