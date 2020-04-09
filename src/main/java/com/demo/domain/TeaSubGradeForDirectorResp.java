package com.demo.domain;

import lombok.Data;

/**
 * 主任查询教师返回实体
 */
@Data
public class TeaSubGradeForDirectorResp {

    private Integer teaId;
    private String teaName;
    private Integer subId;
    private Double avgScore;
    private Double maxScore;
    private Double minScore;

    public TeaSubGradeForDirectorResp(Integer teaId, String teaName, Integer subId, Double avgScore, Double maxScore, Double minScore) {
        this.teaId = teaId;
        this.teaName = teaName;
        this.subId = subId;
        this.avgScore = avgScore;
        this.maxScore = maxScore;
        this.minScore = minScore;
    }
}
