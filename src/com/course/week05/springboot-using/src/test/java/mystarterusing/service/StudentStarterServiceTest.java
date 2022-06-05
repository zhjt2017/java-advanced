package mystarterusing.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests for StudentStarterService
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-05 12:36:52
 */
@SpringBootTest
public class StudentStarterServiceTest {

    private StudentStarterService service;

    @Autowired
    public void setService(StudentStarterService service) {
        this.service = service;
    }

    @Test
    public void doAction() {
        service.doAction();
    }
}
