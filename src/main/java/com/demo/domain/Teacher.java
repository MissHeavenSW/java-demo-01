package com.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tea")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tea_id")
    private int tea_id ; // 主键ID

    @Column(name = "tea_name")
    private String tea_name ; // 教师名称

    @OneToMany
    @JoinColumn(name = "tea_id",referencedColumnName ="tea_id")
    private List<TeacherSub> teacherSubList;


    public Teacher(String tea_name, List<TeacherSub> teacherSubList) {
        this.tea_name = tea_name;
        this.teacherSubList = teacherSubList;
    }
}
