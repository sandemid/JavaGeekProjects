package ru.sandem.java4.lesson3.server;

import java.sql.*;

public class SQLHandler {

    private static Connection connection;


    public static void connect(String dbName) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
    }

    public static String getNick(String login, String password) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "select nick from \"users_table\" where login = \"" + login
                        + "\" and password = \"" + password + "\"");
        String nick = null;
        while (rs.next()) {
            nick = rs.getString("nick");
        }
        return nick;
    }

    public static boolean changeNick(String nick, String newNick) throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(
                "select * from \"users_table\" where nick = \"" + newNick + "\"");
        while (rs.next()) {
            return false;
        }

        int u = statement.executeUpdate(
                "update \"users_table\" set nick = \"" + newNick
                        + "\" where nick = \"" + nick + "\"");
        if (u == 1) {
            return true;
        } else {
            return false;
        }
    }

}
