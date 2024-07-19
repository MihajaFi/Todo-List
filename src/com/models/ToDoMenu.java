package com.models;

import com.dao.toDo;
import com.dao.toDoCRUD;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class ToDoMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayMenu();
    }

    private static void displayMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("----- TODO Menu -----");
            System.out.println("1. Add a TODO");
            System.out.println("2. Find a TODO");
            System.out.println("3. Find all TODO");
            System.out.println("4. Update TODO");
            System.out.println("5. Delete TODO");
            System.out.println("6. Quit");

            System.out.print("Your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    searchTaskById();
                    break;
                case 3:
                    displayAllTasks();
                    break;
                case 4:
                    updateTask();
                    break;
                case 5:
                    deleteTask();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you to use the TODO task ");
                    break;
                default:
                    System.out.println("Retry choice invalid");
            }

            System.out.println(); //Saute une ligne
        }



    }

    private static void addTask() {
        System.out.println("----- Add a TODO -----");
        System.out.print("ID : ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Title : ");
        String title = scanner.nextLine();

        System.out.print("Description : ");
        String description = scanner.nextLine();

        System.out.print("Deadline (yyyy-mm-dd hh:mm:ss) : ");
        String deadlineStr = scanner.nextLine();
        Timestamp deadline = Timestamp.valueOf(deadlineStr);

        System.out.print("Priority : ");
        int priority = Integer.parseInt(scanner.nextLine());

        System.out.print("Done (true or false) : ");
        boolean done = Boolean.parseBoolean(scanner.nextLine());

        toDoCRUD.insertToDo(id, title, description, deadline, priority, done);
    }

    private static void searchTaskById() {
        System.out.println("----- Show a TODO -----");

        System.out.print("ID TODO : ");
        int id = Integer.parseInt(scanner.nextLine());

        toDo todo = toDoCRUD.findTodoById(id);

        if (todo != null) {
            System.out.println("TODO find successfully");
            System.out.println(todo);
        } else {
            System.out.println("Nothing TODO for this ID");
        }
    }

    private static void displayAllTasks() {
        System.out.println("----- Show all TODO -----");

        List<toDo> todoList = toDoCRUD.findAllToDo();

        if (!todoList.isEmpty()) {
            System.out.println("List of the TODO :");
            for (toDo todo : todoList) {
                System.out.println(todo);
            }
        } else {
            System.out.println("Nothing TODO");
        }
    }

    private static void updateTask() {
        System.out.println("----- Update a TODO -----");

        System.out.print("ID of TODO: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("New title: ");
        String newTitle = scanner.nextLine();

        System.out.print("New description: ");
        String newDescription = scanner.nextLine();

        System.out.print("Update deadline (y/n)? ");
        String updateDeadlineChoice = scanner.nextLine();
        Timestamp newDeadline = null;
        if (updateDeadlineChoice.equalsIgnoreCase("y")) {
            System.out.print("New deadline (yyyy-mm-dd hh:mm:ss): ");
            String stringDeadline = scanner.nextLine();
            newDeadline = Timestamp.valueOf(stringDeadline);
        }

        System.out.print("New priority: ");
        int newPriority = Integer.parseInt(scanner.nextLine());

        System.out.print("Done (true or false): ");
        boolean newDone = Boolean.parseBoolean(scanner.nextLine());

        toDoCRUD.updateToDo(id, newTitle, newDescription, newDeadline, newPriority, newDone);
    }

    private static void deleteTask() {
        System.out.println("----- Delete a TODO -----");


        System.out.print("ID of TODO : ");
        int id = Integer.parseInt(scanner.nextLine());

        toDoCRUD.deleteToDo(id);
    }
}
