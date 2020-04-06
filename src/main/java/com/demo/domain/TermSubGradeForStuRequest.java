package com.demo.domain;

import lombok.Data;

/**
 *学生可以查询本人每学年各学科成绩
 */
@Data
public class TermSubGradeForStuRequest {
    private String personId;//用来标识是学生的,这里采用3
}
