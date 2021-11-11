import com.itrip.auth.service.UserService;
import com.itrip.beans.pojo.ItripUser;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : Mr.ShenJinChao
 * @date : 2021/11/10 19:04
 */
public class UserServiceTest {
    @Test
    public void testCreateUser() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServices = context.getBean("userService", UserService.class);
        ItripUser itripUser = new ItripUser();
        itripUser.setUserName("小刘");
        itripUser.setUserCode("shenjinchao88@gmail.com");

        userServices.itriptxCreateUser(itripUser);
    }

    @Test
    public void testActivateUser() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServices = context.getBean("userService", UserService.class);
        System.out.println(userServices.activate("shenjinchao88@gmail.com","7a63ba50c5eefa5d5d4cd8e3c53911e4"));
    }
}
