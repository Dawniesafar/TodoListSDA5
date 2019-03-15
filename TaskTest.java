import com.company.Task;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

 Task testTask = new Task()
 {
     {this.setId(0);}
     {this.setTitle("testTask");}
     {this.setProject("testProject");}
     {this.setDueDate(LocalDate.now());}
     {this.markAsDone();}
 };

    @Test
    void setTitle() {
    assertEquals("testTask", testTask.getTitle());
    }

    @Test
    void setDueDate() {
        assertEquals(LocalDate.now(), testTask.getDueDate());
    }

    @Test
    void setId() {
        assertEquals(0, testTask.getTaskId());
    }

    @Test
    void setProject() {
        assertEquals("testProject", testTask.getProject());
    }

    @Test
    void getTaskId() {
        assertEquals(0, testTask.getTaskId());
    }

    @Test
    void getTitle() {
        assertEquals("testTask", testTask.getTitle());
    }

    @Test
    void getDueDate() {
        assertEquals(LocalDate.now(), testTask.getDueDate());
    }

    @Test
    void getProject() {
        assertEquals("testProject", testTask.getProject());
    }

    @Test
    void getDone() {

        assertEquals(true, testTask.getDone());
    }

    @Test
    void markAsDone() {
        assertEquals(true, testTask.getDone());
    }
}