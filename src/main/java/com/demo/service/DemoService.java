package com.demo.service;

import com.demo.domain.*;
import java.util.List;

public interface DemoService {


    List<TermSubGradeForDirectorResp> findTermSubGradeForDirector(TermSubGradeForDirectorQuery entity) throws Exception;



    /**
     * 教务处主任可以查看教师-学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    List<TeaSubGradeForDirectorResp> findTeaSubGradeForDirector(TeaSubGradeForDirectorQuery entity) throws Exception;

    /**
     * 查询教师本人每学年，学的成绩
     * @param entity
     * @return
     */
    List<TermSubGradeForTeaResp> findTermSubGradeForTea(TermSubGradeForTeaQuery entity) throws Exception;

    /**
     * 学生可以查询本人每学年各学科成绩
     * @param entity
     * @return
     */
    List<TermSubGradeForStuResp> findTermSubGradeForStu(TermSubGradeForStuQuery entity) throws Exception;

    /**
     * 教务处主任查看的成绩 数量
     * @param entity
     * @return
     */
    Integer sumTermSubGradeForDirector(TermSubGradeForDirectorQuery entity)throws Exception;

    /**
     * 教务处主任-教师 成绩 数量
     * @param entity
     * @return
     */
    Integer sumTeaSubGradeForDirector(TeaSubGradeForDirectorQuery entity) throws Exception;

    /**
     * 教师查看的成绩 数量
     * @param entity
     * @return
     */
    Integer sumTermSubGradeForTea(TermSubGradeForTeaQuery entity)throws Exception;

    /**
     * 学生可以查看的成绩 数量
     * @param entity
     * @return
     */
    Integer sumTermSubGradeForStu(TermSubGradeForStuQuery entity)throws Exception;
}
