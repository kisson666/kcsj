package com.wang.myinterface;

import com.wang.model.Student;

import java.util.List;

/**
 * Created by hppc on 2017/4/11.
 */
public interface StudentInterface {
    int addStu(Student student);
    List<Student> findStuBySex(String sex);
    List<Student> findByAcademy(String academy);
    List<Student> findByMajor(String major);
    List<Student> findByName(String name);
    List<Student> findByStuId(String stuId);
    List<Student> findBySex(String sex);
    Student findById(int id);
    int updateStu(Student student);
    int delStu(int id);
    List<Student> findAll();
    int insertStus(List<Student> students);

}
