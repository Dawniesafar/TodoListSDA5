package com.company;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Controller ctr = new Controller();
        boolean input = false;
        Scanner scnr = new Scanner(System.in);

        ctr.loadObject();

        do {
            switch (scnr.nextInt()) {
                case 1:
                    Task t = new Task();
                    System.out.println("Enter task title: ");
                    t.setTitle(scnr.next());
                    System.out.println("Enter a project: ");
                    t.setProject(scnr.next());
                    System.out.println("Enter due date of format: dd-MM-yyyy");
                    LocalDate date = null;
                    do {
                         date = ctr.isValidDate(scnr.next());
                    }while(date == null);
                    t.setDueDate(date);
                    ctr.addTask(t);
                    input = false;
                    break;

                case 2:
                    ctr.printTasks(ctr.tasks);
                    System.out.println("Choose Task ID to edit");
                    ctr.editTask(scnr.nextInt());
                    input = false;
                    break;

                case 3:
                    ctr.printTasks(ctr.tasks);
                    Task removeTask = null;
                    do {
                        System.out.print("Choose Task ID to remove" + "\n");
                        removeTask = ctr.getTaskById(scnr.nextInt());
                    }while(removeTask == null);
                    ctr.removeTask(removeTask.getTaskId());
                    input = false;
                    break;

                case 4:
                    ctr.printTasks(ctr.tasks);
                    Task task = null;
                    do {
                        System.out.println("Choose valid task ID to mark as DONE!");
                        task = ctr.getTaskById(scnr.nextInt());
                    } while(task == null);
                    int taskid = task.getTaskId();
                    ctr.tasks.stream().filter(x->x.getTaskId() == taskid).findFirst().get().markAsDone();
                    input = false;
                    break;

                case 5:
                    System.out.println("Enter Project title: ");
                    ctr.sortByProject(scnr.next());
                    input = false;
                    break;

                case 6:
                    ctr.sortByDate();
                    input = false;
                    break;

                case 7:
                    ctr.getDoneTasks();
                    ctr.printTasks(ctr.doneTasks);
                    input = false;
                    break;

                case 8:
                    ctr.saveObject();
                    input = true;
                    break;

                default:
                    ctr.saveObject();
                    System.out.println("Please enter a number from 1 to 8");
                    input = false;

            }
        }while (!input && scnr.hasNextInt());
    }
}