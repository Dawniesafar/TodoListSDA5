import com.company.Controller;
import com.company.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

class ControllerTest
{
    public Controller ctr = new Controller();
    ArrayList<Task> tasks = new ArrayList<>();
    Task task1 = new Task();
    Task task2 = new Task();

    @BeforeEach
    public void init() {

        task1 = new Task(0, "task1", "pro1", true, LocalDate.of(2019, 04,20));
        task2 = new Task(1, "task2", "pro1", false, LocalDate.of(2019, 04,21));

         tasks = new ArrayList<>() {
            {
                add(task1);
                add(task2);
            }
        };
    }

    ControllerTest() {
    }

    @Test
    void getTaskByIdTest() {
        Task result = ctr.getTaskById(0, tasks);
        assertEquals(task1, result);
    }

    @Test
    void sortByDate() {
        ArrayList<Task> result = ctr.sortByDate(tasks);
        ArrayList<Task> expected = (ArrayList<Task>) tasks.stream().sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
        assertEquals(expected, result);
    }

    @Test
    void sortByProjectTest() {
        ArrayList<Task> result = ctr.sortByProject("pro1", tasks);
        ArrayList<Task> expected = (ArrayList<Task>) tasks.stream().filter(x->x.getProject().equals("pro1"))
                .collect(Collectors.toList());

        assertEquals(expected,result);
    }

    @Test
    void isValidDateTest() {
        LocalDate expectedDate =  LocalDate.of(2019, 04,20);
        assertEquals(expectedDate, ctr.isValidDate("20-04-2019"));
    }

    @Test
    void numOfPendingTasksTest()
    {
        int result = ctr.numOfPendingTasks(this.tasks);
        assertEquals(1,result);
    }
}