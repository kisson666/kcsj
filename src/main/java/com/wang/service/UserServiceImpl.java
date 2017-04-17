package com.wang.service;

import com.wang.common.Path;
import com.wang.model.Student;
import com.wang.model.User;
import com.wang.myinterface.UserInterface;
import com.wang.tools.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * Created by hppc on 2017/2/27.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserInterface userInterface;

    @Override
    public User checkLogin(User user) {
        String email = user.getEmail();
        String password = MD5Util.calc(user.getPassword());
        User usercheck = userInterface.findByEmailAndPassword(email,password);
        if (usercheck != null) {
            if (usercheck.getIco() == null){
                usercheck.setIco("/user_ico/default.png");   //如果当前用户没有设置头像 就用默认的
            }
        }
        return usercheck;
    }

    @Override
    public String register(User user) {
        //String email = user.getEmail();
        int result = -1;
        String password = MD5Util.calc(user.getPassword());
        user.setPassword(password);
        result = userInterface.addUser(user);
        if(result != -1){
            return "register successfully";
        } else {
            return "register failed";
        }
    }

    @Override
    public String setPassword(String newPassword, String oldPassword, String email) {
        newPassword = MD5Util.calc(newPassword);
        oldPassword = MD5Util.calc(oldPassword);
        User usercheck = userInterface.findByEmailAndPassword(email,oldPassword);
        if (usercheck != null){
            usercheck.setPassword(newPassword);
            int i = userInterface.updatePassword(usercheck);
            if (i == 1){
                return "setPassword successfully";
            } else {
                return "newPassword is wrong";
            }

        } else {
            return "oldPassword is wrong";
        }

    }

    @Override
    public User setIco(MultipartFile multipartFile, User user) {
        long time = System.currentTimeMillis();//获得毫秒数
        String foo = String.valueOf(time);
        String filename = user.getEmail()+foo;  //用用户邮箱+毫秒数作为文件名 保证唯一
        String path = Path.ROOT_PATH+Path.ICO_PATH+filename+".jpg";  //物理地址
        try {
            multipartFile.transferTo(new File(path));  //转存
            user.setIco(Path.ICO_URL+filename+".jpg");  //浏览器访问的url  放在上一句的下面是保证先能转存成功再设置数据库字段
            userInterface.updateIco(user); //更新数据库中头像字段的路径
            return user;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String setInfo(User user) {
        int i = userInterface.updateInfo(user);
        if(i == 1){
            return "修改个人资料成功";
        } else {
            return "修改个人资料失败";
        }
    }

    @Override
    public List<User> getAll() {
        return userInterface.findAll();
    }

    @Override
    public User getById(String id) {
        return userInterface.findById(id);
    }


}
