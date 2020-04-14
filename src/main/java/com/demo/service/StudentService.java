package com.demo.service;

import com.demo.domain.*;

import java.util.List;

public interface StudentService {




    /**
     * 学生可以查询本人每学年各学科成绩
     * @param entity
     * @return
     */
    List<TermSubGradeForStuResp> findTermSubGradeForStu(TermSubGradeForStuQuery entity);


    /**
     * 学生可以查看的成绩 数量
     * @param entity
     * @return
     */
    Integer sumTermSubGradeForStu(TermSubGradeForStuQuery entity);
}
