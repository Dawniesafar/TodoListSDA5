package com.company;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {

    ArrayList<Task> tasks;
    ArrayList<Task> doneTasks;
    Scanner scnr = new Scanner(System.in);

    public Controller() {

        tasks = new ArrayList<>();
        doneTasks = new ArrayList<>();
    }

    //add a new task to task list
    public void addTask(Task task) {
        tasks.add(task);
        updateTaskIds();
    }

    //edit task in task list
    public void editTask(int id) {
        Task task = null;
        if(id < tasks.size()) {
            Iterator it = tasks.iterator();
            while (it.hasNext()) {
                task = (Task) it.next();
                if (task.getTaskId() == id) {
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
                    updateTaskIds();
                    break;
                }
            }
        }
        else if (id >= tasks.size())
        {
            System.out.println("Please enter a valid task ID!");
            editTask(scnr.nextInt());
        }
    }

    //removing a task from task list
    public void removeTask(int id) {
        Iterator it = tasks.iterator();
        while(it.hasNext())
        {
            Task task = (Task) it.next();
            if(task.getTaskId() == id){
                it.remove();
            break;
            }
        }
        updateTaskIds();
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

    //update taskId in arrayList of tasks to avoid duplications since taskId is
    //the index of the object in the arrayList
    public void updateTaskIds() {
        for(int i = 0; i < tasks.size(); i++)
            tasks.get(i).setId(i);
    }

    //shows done tasks
    public void getDoneTasks() {
        doneTasks = (ArrayList <Task>) tasks.stream().filter(x -> x.getDone() == true).collect(Collectors.toList());
    }

    //A method to sort tasks according to the due date associated with.
    public void sortByDate(){
            ArrayList<Task> sortedList = (ArrayList<Task>) tasks.stream().sorted(Comparator.comparing(Task::getDueDate))
                    .collect(Collectors.toList());
            printTasks(sortedList);
    }

    //A method to sort tasks as per the project related to.
    public void sortByProject(String project){
            ArrayList<Task> sortedList = (ArrayList<Task>) tasks.stream().filter(x -> x.getProject().equals(project))
                    .collect(Collectors.toList());
            printTasks(sortedList);
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

    File fileName = new File("/Users/dawnie/IdeaProjects/TodoListV3.0/TodoList.bin");

        if (!fileName.exists()) {
        try {
            fileName = new File("TodoList.bin");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File was not saved..");
        }
    }
        else if(fileName.exists()){
        try {
            FileInputStream fos = new FileInputStream("TodoList.bin");
            ObjectInputStream os = new ObjectInputStream(fos);
            tasks = (ArrayList<Task>) os.readObject();
            getDoneTasks();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File is not reachable..");
        }
    }
        if(tasks.size() <= 0)
            System.out.println("\nThere are no tasks to be shown. Choose '1' to start adding your daily tasks");

        System.out.println(
                "You have " + tasks.size() + " to do and " + this.doneTasks.size() + " are done!"
                        + "\n" + "(1) to add new task"
                        + "\n" + "(2) to edit a task"
                        + "\n" + "(3) to remove a task"
                        + "\n" + "(4) to mark task as DONE!"
                        + "\n" + "(5) show task list by project"
                        + "\n" + "(6) show task list by due date"
                        + "\n" + "(7) show DONE! task list"
                        + "\n" + "(8) or any unspecified key to save and quit");
}

    //validate and converts string to date
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

    //print list of tasks
    public void printTasks(ArrayList<Task> tasksToPrint) {
        if(tasksToPrint.size() > 0) {
            System.out.println("ID" + "\t" + "Title" + "\t" + "Project" + "\t" + "Due Date" + "\t" + "is Done");
            tasksToPrint.stream().forEach(x -> System.out.println(x.getTaskId() + "\t" + x.getTitle() +
                    "\t" + x.getProject() + "\t" + x.getDueDate() +
                    "\t" + x.getDone()));
        }
        else System.out.println("There are no tasks to be shown");
        }

    //user option-input restricted to numbers
    public int ScanIsNum() {
        int input = 0;
        try {
            input = Integer.parseInt(scnr.next());
        } catch (Exception e){
        }
        return input;
    }
}