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
    private int tea_id ; // 主键ID

    @Column(name = "tea_name")
    private String tea_name ; // 教师名称

}
