package com.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sub")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subId ; // 主键ID

    private String subName ; // 学科名称


}
