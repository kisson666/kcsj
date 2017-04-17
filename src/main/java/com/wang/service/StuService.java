package com.wang.service;

import com.wang.model.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by hppc on 2017/4/11.
 */
public interface StuService {
    String addStudent(Student student);
    List<Student> getStuByAcademy(String academy);
    List<Student> getStuByMajor(String major);
    List<Student> getStuByName(String name);
    List<Student> getStuByStuId(String stuId);
    List<Student> getStuBySex(String sex);
    String setStu(Student student);
    String delStu(int id);
    List<Student> getAll();
    List<Student> inpExcel(MultipartFile mfile,String email);
    String insertStus(List<Student> students);
}
