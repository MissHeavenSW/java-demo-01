package com.demo.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 教师关联学科，可以查询教师本人每学年，学科平均成绩，最高分，最低分 请求实体
 */
@Data
public class TermSubGradeForTeaQuery {
    @ApiModelProperty(value="教师id",dataType="String",name="personId",example="2")
    @NotNull(message = "教师id不能为空")
    private Integer teacherId;//教师id

    private Integer page =0;//第几页
    private Integer pageSize=10;//每页大小
}
