package com.demo.service;


import com.demo.common.ResponsePageEntity;
import com.demo.domain.*;

public interface DemoService {


    ResponsePageEntity findTermSubGradeForDirector(TermSubGradeForDirectorRequest entity) throws Exception;



    /**
     * 教务处主任可以查看教师-学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    ResponsePageEntity findTeaSubGradeForDirector(TeaSubGradeForDirectorRequest entity) throws Exception;

    /**
     * 查询教师本人每学年，学的成绩
     * @param entity
     * @return
     */
    ResponsePageEntity findTermSubGradeForTea(TermSubGradeForTeaRequest entity);

    /**
     * 学生可以查询本人每学年各学科成绩
     * @param entity
     * @return
     */
    ResponsePageEntity findTermSubGradeForStu(TermSubGradeForStuRequest entity);
}
