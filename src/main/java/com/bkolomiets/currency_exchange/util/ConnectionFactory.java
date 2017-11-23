package com.bkolomiets.currency_exchange.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import static java.lang.Class.forName;

public class ConnectionFactory {

    public static Connection getConnection() {
        Properties props = new Properties();
        FileInputStream fis;
        Connection connection = null;

        try {
            fis = new FileInputStream(
        "C://Users/Марина/IdeaProjects/currency_exchange/currency_exchange/src/main/resources/application.properties");
            props.load(fis);

            // load the Driver Class
            forName(props.getProperty("DB_DRIVER_CLASS"));

            // create the connection now
            connection = DriverManager.getConnection(
                    props.getProperty("DB_URL"),
                    props.getProperty("DB_USERNAME"),
                    props.getProperty("DB_PASSWORD")
            );
        } catch (IOException | ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.getErrorCode();
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.getErrorCode();
        }
    }
}

