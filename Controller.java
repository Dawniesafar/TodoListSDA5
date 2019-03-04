package com.company;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
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
        task.setId(IdGenerator());
        tasks.add(task);
    }

    //edit task in task list
    public void updateTask(int id) {
        for (Task t : tasks) {
            if (t.getTaskId() == id) {
                System.out.println("Edit task title: ");
                t.setTitle(stringScanner());
                System.out.println("Edit task project: ");
                t.setProject(stringScanner());
                String s  = stringScanner();
                do {
                    System.out.println("Edit due date format it as dd-MM-yyy: ");
                }while (!isValidDateV2(s));
                //isValidDateV2(stringScanner());
                t.setDueDate(s);
                System.out.println("to mark as done type true and for awaiting type false: ");

            } else {
                System.out.println("Enter a valid ID...");
            }
        }
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

    // A method to allow the user to differ tasks with the same title.
    public int IdGenerator() {
        id += 1;
        return id;
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

//    public void addProject(String title) {
//        project.setTitle(title);
//        projects.add(project);
//        try {
//            FileWriter fw = new FileWriter("MyProjects.txt", true);
//            BufferedWriter bw = new BufferedWriter(fw);
//            for (Project p : projects)
//                bw.write(project.getTitle() + "\n");
//            bw.flush();
//            bw.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("File was not saved..");
//        } finally {
//        }
//    }

//    public String getProject(String projectTitle) {
//        for (Task t : tasks) {
//            if (t.getProject() == projectTitle)
//                return t.getProject();
//            else {
//                System.out.println("Please enter a vaild project title...");
//                return null;
//            }
//        }
//    }

//    public void setProject(String p) {
//        this.project = this.getProject(p);
//    }
//
//    public void addTaskToProject(Task t) {
//        tasks.add(t);
//        project.getArrayListOfTasks().add(t);
//        t.setId(IdGenerator());
//    }

    public String stringScanner() {
        return scnr.next();
    }

    public int intScanner() {
        return scnr.nextInt();
    }

    //load method is the first method to be called when the project starts to load previous saved tasks
    public void load() {
        System.out.println(
                "(1) to add new task"
                        + "\n" + "(2) to edit a task"
                        + "\n" + "(3) show task list by project or due date"
                        + "\n" + "(4) to mark a task as DONE!"
                        + "\n" + "(5) to save and quit");

        File fileName = new File("/Users/dawnie/IdeaProjects/TodoListV3.0/TodoList.txt");

        if (!fileName.exists()) {
            try {
                FileWriter fw = new FileWriter("TodoList.txt");
                BufferedWriter bw = new BufferedWriter(fw);
                bw.flush();
                bw.close();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("File was not saved..");
            } finally {
            }
        } else {
            String stringListOfTasks = "";
            Task task;
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = br.readLine()) != null) {
                    stringListOfTasks += (line);
                }
                br.close();

                String[] charArrayOfTasks = stringListOfTasks.split(",");
                id = (Integer.parseInt(charArrayOfTasks[charArrayOfTasks.length - 5]));

                for (int i = 0; i < charArrayOfTasks.length; ) {
                    task = new Task();
                    for (int j = 0; j < 5; j++) {
                        task.setId(Integer.parseInt(charArrayOfTasks[i]));
                        i++;
                        j++;
                        task.setTitle(charArrayOfTasks[i]);
                        i++;
                        j++;
                        task.setProject(charArrayOfTasks[i]);
                        i++;
                        j++;
                        task.setDueDate(charArrayOfTasks[i]);
                        i++;
                        j++;
                        task.setDone(Boolean.parseBoolean(charArrayOfTasks[i]));
                        i++;
                        j++;
                        tasks.add(task);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("File is not reachable..");
            } finally {
                printTasks();
            }
        }
    }

    public void save() {
        File fileName = new File("/Users/dawnie/IdeaProjects/TodoListV2.0/TodoList.txt");

        try {
            FileWriter fw = new FileWriter("TodoList.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Task task : tasks) {
                bw.write(task.getTaskId() + "," + task.getTitle() + "," + task.getProject() + ","
                        + task.getDueDate() + "," + task.getDone() + "," + "\n");
            }
            bw.flush();
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File was not saved..");
        } finally {
        }
    }

    public LocalDate parseDate(String dateString) {
        boolean isValid = isValidDate(dateString);
        LocalDate localDate = LocalDate.now();
        if (isValid) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            localDate = LocalDate.parse(dateString, formatter);

            while(localDate.compareTo(LocalDate.now()) < 0)
            {
                System.out.println("Invalid date! don't set an old date...");
            }
        }
        return localDate;
    }
        public boolean isValidDate (final String dateString1)
        {
            String[] dateIntoArray = dateString1.split("-");
            int year = Integer.parseInt(dateIntoArray[0]);
            int month = dateIntoArray.length > 1 ? Integer.parseInt(dateIntoArray[1]) : 1;
            int day = dateIntoArray.length > 2 ? Integer.parseInt(dateIntoArray[2]) : 2;
            if (1 < day || day > 31 || 1 > month || month > 12) {
                System.out.println("Enter a valid day/month as of format: dd-MM-yyyy");
            }
            try {
                LocalDate.of(year, month, day);

                return true;
        } catch(DateTimeException e){
            return false;
        }
    }
    public boolean isValidDateV2(String dateString2)
    {
        LocalDate Date;
        String[] dateIntoArray = dateString2.split("-");
        int year = Integer.parseInt(dateIntoArray[0]);
        int month = dateIntoArray.length > 1 ? Integer.parseInt(dateIntoArray[1]) : 1;
        int day = dateIntoArray.length > 2 ? Integer.parseInt(dateIntoArray[2]) : 2;
//        int month = Integer.parseInt(dateIntoArray[1]);
//        int day = Integer.parseInt(dateIntoArray[2]);
        Date = LocalDate.of(year, month, day);

        if (Date.compareTo(LocalDate.now()) >= 0)
        {
            return true;
        }
        else return false;
    }
    public boolean isValidDateV3(String dateString3) {
        String[] dateIntoArray = dateString3.split("-");
//        int year = Integer.parseInt(dateIntoArray[2]);
//        int month = dateIntoArray.length > 1 ? Integer.parseInt(dateIntoArray[1]) : 1;
//        int day = dateIntoArray.length > 2 ? Integer.parseInt(dateIntoArray[0]) : 2;
        int month = Integer.parseInt(dateIntoArray[1]);
        int day = Integer.parseInt(dateIntoArray[0]);
        if(1 > day || day > 31 || 1 > month || month > 12)
        {
            return false;
        }
        DateTimeFormatter format =  DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateString3, format);
        if(date.compareTo(LocalDate.now()) < 0)
            return false;
        else return true;
    }

        public void printTasks()
        {
            System.out.println("ID" + "\t" + "Title" + "\t" + "Project" + "\t" + "Due Date" + "\t" + "is Done");
            tasks.stream().forEach(x-> System.out.println(x.getTaskId() + "\t" + x.getTitle() +
                    "\t" + x.getProject() + "\t" + x.getDueDate() +
                    "\t" + x.getDone()));
        }
//        else {
//            Task t;
//            String s;
//            List<String> listOfTasks = new ArrayList<>();
//            try {
//                BufferedReader br = new BufferedReader(new FileReader(fileName));
//                String line;
//                while((line = br.readLine()) != null)
//                {
//                    listOfTasks.add(line);
//                }
//                br.close();
//
//                for(int i = 0; i < listOfTasks.size(); i++)
//                {
//                    s = listOfTasks.get(i).split(", ");
//                    t.setTitle(s);
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("File is not reachable..");
//            } finally {
//            }
//        }
//    }
}
