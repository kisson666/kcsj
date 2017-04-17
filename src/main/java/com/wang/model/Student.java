package com.wang.model;

/**
 * Created by hppc on 2017/4/11.
 */
public class Student {
    private String stuId;
    private String name;
    private String sex;
    private String major;
    private String academy;
    private String entry_year;
    private String hometown;
    private String phone;
    private int id;

    public Student(String stuId, String name, String sex, String major, String academy, String entry_year, String hometown, String phone, int id) {
        this.stuId = stuId;
        this.name = name;
        this.sex = sex;
        this.major = major;
        this.academy = academy;
        this.entry_year = entry_year;
        this.hometown = hometown;
        this.phone = phone;
        this.id = id;
    }

    public Student(String stuId, String name, String sex, String major, String academy, String entry_year, String hometown, String phone) {
        this.stuId = stuId;
        this.name = name;
        this.sex = sex;
        this.major = major;

        this.academy = academy;
        this.entry_year = entry_year;
        this.hometown = hometown;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId='" + stuId + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", major='" + major + '\'' +
                ", academy='" + academy + '\'' +
                ", entry_year='" + entry_year + '\'' +
                ", hometown='" + hometown + '\'' +

                ", phone='" + phone + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student() {
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getEntry_year() {
        return entry_year;
    }

    public void setEntry_year(String entry_year) {
        this.entry_year = entry_year;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
