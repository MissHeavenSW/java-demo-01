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
    private int sub_id ; // 主键ID

    @Column(name = "sub_name")
    private String sub_name ; // 学科名称


    public Subject(String sub_name) {
        this.sub_name = sub_name;
    }
}
