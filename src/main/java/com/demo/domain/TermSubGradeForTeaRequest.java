package com.demo.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 教师关联学科，可以查询教师本人每学年，学科平均成绩，最高分，最低分 请求实体
 */
@Data
public class TermSubGradeForTeaRequest {
    @ApiModelProperty(value="教师标识",dataType="String",name="personId",example="2")
    private String personId;//用户标识,用来标识是否是教师,教师传2
}
