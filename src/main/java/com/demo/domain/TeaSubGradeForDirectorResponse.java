package com.demo.domain;

import lombok.Data;

/**
 * 主任查询教师返回实体
 */
@Data
public class TeaSubGradeForDirectorResponse {

    private Integer tea_id;
    private String tea_name;
    private Integer sub_id;
    private Double avg_score;
    private Double max_score;
    private Double min_score;

    public TeaSubGradeForDirectorResponse(Integer tea_id, String tea_name, Integer sub_id, Double avg_score, Double max_score, Double min_score) {
        this.tea_id = tea_id;
        this.tea_name = tea_name;
        this.sub_id = sub_id;
        this.avg_score = avg_score;
        this.max_score = max_score;
        this.min_score = min_score;
    }
}
