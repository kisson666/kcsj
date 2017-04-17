package com.wang.service;

import com.wang.common.Path;
import com.wang.model.Student;
import com.wang.myinterface.StudentInterface;
import com.wang.tools.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by hppc on 2017/4/11.
 */
@Service
public class StuServiceImpl implements StuService {
    @Autowired
    StudentInterface studentInterface;
    @Autowired
    ReadExcel readExcel;
    @Override
    public String addStudent(Student student) {
        if (student.getStuId() == null || student.getName() == null){
            //equals(null) 无意义 若为null 会返回空指针异常
            return "添加失败";
        } else {
            int i = -1;
            i = studentInterface.addStu(student);
            if (i != -1){
                return "添加成功";
            } else {
                System.out.println(i);
                return "添加失败";
            }

        }
    }


    @Override
    public List<Student> getStuByAcademy(String academy) {
        List<Student> students = studentInterface.findByAcademy(academy);
        return students;
    }

    @Override
    public List<Student> getStuByMajor(String major) {
        List<Student> students = studentInterface.findByMajor(major);
        return students;
    }

    @Override
    public List<Student> getStuByName(String name) {
        List<Student> students = studentInterface.findByName(name);
//        System.out.println(students);
        return students;
    }

    @Override
    public List<Student> getStuByStuId(String stuId) {
        List<Student> students = studentInterface.findByStuId(stuId);
        return students;
    }

    @Override
    public List<Student> getStuBySex(String sex) {
        List<Student> students = studentInterface.findBySex(sex);
        return students;
    }

    @Override
    public String setStu(Student student) {
       int i = studentInterface.updateStu(student);
       if (i == 1 ) {
           return "修改成功";
       } else {
           return "修改失败";
       }
    }

    @Override
    public String delStu(int id) {
        int i = studentInterface.delStu(id);
        if (i == 1){
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @Override
    public List<Student> getAll() {
        return studentInterface.findAll();
    }

    @Override
    public List<Student> inpExcel(MultipartFile mfile,String email) {
        long time = System.currentTimeMillis();//获得毫秒数
        String foo = String.valueOf(time);
        String transPath = Path.ROOT_PATH+Path.STU_EXCEL+email+foo;
        return readExcel.getExcelInfo(mfile,transPath);
    }

    @Override
    public String insertStus(List<Student> students) {
        int i = -1;
        try {
            i = studentInterface.insertStus(students);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("执行批量插入时候抛异常");
        }
        System.out.println("插入最后一条记录后总共影响的行数是"+i);
        if (i != -1){
            return "导入成功";
        } else {
            return "导入失败";
        }

    }
}
