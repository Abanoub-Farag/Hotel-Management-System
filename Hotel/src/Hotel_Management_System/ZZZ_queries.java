package Hotel_Management_System;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ZZZ_queries {

    // for login1
    public static boolean checkLogin1(String user, String pass){
        boolean valid = false;
        try {
            Z_Con c = new Z_Con();
            String q = "SELECT * FROM login WHERE username = ? AND password = ?";
            PreparedStatement pst = c.connection.prepareStatement(q);
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            valid = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valid;
    }

    // for login2
    public static boolean checkLogin2(String user, String pass){
        boolean valid = false;
        try {
            Z_Con c = new Z_Con();
            String q = "SELECT * FROM login2 WHERE username = ? AND password = ?";
            PreparedStatement pst = c.connection.prepareStatement(q);
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            valid = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valid;
    }

    // for rooms
    public static void loadAvailableRooms(JComboBox<String> roomComboBox){
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

    public static final String LOAD_ROOMS =
            "SELECT room_number, availability, cleaning_status, bed_type, price " +
                    "FROM room ORDER BY room_number";

    public static String roomCount(String availability){
        try {
            Z_Con c = new Z_Con();
            ResultSet rs = c.statement.executeQuery("SELECT COUNT(*) FROM room WHERE availability='" + availability + "'");
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    public static String cleanCount(String cleanStatus){
        try {
            Z_Con c = new Z_Con();
            ResultSet rs = c.statement.executeQuery("SELECT COUNT(*) FROM room WHERE cleaning_status='" + cleanStatus + "'");
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    // add customer

    public static boolean customerExists(int idNumber) {
        try {
            Z_Con c = new Z_Con();
            String query = "SELECT COUNT(*) FROM customer WHERE customer_id = ?";
            PreparedStatement pstmt = c.connection.prepareStatement(query);
            pstmt.setInt(1, idNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                pstmt.close();
                return true;
            }
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean customerExistsWithRoom(int idNumber){
        try {
            Z_Con c = new Z_Con();
            String query = "SELECT COUNT(*) FROM customer WHERE customer_id = ? AND room_number IS NOT NULL";
            PreparedStatement pstmt = c.connection.prepareStatement(query);
            pstmt.setInt(1, idNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                pstmt.close();
                return true;
            }
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean customerExistsWithNORoom(int idNumber){
        try {
            Z_Con c = new Z_Con();
            String query = "SELECT COUNT(*) FROM customer WHERE customer_id = ? AND room_number IS NULL";
            PreparedStatement pstmt = c.connection.prepareStatement(query);
            pstmt.setInt(1, idNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                pstmt.close();
                return true;
            }
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addCustomer(
            int customerId,
            String idType,
            String firstName,
            String lastName,
            String email,
            String phone,
            String gender,
            String country,
            String checkinDate,
            double deposit,
            int roomNumber
    ) throws Exception {

        // 1) تحقق من تكرار Customer ID

        if (customerExistsWithRoom(customerId)) {
            throw new Exception("Customer ID already exists");
        }

        else if(customerExistsWithNORoom(customerId)){
            Z_Con c = new Z_Con();

            String q = "UPDATE customer SET room_number = ? WHERE customer_id = ? AND room_number IS NULL";

            PreparedStatement pst = c.connection.prepareStatement(q);
            pst.setInt(1, roomNumber);
            pst.setInt(2, customerId);
            pst.executeUpdate();

            String updateRoom = "UPDATE room SET availability='Occupied' WHERE room_number=?";
            PreparedStatement roomStmt = c.connection.prepareStatement(updateRoom);
            roomStmt.setInt(1, roomNumber);
            roomStmt.executeUpdate();

            int bookingId = 1;
            ResultSet bookingRs = c.statement.executeQuery(
                    "SELECT IFNULL(MAX(booking_id),0)+1 AS new_id FROM bookings"
            );
            if (bookingRs.next()) {
                bookingId = bookingRs.getInt("new_id");
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date checkin = df.parse(checkinDate);
            Date checkout = new Date(checkin.getTime() + (24 * 60 * 60 * 1000));

            double totalAmount = 0;
            ResultSet priceRs = c.statement.executeQuery(
                    "SELECT price FROM room WHERE room_number=" + roomNumber
            );
            if (priceRs.next()) {
                totalAmount = priceRs.getDouble("price");
            }

            String insertBooking =
                    "INSERT INTO bookings (booking_id, customer_id, room_number, status, total_amount, checkin_date, checkout_date, booking_date) " +
                            "VALUES (?, ?, ?, 'Active', ?, ?, ?, ?)";

            PreparedStatement bookingStmt = c.connection.prepareStatement(insertBooking);
            bookingStmt.setInt(1, bookingId);
            bookingStmt.setInt(2, customerId);
            bookingStmt.setInt(3, roomNumber);
            bookingStmt.setDouble(4, totalAmount);
            bookingStmt.setString(5, checkinDate);
            bookingStmt.setString(6, df.format(checkout));
            bookingStmt.setString(7, checkinDate);

            bookingStmt.executeUpdate();
        }
        else{
            Z_Con c = new Z_Con();
            String insertCustomer =
                    "INSERT INTO customer (customer_id, id_type, first_name, last_name, email, phone, gender, country, checkin_date, deposit, room_number) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = c.connection.prepareStatement(insertCustomer);
            pstmt.setInt(1, customerId);
            pstmt.setString(2, idType);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, email);
            pstmt.setString(6, phone);
            pstmt.setString(7, gender);
            pstmt.setString(8, country);
            pstmt.setString(9, checkinDate);
            pstmt.setDouble(10, deposit);
            pstmt.setInt(11, roomNumber);

            pstmt.executeUpdate();

            String updateRoom = "UPDATE room SET availability='Occupied' WHERE room_number=?";
            PreparedStatement roomStmt = c.connection.prepareStatement(updateRoom);
            roomStmt.setInt(1, roomNumber);
            roomStmt.executeUpdate();

            int bookingId = 1;
            ResultSet bookingRs = c.statement.executeQuery(
                    "SELECT IFNULL(MAX(booking_id),0)+1 AS new_id FROM bookings"
            );
            if (bookingRs.next()) {
                bookingId = bookingRs.getInt("new_id");
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date checkin = df.parse(checkinDate);
            Date checkout = new Date(checkin.getTime() + (24 * 60 * 60 * 1000));

            double totalAmount = 0;
            ResultSet priceRs = c.statement.executeQuery(
                    "SELECT price FROM room WHERE room_number=" + roomNumber
            );
            if (priceRs.next()) {
                totalAmount = priceRs.getDouble("price");
            }

            String insertBooking =
                    "INSERT INTO bookings (booking_id, customer_id, room_number, status, total_amount, checkin_date, checkout_date, booking_date) " +
                            "VALUES (?, ?, ?, 'Active', ?, ?, ?, ?)";

            PreparedStatement bookingStmt = c.connection.prepareStatement(insertBooking);
            bookingStmt.setInt(1, bookingId);
            bookingStmt.setInt(2, customerId);
            bookingStmt.setInt(3, roomNumber);
            bookingStmt.setDouble(4, totalAmount);
            bookingStmt.setString(5, checkinDate);
            bookingStmt.setString(6, df.format(checkout));
            bookingStmt.setString(7, checkinDate);

            bookingStmt.executeUpdate();
        }
    }
}
