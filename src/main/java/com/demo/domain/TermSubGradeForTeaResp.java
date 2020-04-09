package com.demo.domain;

import lombok.Data;

/**
 * 教师查看的返回实体
 */
@Data
public class TermSubGradeForTeaResp {
    private Integer teaId;
    private Integer term;
    private Integer subId;
    private Double avgScore;
    private Double maxScore;
    private Double minScore;

    public TermSubGradeForTeaResp(Integer teaId, Integer term, Integer subId, Double avgScore, Double maxScore, Double minScore) {
        this.teaId = teaId;
        this.term = term;
        this.subId = subId;
        this.avgScore = avgScore;
        this.maxScore = maxScore;
        this.minScore = minScore;
    }
}
