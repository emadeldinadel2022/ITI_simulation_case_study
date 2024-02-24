package iti.data_access_layer;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DAO {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "iti_user";
    private static final String PASSWORD = "1234";
    private Connection connection;
    private static DAO doaSingleton;

    private DAO(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static synchronized DAO initiaDAO(){
        if(doaSingleton == null){
            doaSingleton = new DAO();
            System.out.println("Database Connection Open");
        }
        return doaSingleton;
    }

    public Connection getConnection(){
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            // Handle closure error
            e.printStackTrace();
        }
    }


}
