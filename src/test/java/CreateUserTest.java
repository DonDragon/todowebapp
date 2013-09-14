import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.od.hillel.todo.dao.TODODao;
import ua.od.hillel.todo.entities.User;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        {"classpath:applicationContext.xml",
         "file:src/main/webapp//WEB-INF/mvc-dispatcher-servlet.xml",
         "file:src/main/webapp//WEB-INF/spring-security.xml"
        })
public class CreateUserTest {

    @Autowired
    TODODao dao;

    @Test
    public void test() {
        User user = new User();
        user.setUsername("Test User");
        user.setAge(27);
        user.setEmail("test@mail.com");
        user.setPassword("123456");

        dao.create(user);

        assertNotNull(dao.findUserByName("Test User"));


    }

}
