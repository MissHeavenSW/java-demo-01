package com.demo.domain;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stu_score")
@Data
public class Score {

    @Id
    private int stuId ; //

    private int subId ; // 学科id

    private Double subScore ; // 成绩

    private int term ; // 学年

    @OneToMany
    @JoinColumn(name = "subId", referencedColumnName ="subId")
    private List<Subject> subList;


}
