package com.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sub")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_id")
    private int subId ; // 主键ID

    @Column(name = "sub_name")
    private String subName ; // 学科名称


}