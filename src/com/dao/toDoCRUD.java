package com.dao;
import java.sql.* ;
public class toDoCRUD {
    String sql = "INSERT INTO todo"
            + "  (id, title, description, deadline, priority, done) VALUES " + " (?, ?, ?, ?, ? ,?);";

    private static  Connection connection ;
    private static PreparedStatement ps ;

    private static Connection getConnection() {
        if (connection == null) {
            // Code pour créer la connexion à la base de données
            ConnectionToDataBase db = new ConnectionToDataBase();
            connection = db.createConnection();
        }
        return connection;
    }

    private static PreparedStatement getPreparedStatement(String sql) {
        if (ps == null) {
            try {
                Connection connection = getConnection();
                ps = connection.prepareStatement(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ps;
    }


    public static void inserttoDo(int id, String title, String description, Timestamp deadline , int priority , boolean done){
        try {
            String sql = "INSERT INTO todo (id, title, description, deadline, priority, done) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = getPreparedStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setTimestamp(4, deadline);
            ps.setInt(5, priority);
            ps.setBoolean(6, done);
            ps.executeUpdate();

            System.out.println("Tâche insérée avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // find TODO

}
