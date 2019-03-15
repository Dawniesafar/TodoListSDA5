package com.company;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Controller ctr = new Controller();
        boolean input = false;
        Scanner scnr = new Scanner(System.in);

        ctr.load();

            do {
                int choice = ctr.scanIsNum();

                switch (choice) {
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
                        } while (date == null);
                        t.setDueDate(date);
                        ctr.addTask(t);
                        input = false;
                        break;

                    case 2:
                        ctr.printTasks(ctr.getTasks());
                        System.out.println("Choose Task ID to edit");
                        ctr.editTask(scnr.nextInt());
                        input = false;
                        break;

                    case 3:
                        ctr.printTasks(ctr.getTasks());
                        Task removeTask = null;
                        do {
                            System.out.print("Choose Task ID to remove" + "\n");
                            removeTask = ctr.getTaskById(scnr.nextInt(),ctr.getTasks());
                        } while (removeTask == null);
                        ctr.removeTask(removeTask.getTaskId());
                        input = false;
                        break;

                    case 4:
                        ctr.printTasks(ctr.getTasks());
                        Task task = null;
                        do {
                            System.out.println("Choose valid task ID to mark as DONE!");
                            task = ctr.getTaskById(scnr.nextInt(),ctr.getTasks());
                        } while (task == null);
                        int taskid = task.getTaskId();
                        ctr.getTasks().stream().filter(x -> x.getTaskId() == taskid).findFirst().get().markAsDone();
                        input = false;
                        break;

                    case 5:
                        System.out.println("Enter Project title: ");
                        ctr.printTasks(ctr.sortByProject(scnr.next(), ctr.getTasks()));
                        input = false;
                        break;

                    case 6:
                        ctr.printTasks(ctr.sortByDate(ctr.getTasks()));
                        input = false;
                        break;

                    case 7:
                        ctr.printTasks((ctr.setDoneTasks(ctr.getTasks())));
                        input = false;
                        break;

                    case 8:
                        ctr.save();
                        input = true;
                        break;

                    default:
                        ctr.save();
                        System.out.println(
                                "You have " + ctr.numOfPendingTasks(ctr.getTasks()) + " tasks to do and " +
                                        ctr.setDoneTasks(ctr.getTasks()).size() + " tasks are done!"
                                        + "\n" + "(1) to add new task"
                                        + "\n" + "(2) to edit a task"
                                        + "\n" + "(3) to remove a task"
                                        + "\n" + "(4) to mark task as DONE!"
                                        + "\n" + "(5) show task list by project"
                                        + "\n" + "(6) show task list by due date"
                                        + "\n" + "(7) show DONE! tasks list"
                                        + "\n" + "(8) to save and quit");
                        input = false;

                }
            } while (!input);
    }
}