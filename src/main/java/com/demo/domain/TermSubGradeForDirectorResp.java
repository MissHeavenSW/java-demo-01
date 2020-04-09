package com.demo.domain;

import lombok.Data;

/**
 * 主任查询返回实体
 */
@Data
public class TermSubGradeForDirectorResp {
    private Integer term;
    private Integer subId;
    private String subName;
    private Double avgScore;
    private Double maxScore;
    private Double minScore;

    public TermSubGradeForDirectorResp(Integer term, Integer subId, String subName, Double avgScore, Double maxScore, Double minScore) {
        this.term = term;
        this.subId = subId;
        this.subName = subName;
        this.avgScore = avgScore;
        this.maxScore = maxScore;
        this.minScore = minScore;
    }
}
