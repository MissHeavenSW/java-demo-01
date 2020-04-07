package com.demo.domain;

import lombok.Data;

/**
 * 教师查看的返回实体
 */
@Data
public class TermSubGradeForTeaResponse {
    private Integer tea_id;
    private Integer term;
    private Integer sub_id;
    private Double avg_score;
    private Double max_score;
    private Double min_score;

    public TermSubGradeForTeaResponse(Integer tea_id, Integer term, Integer sub_id, Double avg_score, Double max_score, Double min_score) {
        this.tea_id = tea_id;
        this.term = term;
        this.sub_id = sub_id;
        this.avg_score = avg_score;
        this.max_score = max_score;
        this.min_score = min_score;
    }
}
