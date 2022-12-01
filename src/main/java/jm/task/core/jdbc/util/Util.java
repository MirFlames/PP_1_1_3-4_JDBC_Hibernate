package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    public static Connection getMyConnection() throws SQLException,
            ClassNotFoundException {

        final String hostName = "localhost";
        final String dbName = "pre-project";
        final String userName = "root";
        final String password = "123456";

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        return DriverManager.getConnection(connectionURL, userName,
                password);
    }
}

