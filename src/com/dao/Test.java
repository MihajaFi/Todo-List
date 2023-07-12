package com.dao;
import  java.sql.* ;
public class Test {
    public static void main(String[] args) {
        int id = 3;
        String title = "Manger";
        String description = "Manger ";
        Timestamp deadline = Timestamp.valueOf("2023-07-13 10:00:00");
        int priority = 5;
        boolean done = false;

        // Appel de la méthode à tester
       // toDoCRUD.insertToDo(id, title, description, deadline, priority, done);
      //  System.out.println(toDoCRUD.findTodoById(1));
     System.out.println(toDoCRUD.findAllToDo());
       // toDoCRUD.updateTodo(id , title , description , deadline , priority , done);
    }

}
