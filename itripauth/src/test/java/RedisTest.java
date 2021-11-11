import com.itrip.common.RedisAPI;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * @author : Mr.ShenJinChao
 * @date : 2021/11/9 10:52
 */
public class RedisTest {
    @Test
    public void controllerRedis(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisAPI redisAPI = context.getBean("redisAPI", RedisAPI.class);
        Scanner input = new Scanner(System.in);
        System.out.println("请输入一个缓存对象：");
        redisAPI.set("userName","liujiahaoshishabi");
        System.out.println("缓存结果"+redisAPI.get("userName"));
        System.out.println("过期时间"+redisAPI.ttl("userName"));
    }
}
