package com.demo.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 教导主任查询每学年,每学科成绩的请求实体
 */
@Data
public class TermSubGradeForDirectorRequest {
    @ApiModelProperty(value="学年",dataType="String",name="term",example="1")
    @NotNull(message = "学年不能为空")
    private Integer term;

    private Integer page = 0 ;//第几页
    private Integer page_size = 10;//每页大小
}
