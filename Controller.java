package com.company;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private static int id = 0;
    ArrayList<Task> tasks;
    Scanner scnr = new Scanner(System.in);

    public Controller() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    public void addTask (Task task)
    {
        task.setId(IdGenerator());
        tasks.add(task);
        saveTask(task);
    }

    public Task getTaskById(int id)
    {
        Task task = new Task();
        for(Task t : tasks)
        {
            if(t.getTaskId() == id)
                task =  t;
            else
            {
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
    public void sortByDate()
    {
        System.out.println("ID" + "\t" + "Title" + "\t" + "Project" + "\t" + "Due Date" + "\t" + "is Done");
         tasks.stream().sorted(Comparator.comparing(Task::getDueDate)).forEach(x->
                System.out.println(x.getTaskId() + "\t" + x.getTitle() + "\t" +
                        "\t" + x.getProject() + "\t" + "\t" + x.getDueDate() +
                        "\t" + x.getCheck()));
    }
    public void sortByProject(String project)
    {
        System.out.println("ID" + "\t" + "Title" + "\t" + "Project" + "\t" + "Due Date" + "\t" + "is Done");
        tasks.stream().filter(x->x.getProject().equals(project)).forEach(x->
                System.out.println(x.getTaskId() + "\t" + x.getTitle() + "\t" +
                        "\t" + x.getProject() + "\t" + "\t" + x.getDueDate() +
                        "\t" + x.getCheck()));
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

    public void load() {
        System.out.println("! What are your plans for today!"
                + "\n" + "(1) to add new task"
                + "\n" + "(2) to edit a task"
                + "\n" + "(3) show task list by date or project"
                + "\n" + "(4) to save and quit task"
                + "\n" + "(0) to Quit");

        File fileName = new File("/Users/dawnie/IdeaProjects/TodoListV2.0/TodoList.txt");

        if (!fileName.exists()) {
            try {
                FileWriter fw = new FileWriter("TodoList.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                //bw.write((char[]) null);
                bw.flush();
                bw.close();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("File was not saved..");
            } finally {
            }
        }
        else {
            String stringListOfTasks = "";
            //ArrayList<String> charArrayOfTasks;
            Task task = new Task();
            String splitedTask = "";
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String line;
                while((line = br.readLine()) != null)
                {
                    stringListOfTasks += (line);
                }
                br.close();

                //for(String s : stringListOfTasks)
                    String [] charArrayOfTasks = stringListOfTasks.split(",");
                for(int i = 0; i < charArrayOfTasks.length;) {
                    splitedTask += charArrayOfTasks.get(i);
                        task.setId(Integer.parseInt(splitedTask));
                        i++;
                        task.setTitle(charArrayOfTasks(i));
                        i++;
                        task.setProject(splitedTask);
                        i++;
                        task.setDueDate(splitedTask);
                        i++;
                        task.setCheck(Boolean.parseBoolean(splitedTask));
                        i++;
                        tasks.add(task);
                    }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("File is not reachable..");
            } finally {

                for(Task task1 : tasks)
                    System.out.print(task1.getTitle() + " ");
            }
        }
    }
    public void saveTask(Task task)
    {
        File fileName = new File("/Users/dawnie/IdeaProjects/TodoListV2.0/TodoList.txt");

        try{
                FileWriter fw = new FileWriter("TodoList.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(task.getTaskId() + "," + task.getTitle() + "," + task.getProject() + ","
                        + task.getDueDate() + "," + task.getCheck() + "\n");

                bw.flush();
                bw.close();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("File was not saved..");
            } finally {
            }
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
