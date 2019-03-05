package com.company;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Controller {

    private static int id = 0;
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
    public void updateTask(int id)
    {
        Task task = getTaskById(id);
        System.out.println("Edit task title: ");
        task.setTitle(scnr.next());
        System.out.println("Edit task project: ");
        task.setProject(scnr.next());
        System.out.println("Enter due date of format: dd-MM-yyyy");
        LocalDate date = null;
        do {
            date = isValidDate(scnr.next());
        }while(date == null);
        task.setDueDate(date);
        task.setDueDate(date);
    }
    //removing a task from task list
    public void removeTask(int id) {
        for (Task t : tasks) {
            if (t.getTaskId() == id) {
                System.out.println("Task " + t.getTitle() + "is deleted!");
                tasks.remove(t);
            } else {
                System.out.println("Enter a valid ID...");
            }
        }
    }

    //retrieve task by its ID
    //Not user method, it may come handy later
    public Task getTaskById(int id) {
        Task task = new Task();
        for (Task t : tasks) {
            if (t.getTaskId() == id)
                task = t;
            else {
                System.out.println("Enter a valid ID...");
            }
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

    //load method is the first method to be called when the project starts to load previous saved tasks
//    public void load() {
//        System.out.println(
//                "(1) to add new task"
//                        + "\n" + "(2) to edit a task"
//                        + "\n" + "(3) show task list by project or due date"
//                        + "\n" + "(4) to mark a task as DONE!"
//                        + "\n" + "(5) to save and quit");
//
//        File fileName = new File("/Users/dawnie/IdeaProjects/TodoListV3.0/TodoList.txt");
//
//        if (!fileName.exists()) {
//            try {
//                FileWriter fw = new FileWriter("TodoList.txt");
//                BufferedWriter bw = new BufferedWriter(fw);
//                bw.flush();
//                bw.close();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("File was not saved..");
//            } finally {
//            }
//        } else {
//            String stringListOfTasks = "";
//            Task task;
//            try {
//                BufferedReader br = new BufferedReader(new FileReader(fileName));
//                String line;
//                while ((line = br.readLine()) != null) {
//                    stringListOfTasks += (line);
//                }
//                br.close();
//
//                String[] charArrayOfTasks = stringListOfTasks.split(",");
//                id = (Integer.parseInt(charArrayOfTasks[charArrayOfTasks.length - 5]));
//
//                for (int i = 0; i < charArrayOfTasks.length; ) {
//                    task = new Task();
//                    for (int j = 0; j < 5; j++) {
//                        task.setId(Integer.parseInt(charArrayOfTasks[i]));
//                        i++;
//                        j++;
//                        task.setTitle(charArrayOfTasks[i]);
//                        i++;
//                        j++;
//                        task.setProject(charArrayOfTasks[i]);
//                        i++;
//                        j++;
//                        task.setDueDate(charArrayOfTasks[i]);
//                        i++;
//                        j++;
//                        task.setDone(Boolean.parseBoolean(charArrayOfTasks[i]));
//                        i++;
//                        j++;
//                        tasks.add(task);
//                    }
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("File is not reachable..");
//            } finally {
//                printTasks();
//            }
//        }
//    }


//    public void save() {
//        File fileName = new File("/Users/dawnie/IdeaProjects/TodoListV2.0/TodoList.txt");
//
//        try {
//            FileWriter fw = new FileWriter("TodoList.txt");
//            BufferedWriter bw = new BufferedWriter(fw);
//            for (Task task : tasks) {
//                bw.write(task.getTaskId() + "," + task.getTitle() + "," + task.getProject() + ","
//                        + task.getDueDate() + "," + task.getDone() + "," + "\n");
//            }
//            bw.flush();
//            bw.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("File was not saved..");
//        } finally {
//        }
//    }

    //save the tasks and exit the app
    public void saveObject() {
        try {
            FileOutputStream fos = new FileOutputStream("/Users/dawnie/IdeaProjects/TodoListV3.0/TodoListV2.bin");
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
    public void loadObject()
    {
   System.out.println(
           "(1) to add new task"
           + "\n" + "(2) to edit a task"
           + "\n" + "(3) to remove task"
           + "\n" + "(4) to mark task as DONE!"
           + "\n" + "(5) show task list by project"
           + "\n" + "(6) show task list by due date"
           + "\n" + "(7) to save and quit");

    File fileName = new File("/Users/dawnie/IdeaProjects/TodoListV3.0/TodoListV2.bin");
        FileInputStream fos;
        ObjectInputStream os;
        if (!fileName.exists()) {
        try {
            fos = new FileInputStream("TodoListV2.bin");
            os = new ObjectInputStream(fos);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File was not saved..");
        }
    }
        else {
        try {
            fos = new FileInputStream("TodoListV2.bin");
            os = new ObjectInputStream(fos);
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

        public void printTasks()
        {
            System.out.println("ID" + "\t" + "Title" + "\t" + "Project" + "\t" + "Due Date" + "\t" + "is Done");
            tasks.stream().forEach(x-> System.out.println(x.getTaskId() + "\t" + x.getTitle() +
                    "\t" + x.getProject() + "\t" + x.getDueDate() +
                    "\t" + x.getDone()));
        }
}
