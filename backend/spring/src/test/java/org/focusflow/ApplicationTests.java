package org.focusflow;

import org.focusflow.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTests {
    Task task;

    @Test
    void contextLoads() {
    }

    @Test
    void createTask(){
       //...
       int id= task.getId();
       assertNotNull(id);

    }

}
