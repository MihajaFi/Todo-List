package com.dao;
import java.sql.* ;
import java.util.List ;
import java.util.ArrayList ;
public class toDoCRUD {


    private static  Connection connection ;


    private static Connection getConnection() {
        //singleton de Connection
        if (connection == null) {

            ConnectionToDataBase db = new ConnectionToDataBase();
            connection = db.createConnection();
        }
        return connection;
    }



    // insert TODO in database
    public static void insertToDo(int id, String title, String description, Timestamp deadline, int priority, boolean done) {
        try {
            String sql = "INSERT INTO todo (id, title, description, deadline, priority, done) VALUES (?, ?, ?, ?, ?, ?)";
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
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

    public static toDo findTodoById(int id) {
        toDo todo = null;
        try {
            String sql = "SELECT * FROM todo WHERE id = ?";
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
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

    public static List<toDo> findAllToDo() {
        List<toDo> todoList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM todo";
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
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
    public static void updateToDo(int id, String newTitle, String newDescription, Timestamp newDeadline, int newPriority, boolean newDone) {
        try {
            String sql = "UPDATE todo SET title = ?, description = ?, deadline = ?, priority = ?, done = ? WHERE id = ?";
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newTitle);
            ps.setString(2, newDescription);
            ps.setObject(3, newDeadline); //pour que deadline peut etre null
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
    public static void deleteToDo(int id) {
        try {
            String sql = "DELETE FROM todo WHERE id = ?";
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("TODO deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }}
