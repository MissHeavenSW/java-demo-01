package com.demo.domain;
import javax.persistence.*;

@Entity
@Table(name = "stu_score")
public class Score {

    @Id
    @Column(name = "stu_id")
    private int stuId ; //

    @Column(name = "sub_id")
    private int subId ; // 学科id

    @Column(name = "sub_score")
    private Double subScore ; // 成绩

    @Column(name = "term")
    private int term ; // 学年


}
