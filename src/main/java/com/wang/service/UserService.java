package com.wang.service;

import com.wang.model.Student;
import com.wang.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by hppc on 2017/2/27.
 */
public interface UserService {
    User checkLogin(User user);
    String register(User user);
    String setPassword(String newPassword,String oldPassword,String email);
    User setIco(MultipartFile multipartFile,User user);
    String setInfo(User user);
    List<User> getAll();
    User getById(String id);


}
