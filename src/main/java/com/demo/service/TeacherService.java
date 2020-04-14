package com.demo.service;

import com.demo.domain.*;

import java.util.List;

public interface TeacherService {



    /**
     * 查询教师本人每学年，学的成绩
     * @param entity
     * @return
     */
    List<TermSubGradeForTeaResp> findTermSubGradeForTea(TermSubGradeForTeaQuery entity);



    /**
     * 教师查看的成绩 数量
     * @param entity
     * @return
     */
    Integer sumTermSubGradeForTea(TermSubGradeForTeaQuery entity);

}
