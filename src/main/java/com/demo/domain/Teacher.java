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
    private int teaId ; // 主键ID

    private String teaName ; // 教师名称

    @OneToMany
    @JoinColumn(name = "teaId",referencedColumnName ="teaId")
    private List<TeacherSub> teacherSubList;



}
