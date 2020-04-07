package com.demo.domain;
import javax.persistence.*;

@Entity
@Table(name = "stu_score")
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


}
