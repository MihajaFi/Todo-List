package com.dao;
import java.sql.* ;
public class toDoCRUD {
    String sql = "INSERT INTO todo"
            + "  (id, title, description, deadline, priority, done) VALUES " + " (?, ?, ?, ?, ? ,?);";

    private static  Connection connection ;
    private static Statement st ;

    private static Connection getConnection() {
        if (connection == null) {
            // Code pour créer la connexion à la base de données
            ConnectionToDataBase db = new ConnectionToDataBase();
            connection = db.createConnection();
        }
        return connection;
    }

    private static Statement getStatement() {
        if (st == null) {
            try {
                Connection connection = getConnection();
                st = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return st;
    }
}
