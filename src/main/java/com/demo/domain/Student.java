package com.demo.domain;




import javax.persistence.*;

@Entity
@Table(name = "stu")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stuId ; // 主键ID

    private String stuName ; // 学生名称

    private int teaId ; // 教师表主键ID


}
