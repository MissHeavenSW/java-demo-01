package com.demo.domain;

import lombok.Data;

/**
 * 学生查询的返回实体
 */
@Data
public class TermSubGradeForStuResponse {
    private Integer stu_id;
    private Double sub_score;
    private Integer term;
    private Integer sub_id;

    public TermSubGradeForStuResponse(Integer stu_id, Double sub_score, Integer term, Integer sub_id) {
        this.stu_id = stu_id;
        this.sub_score = sub_score;
        this.term = term;
        this.sub_id = sub_id;
    }
}
