package com.demo.domain;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stu_score")
@Data
public class Score {

    @Id
    @Column(name = "stu_id")
    private int stu_id ; //

    @Column(name = "sub_id")
    private int sub_id ; // 学科id

    @Column(name = "sub_score")
    private Double sub_score ; // 成绩

    @Column(name = "term")
    private int term ; // 学年

    @OneToMany
    @JoinColumn(name = "sub_id", referencedColumnName ="sub_id")
    private List<Subject> subList;

    public Score(int stu_id, int sub_id, Double sub_score, int term, List<Subject> subList) {
        this.stu_id = stu_id;
        this.sub_id = sub_id;
        this.sub_score = sub_score;
        this.term = term;
        this.subList = subList;
    }
}
