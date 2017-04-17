
//import com.alibaba.fastjson.JSON;
import com.wang.model.Student;
import com.wang.model.User;
import com.wang.myinterface.StudentInterface;
import com.wang.service.StuService;
import org.junit.Test;
import com.wang.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by hppc on 2017/2/27.
 */
public class TestForService {
    private ApplicationContext applicationContext=null;
    UserService userService=null;
    StuService stuService=null;


    {
        applicationContext=new ClassPathXmlApplicationContext("spring-config.xml");
        userService=(UserService) applicationContext.getBean("userServiceImpl");
        stuService=(StuService) applicationContext.getBean("stuServiceImpl");

    }
    //    @org.junit.TestForService
//    public void test01(){
//        User user=userService.getUserById(1);
//        System.out.println(user);
//    }
    @Test
    public void test02(){
        User user = new User("2015060101005@qq.com","fdsa951");
        String result = userService.register(user);
        System.out.println(result);
    }
    @Test
    public void test03(){
        User user1 = new User("704","fff");
//        System.out.println(JSON.toJSONString(user1));
    }
//    @Test
//    public void test04(){
//        String res = userService.setPassword("fdsa951","fdsa95123","704321406@qq.com");
//        System.out.println(res);
//    }

    @Test
    public void test05(){
        Student student = new Student();
        student.setAcademy("计算机"); student.setStuId("2015060101789");student.setName("王阳456");student.setId(0);
        System.out.println(student);
        String res = stuService.addStudent(student);
        System.out.println(res);
    }
    @Test
    public void test06(){
        List<Student> students = stuService.getStuByAcademy("计算机");
        System.out.println(students);
//        System.out.println("");
    }
    @Test
    public void test07(){
        Student student = new Student();
        student.setId(2);student.setStuId("20111111");student.setName("测试");
        System.out.println(stuService.setStu(student));
    }
    @Test
    public void test08(){
        System.out.println(stuService.delStu(2));
    }

    @Test
    public void test09(){
        System.out.println(stuService.getAll());
    }
}