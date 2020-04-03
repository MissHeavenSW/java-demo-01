package com.demo.domain;


import lombok.Data;

@Data
public class Request {

    private String personId;//1代表主任

    private String term; //学年 1代表查询第一学年,2代表查询第二学年

    private Integer teaId;//教师的id,用来查询哪个教师的,教师有1,2两个id(目前)

    private Integer stuId;//学生id
}
