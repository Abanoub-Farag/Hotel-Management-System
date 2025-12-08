package Hotel_Management_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Z_Con {
    Connection connection;
    Statement statement;

    public Z_Con(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelMS", "root", "2006");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}