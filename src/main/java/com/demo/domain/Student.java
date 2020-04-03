package com.demo.domain;




import javax.persistence.*;

@Entity
@Table(name = "stu")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stu_id")
    private int stuId ; // 主键ID

    @Column(name = "stu_name")
    private String stuName ; // 学生名称

    @Column(name = "tea_id")
    private int teaId ; // 教师表主键ID
}
