package com.demo.dao;

import com.demo.domain.TermSubGradeForTeaQuery;
import com.demo.domain.TermSubGradeForTeaResp;

import java.util.List;

public interface TeacherDao  {


    /**
     * 老师查询成绩
     * @param entity
     * @return
     */
    List<TermSubGradeForTeaResp> findTermSubGradeForTea(TermSubGradeForTeaQuery entity);


    /**
     * 老师查询成绩数量
     * @param entity
     * @return
     */
    Integer sumTermSubGradeForTea(TermSubGradeForTeaQuery entity);
}
