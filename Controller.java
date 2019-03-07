package com.company;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Controller {

    ArrayList<Task> tasks;
    Scanner scnr = new Scanner(System.in);

    public Controller() {
        tasks = new ArrayList<>();
    }

    //add a new task to task list
    public void addTask(Task task) {
        //task.setId(IdGenerator());
        task.setId(tasks.size());
        tasks.add(task);
    }

    //edit task in task list
    public void editTask(int id) {
        Iterator it = tasks.iterator();
        while(it.hasNext()) {
            Task task = (Task) it.next();
            if (task.getTaskId() == id){
                task.setTitle(scnr.next());
            System.out.println("Edit task title: ");
            task.setTitle(scnr.next());
            System.out.println("Edit task project: ");
            task.setProject(scnr.next());
            System.out.println("Enter due date of format: dd-MM-yyyy");
            LocalDate date = null;
            do {
                date = isValidDate(scnr.next());
            } while (date == null);
            task.setDueDate(date);
        }
        }
    }
    //removing a task from task list
    public void removeTask(int id) {
        Iterator it = tasks.iterator();
        while(it.hasNext())
        {
            Task task = (Task) it.next();
            if(task.getTaskId() == id)
                it.remove();
        }
    }
    //retrieve task by its ID
    public Task getTaskById(int id) {
        Task task = new Task();
        for (Task t : tasks) {
            if (t.getTaskId() == id) {
                task = t;
                break;
            }
            else task = null;
        }
        return task;
    }

    //A method to sort tasks according to the due date associated with.
    public void sortByDate() {
        System.out.println("ID" + "\t" + "Title" + "\t" + "Project" + "\t" + "Due Date" + "\t" + "is Done");
        tasks.stream().sorted(Comparator.comparing(Task::getDueDate)).forEach(x ->
                System.out.println(x.getTaskId() + "\t" + x.getTitle() +
                        "\t" + x.getProject() + "\t" + x.getDueDate() + "\t" + x.getDone()));
    }

    //A method to sort tasks as per the project related to.
    public void sortByProject(String project) {
        System.out.println("ID" + "\t" + "Title" + "\t" + "Project" + "\t" + "Due Date" + "\t" + "is Done");
        tasks.stream().filter(x -> x.getProject().equals(project)).forEach(x ->
                System.out.println(x.getTaskId() + "\t" + x.getTitle() +
                        "\t" + x.getProject() + "\t" + x.getDueDate() + "\t" + x.getDone()));
    }

    //save the tasks and exit the app
    public void saveObject() {
        try {
            FileOutputStream fos = new FileOutputStream("/Users/dawnie/IdeaProjects/TodoListV3.0/TodoList.bin");
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(tasks);
            os.close();

        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("File was not saved...");
        }
        finally {

        }
    }
    //load method is the first method to be called when the project starts to load previous saved tasks
    public void loadObject() {
        System.out.println(
           "(1) to add new task"
           + "\n" + "(2) to edit a task"
           + "\n" + "(3) to remove a task"
           + "\n" + "(4) to mark task as DONE!"
           + "\n" + "(5) show task list by project"
           + "\n" + "(6) show task list by due date"
           + "\n" + "(7) or any unspecified key to save and quit");

    File fileName = new File("/Users/dawnie/IdeaProjects/TodoListV3.0/TodoList.bin");

        if (!fileName.exists()) {
        try {
            fileName = new File("TodoList.bin");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File was not saved..");
        }
    }
        else {
        try {
            FileInputStream fos = new FileInputStream("TodoList.bin");
            ObjectInputStream os = new ObjectInputStream(fos);
            tasks = (ArrayList<Task>) os.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File is not reachable..");
        }
    }
}

    public LocalDate isValidDate(String date) {
        LocalDate localDate = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            try {
                localDate = LocalDate.parse(date, format);
                if(localDate.isBefore(LocalDate.now()))
                {
                    System.out.println("Date before now is invalid");
                    localDate = null;
                }
            } catch (Exception e) {
               System.out.println("invalid date");
            }
        return localDate;
    }

    public void printTasks() {
            System.out.println("ID" + "\t" + "Title" + "\t" + "Project" + "\t" + "Due Date" + "\t" + "is Done");
            tasks.stream().forEach(x-> System.out.println(x.getTaskId() + "\t" + x.getTitle() +
                    "\t" + x.getProject() + "\t" + x.getDueDate() +
                    "\t" + x.getDone()));
        }
}
