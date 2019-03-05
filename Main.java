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
                    ctr.printTasks();
                    System.out.println("Choose Task ID to edit" + "\n");
                    ctr.updateTask(scnr.nextInt());
                    input = false;
                    break;

                case 3:
                    System.out.println("Choose Task ID to remove" + "\n");
                    ctr.removeTask(scnr.nextInt());
                    input = false;
                    break;

                case 4:
                    ctr.printTasks();
                    System.out.println("Choose task ID to mark as DONE!");
                    ctr.tasks.stream().filter(x->x.getTaskId() == scnr.nextInt()).findFirst().get().markAsDone();
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
                    ctr.saveObject();
                    input = true;
                    break;

                default:
                    ctr.saveObject();
                    input = true;
                    break;
            }
        }while (!input || scnr.hasNextInt());
    }
}
