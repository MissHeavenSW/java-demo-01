package com.demo.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *学生可以查询本人每学年各学科成绩
 */
@Data
public class TermSubGradeForStuQuery implements Serializable {
    @ApiModelProperty(value="学生id",dataType="String",name="personId",example="3")
    @NotNull(message = "学生id不能为空")
    private Integer stu_id;//学生id
    private Integer page =0 ;//第几页
    private Integer page_size =10;//每页大小
}
