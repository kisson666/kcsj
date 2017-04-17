package com.wang.myinterface;

import com.wang.model.Student;
import com.wang.model.User;

import java.util.List;

/**
 * Created by hppc on 2017/2/20.
 */
//@Repository                     //这个地方不加这个注解也能用 只是service层@Autowirred装配的时候会提示有错误
public interface UserInterface {   //，但是可以正常使用，按理说我在配置文件中配置了自动扫描这些接口，应该不用再加上@Repository的，就跟springdatajpa的repository一样//我还不知道是为什么
//    List<User> selectUsers(String name);
//    void addUser(User user);
//    void updateUser(User user);
//    void deleteUser(int id);
      User findByEmailAndPassword(String email,String password); //
      int addUser(User user);
      int updatePassword(User user);
      int updateIco(User user);
      int updateInfo(User user);
      List<User> findAll();
      User findById(String id);
}
