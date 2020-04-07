package com.demo.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *学生可以查询本人每学年各学科成绩
 */
@Data
public class TermSubGradeForStuRequest implements Serializable {
    @ApiModelProperty(value="学生标识",dataType="String",name="personId",example="3")
    private String personId;//用来标识是学生的,这里采用3
}
