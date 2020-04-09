package com.demo.domain;

import lombok.Data;

/**
 * 学生查询的返回实体
 */
@Data
public class TermSubGradeForStuResp {
    private Integer stuId;
    private Double subScore;
    private Integer term;
    private Integer subId;

    public TermSubGradeForStuResp(Integer stuId, Double subScore, Integer term, Integer subId) {
        this.stuId = stuId;
        this.subScore = subScore;
        this.term = term;
        this.subId = subId;
    }
}
