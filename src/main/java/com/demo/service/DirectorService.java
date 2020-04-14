package com.demo.service;

import com.demo.domain.*;

import java.util.List;

public interface DirectorService {


    List<TermSubGradeForDirectorResp> findTermSubGradeForDirector(TermSubGradeForDirectorQuery entity) ;



    /**
     * 教务处主任可以查看教师-学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    List<TeaSubGradeForDirectorResp> findTeaSubGradeForDirector(TeaSubGradeForDirectorQuery entity);



    /**
     * 教务处主任查看的成绩 数量
     * @param entity
     * @return
     */
    Integer sumTermSubGradeForDirector(TermSubGradeForDirectorQuery entity);

    /**
     * 教务处主任-教师 成绩 数量
     * @param entity
     * @return
     */
    Integer sumTeaSubGradeForDirector(TeaSubGradeForDirectorQuery entity);

}
