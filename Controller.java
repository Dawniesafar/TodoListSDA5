package com.company;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Controller class is the responsible for all operations on tasks and projects
 */
public class Controller {

    private ArrayList<Task> tasks;
    private Scanner scnr = new Scanner(System.in);

    public Controller() {
        tasks = new ArrayList<>();
    }

    /**
     * add a new task to task list
     * @param task task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
        updateTaskIds();
    }

    /**
     * edit an existing task in task list
     * @param id specifies id of task to edit
     */
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
                    System.out.println("Task as been edited!");
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

    /**
     * remove an existing task from task list
     * @param id specifies id of task to remove
     */
    public void removeTask(int id) {
        Iterator it = tasks.iterator();
        while(it.hasNext())
        {
            Task task = (Task) it.next();
            if(task.getTaskId() == id){
                it.remove();
                System.out.println("Task has been removed successfully");
            break;
            }
        }
        updateTaskIds();
    }

    /**
     * retrieve task by its ID
     * @param id specifies id of task to be returned
     * @return task from task list as per its related id
     */
    public Task getTaskById(int id, ArrayList<Task> getTaskList) {
        Task task = new Task();
        for (Task t : getTaskList) {
            if (t.getTaskId() == id) {
                task = t;
                break;
            }
            else task = null;
        }
        return task;
    }

    /**
     * update taskId in arrayList of tasks to avoid duplications since taskId is
     * the index of the object in the arrayList
     */
    public void updateTaskIds() {
        for(int i = 0; i < tasks.size(); i++)
            tasks.get(i).setId(i);
    }

    /**
     * A method to sort tasks according to the due date associated with.
     */
    public ArrayList<Task> sortByDate(ArrayList<Task> taskList){
            return (ArrayList<Task>) taskList.stream().sorted(Comparator.comparing(Task::getDueDate))
                    .collect(Collectors.toList());
    }

    /**
     * a method to sort tasks as per the project related to
     * @param project specifies the project title takes String value
     */
    public ArrayList<Task> sortByProject(String project, ArrayList<Task> taskList){
           return  (ArrayList<Task>) taskList.stream().filter(x -> x.getProject().equals(project))
                    .collect(Collectors.toList());
    }

    /**
     * save the tasks to file and exit the app
     */
    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream("TodoList.bin");
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

    /**
     * load method creates a file of tasks if does not exist and loads previous saved tasks
     * or just loads tasks if file exists
     */
    public void load() {

    File fileName = new File("TodoList.bin");

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
            setDoneTasks(tasks);
            os.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("File not found...");
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("File is not reachable..");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("File is not reachable..");
        }
    }
        if(tasks.size() <= 0)
            System.out.println("\nThere are no tasks to be shown. Choose '1' to start adding your daily tasks");

        System.out.println(
                "You have " + numOfPendingTasks(getTasks()) + " tasks to do and " + setDoneTasks(getTasks()).size() + " tasks are done!"
                        + "\n" + "(1) to add new task"
                        + "\n" + "(2) to edit a task"
                        + "\n" + "(3) to remove a task"
                        + "\n" + "(4) to mark task as DONE!"
                        + "\n" + "(5) show task list by project"
                        + "\n" + "(6) show task list by due date"
                        + "\n" + "(7) show DONE! tasks list"
                        + "\n" + "(8) to save and quit");
}

    /**
     * validate and parses String to LocalDate
     * @param date the String to be parsed into LocalDate
     * @return a LocalDate
     */
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

    /**
     *
     * @param tasksToPrint list of tasks to be printed
     */
    public void printTasks(ArrayList<Task> tasksToPrint) {
        if(tasksToPrint.size() > 0) {
            System.out.println("ID" + "\t" + "   " + "Title" + "\t" +  "  " + "Project" + "\t" + "  " + "Due Date" + "\t" + "    " +  "is Done!");

            tasksToPrint.stream().forEach( x-> System.out.printf("%s %10s %10s %15s %10s\n", x.getTaskId(), x.getTitle(),
                    x.getProject(), x.getDueDate(), x.getDone()));
        }
        else System.out.println("There are no tasks to be shown");
        }

    /**
     * user option-input restricted to numbers
     * @return integer
     */
    public int scanIsNum() {
        int input = 0;
        try {
            input = Integer.parseInt(scnr.next());
        } catch (Exception e){
        }
        return input;
    }

    /**
     * retrieves tasks marked as DONE!
     */
    public ArrayList<Task> setDoneTasks(ArrayList<Task> doneTasks ) {
        doneTasks = (ArrayList <Task>) tasks.stream().filter(x -> x.getDone() == true).collect(Collectors.toList());
        return doneTasks;
    }

    /**
     * @return list of tasks
     */
    public ArrayList<Task> getTasks()
    {
        return this.tasks;
    }

    /**
     * @param myTasks specifies the List of tasks user wants to check
     * @return number of undone tasks
     */
    public int numOfPendingTasks(ArrayList<Task> myTasks) {
        return (int) myTasks.stream().filter(x-> x.getDone() == false).count();
    }
}