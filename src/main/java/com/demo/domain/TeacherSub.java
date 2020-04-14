package com.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tea_sub")
@Data
public class TeacherSub {

    @Id
    private int teaId ; // 教师id
    private int subId ; // 学科id

    @OneToMany
    @JoinColumn(name = "subId",referencedColumnName ="subId")
    private List<Score> scoreList;

}
