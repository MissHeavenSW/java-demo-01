package com.demo.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 教务处主任可以查看教师-学科平均成绩，最高分，最低分请求实体
 */
@Data
public class TeaSubGradeForDirectorQuery {
    @ApiModelProperty(value="教导主任标识",dataType="String",name="teacher_id",example="1")
    @NotNull(message = "教师id不能为空")
    private Integer teacherId;//教师id

    private Integer page = 0;//第几页
    private Integer pageSize = 10;//每页大小
}
