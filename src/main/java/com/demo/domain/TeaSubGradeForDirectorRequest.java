package com.demo.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 教务处主任可以查看教师-学科平均成绩，最高分，最低分请求实体
 */
@Data
public class TeaSubGradeForDirectorRequest {
    @ApiModelProperty(value="教导主任标识",dataType="String",name="personId",example="1")
    private String personId;//教导主任的辨认标识,1代表是教导主任
}
