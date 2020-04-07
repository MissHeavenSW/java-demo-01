package com.demo.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 教导主任查询每学年,每学科成绩的请求实体
 */
@Data
public class TermSubGradeForDirectorRequest {
    @ApiModelProperty(value="教导主任标识",dataType="String",name="personId",example="1")
    private String personId;//人物标识,用来标识是否是教导主任,这里采用1代表教导主任
}
