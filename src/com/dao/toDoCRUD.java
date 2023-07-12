package com.dao;
import java.sql.* ;
import java.util.List ;
import java.util.ArrayList ;
public class toDoCRUD {


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

    // insert TODO in database
    public static void insertToDo(int id, String title, String description, Timestamp deadline , int priority , boolean done){
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
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // find TODO

    public static toDo findTodoById(int id){
        toDo todo = null;
        try {
            String sql = "SELECT * FROM todo WHERE id = ?";
            PreparedStatement ps = getPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Récupérer les valeurs de la ligne trouvée et créer un objet ToDo
                int todoId = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Timestamp deadline = rs.getTimestamp("deadline");
                int priority = rs.getInt("priority");
                boolean done = rs.getBoolean("done");

                todo = new toDo(todoId, title, description, deadline, priority, done);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;



    }

    // find all TODO list in database

    public static List<toDo> findAllToDo (){

        List<toDo> todoList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM todo";
            PreparedStatement ps = getPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Timestamp deadline = rs.getTimestamp("deadline");
                int priority = rs.getInt("priority");
                boolean done = rs.getBoolean("done");

                toDo todo = new toDo(id, title, description, deadline, priority, done);
                todoList.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todoList;
    }

    //update a TODO list in database
    public static void updateToDo (int id, String newTitle, String newDescription, Timestamp newDeadline, int newPriority, boolean newDone){
        try {
            String sql = "UPDATE todo SET title = ?, description = ?, deadline = ?, priority = ?, done = ? WHERE id = ?";
            PreparedStatement ps = getPreparedStatement(sql);
            ps.setString(1, newTitle);
            ps.setString(2, newDescription);
            ps.setTimestamp(3, newDeadline);
            ps.setInt(4, newPriority);
            ps.setBoolean(5, newDone);
            ps.setInt(6, id);

            if (ps.executeUpdate() > 0) {
                System.out.println("TODO updated successfully");
            } else {
                System.out.println("Update not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //delete a TODO list in database
    public static void deleteToDo(int id ){
        try {
            String sql = "DELETE FROM todo WHERE id = ?";
            PreparedStatement ps = getPreparedStatement(sql);
            ps.setInt(1, id);
            System.out.println("TODO deleted ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
