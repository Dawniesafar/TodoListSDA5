package com.company;

public class Main {

    public static void main(String[] args) {

        Controller ctr = new Controller();
        boolean input = false;
        int userCase;

        ctr.load();

        do {

            switch (ctr.intScanner()) {
                case 1:
                    Task t = new Task();
                    System.out.println("Enter task title: ");
                    t.setTitle(ctr.stringScanner());
                    System.out.println("Enter a project: ");
                    t.setProject(ctr.stringScanner());
                    System.out.println("Set a valid due date: ");
                    t.setDueDate(ctr.stringScanner());
                    ctr.addTask(t);
                    input = false;
                    break;

                case 2:
                    System.out.print("Choose Task ID to edit..." + "\n" + "ID" + "\t" + "Title" + "\n");
                    ctr.tasks.stream().forEach(x ->
                            System.out.print(x.getTaskId() + "\t" + x.getTitle() + "\n"));
                    Task taskToEdit = ctr.getTaskById(ctr.intScanner());
                    System.out.println("Edit task title: ");
                    taskToEdit.setTitle(ctr.stringScanner());
                    System.out.println("Edit due date: ");
                    taskToEdit.setDueDate(ctr.stringScanner());
                    input = false;
                    break;

                case 3:
                    System.out.println("Select 1 to sort tasks by project and 2 by date");
                    userCase = ctr.intScanner();
                    if(userCase == 1)
                    {
                        System.out.println("Enter Project title: ");
                        ctr.sortByProject(ctr.stringScanner());
                    }
                    if(userCase == 2)
                        ctr.sortByDate();

                    input = false;
                    break;

                case 4:
                    input = false;

                case 0:
                    input = true;
                    break;

                default:
                    input = true;
            }
        }while (!input);
    }
}
