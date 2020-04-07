package com.demo.domain;

import lombok.Data;

/**
 * 主任查询返回实体
 */
@Data
public class TermSubGradeForDirectorResponse {
    private Integer term;
    private Integer sub_id;
    private String sub_name;
    private Double avg_score;
    private Double max_score;
    private Double min_score;

    public TermSubGradeForDirectorResponse(Integer term, Integer sub_id, String sub_name, Double avg_score, Double max_score, Double min_score) {
        this.term = term;
        this.sub_id = sub_id;
        this.sub_name = sub_name;
        this.avg_score = avg_score;
        this.max_score = max_score;
        this.min_score = min_score;
    }
}
