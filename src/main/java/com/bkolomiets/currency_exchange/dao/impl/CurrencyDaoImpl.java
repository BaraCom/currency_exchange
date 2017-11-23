package com.bkolomiets.currency_exchange.dao.impl;

import com.bkolomiets.currency_exchange.dao.interfaces.CurrencyDao;
import com.bkolomiets.currency_exchange.entity.Order;
import com.bkolomiets.currency_exchange.entity.enums.TableName;
import java.sql.*;
import static com.bkolomiets.currency_exchange.util.ConnectionFactory.closeConnection;
import static com.bkolomiets.currency_exchange.util.ConnectionFactory.closeStatement;
import static com.bkolomiets.currency_exchange.util.ConnectionFactory.getConnection;

public class CurrencyDaoImpl implements CurrencyDao {

    @Override
    public void getByName(String name, int choiceMenu) {
        Connection connection = getConnection();
        Statement stmt = null;
        ResultSet resultSet = null;
        TableName[] tableNames = TableName.values();

        try {
            stmt = connection.createStatement();
            if (choiceMenu == 1 || choiceMenu == 3) {
                resultSet = stmt.executeQuery("SELECT * FROM "
                        + tableNames[choiceMenu - 1].toString()
                        + " WHERE source=\""
                        + name
                        + "\"");
            } else if (choiceMenu == 2) {
                resultSet = stmt.executeQuery("SELECT * FROM "
                        + tableNames[choiceMenu - 1].toString()
                        + " WHERE destination=\""
                        + name
                        + "\"");
            }

            while (resultSet.next()) {
                String destination = resultSet.getString("destination");
                String amount = resultSet.getString("amount");
                String rate = resultSet.getString("rate");
                String sumConversion = resultSet.getString("sum_conversion");
                String date = resultSet.getString("date");

                System.out.println("- Destination currency" + " | \t" + destination);
                System.out.println("- Amount" + "               | \t" + amount);
                System.out.println("- Rate" + "                 | \t" + rate);
                System.out.println("- Amount of conversion" + " | \t" + sumConversion);
                System.out.println("- Date" + "                 | \t" + date + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }

    @Override
    public void save(Order order, String tableName) {
        Connection connection = getConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement(
                    "INSERT INTO "
                            + tableName
                            + " (uuid, source, destination, amount, rate, sum_conversion, date) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, order.getUuid().toString());
            pstmt.setString(2, order.getSourceCurrency().toString());
            pstmt.setString(3, order.getDestinationCurrency().toString());
            pstmt.setBigDecimal(4, order.getAmount());
            pstmt.setBigDecimal(5, order.getRate());
            pstmt.setBigDecimal(6, order.getSumConversion());
            pstmt.setString(7, order.getDate());

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(pstmt);
            closeConnection(connection);
        }
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(String tableName, String id) {
        Connection connection = getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.execute("DELETE FROM "
                    + tableName
                    + " WHERE uuid=\'"
                    + id
                    + "\'");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }
}