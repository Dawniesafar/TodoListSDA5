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
                    //System.out.println("Set due date format it as dd-MM-yyy: ");
                    String s;
                    do {
                        System.out.println("Set a valid due date as the format dd-MM-yyy: ");
                        s = ctr.stringScanner();
                    }while (!(ctr.isValidDateV3(s)));
                    //isValidDateV2(stringScanner());
                    t.setDueDate(s);
                    ctr.addTask(t);
                    input = false;
                    break;

                case 2:
                    ctr.printTasks();
                    System.out.println("Choose 1 to edit or 2 to remove task: ");
                    userCase = ctr.intScanner();
                    if(userCase == 1) {
                        System.out.print("Choose Task ID to edit" + "\n");
                        ctr.updateTask(ctr.intScanner());
                    }
                    else if(userCase == 2){
                        System.out.print("Choose Task ID to remove" + "\n");
                        ctr.removeTask(ctr.intScanner());
                    }

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
                    ctr.printTasks();
                    System.out.println("Choose task ID to mark as DONE!");
                    ctr.tasks.stream().filter(x->x.getTaskId() == ctr.intScanner()).findFirst().get().markAsDone();
                    input = false;
                    break;

                case 5:
                    ctr.save();
                    input = true;
                    break;

                default:
                    input = true;
                    break;
            }
        }while (!input);
    }
}
