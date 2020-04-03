package com.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "tea_sub")
public class TeacherSub {

    @Id
    @Column(name = "tea_id")
    private int teaId ; // 教师id

    @Column(name = "sub_id")
    private int subId ; // 学科id

}