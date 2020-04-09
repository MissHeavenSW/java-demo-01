package com.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tea_sub")
@Data
public class TeacherSub {

    @Id
    @Column(name = "tea_id")
    private int tea_id ; // 教师id

    @Column(name = "sub_id")
    private int sub_id ; // 学科id

    @OneToMany
    @JoinColumn(name = "sub_id",referencedColumnName ="sub_id")
    private List<Score> scoreList;

    public TeacherSub(int tea_id, int sub_id, List<Score> scoreList) {
        this.tea_id = tea_id;
        this.sub_id = sub_id;
        this.scoreList = scoreList;
    }
}
