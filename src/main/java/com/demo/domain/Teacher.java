package com.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tea")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tea_id")
    private int teaId ; // 主键ID

    @Column(name = "tea_name")
    private String teaName ; // 教师名称

}
