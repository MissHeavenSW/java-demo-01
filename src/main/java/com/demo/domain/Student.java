package com.demo.domain;




import javax.persistence.*;

@Entity
@Table(name = "stu")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stu_id")
    private int stu_id ; // 主键ID

    @Column(name = "stu_name")
    private String stu_name ; // 学生名称

    @Column(name = "tea_id")
    private int tea_id ; // 教师表主键ID
}
