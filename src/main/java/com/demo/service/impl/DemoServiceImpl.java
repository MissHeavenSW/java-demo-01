package com.demo.service.impl;

import com.demo.common.ResponsePageEntity;
import com.demo.dao.CourseDao;
import com.demo.dao.StudentDao;
import com.demo.dao.TeacherDao;
import com.demo.domain.*;
import com.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class DemoServiceImpl implements DemoService {


    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private CourseDao courseDao;


    //定义常亮
    private static final String SUB_ID = "sub_id";
    private static final String TEA_ID = "tea_id";
    private static final String STU_ID = "stu_id";
    private static final String SYSTEM_ERROR = "系统异常,请稍后再试";
    private static final String SUCCESS = "成功";
    private static final String FAIL = "失败";


    /**
     * 教导主任查询每学年,每学科成绩
     * @param entity
     * @return
     */
    @Transactional
    public ResponsePageEntity findTermSubGradeForDirector(TermSubGradeForDirectorRequest entity) throws Exception {
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();

        //3,数据查询
        try {
            //3.1数据查询封装
            Sort sort = Sort.by(Sort.Direction.DESC, SUB_ID);
            Pageable pageable = PageRequest.of(entity.getPage(),entity.getPage_size(),sort);
            //3.2数据查询
            Page<TermSubGradeForDirectorResponse> returnPage = courseDao.findTermSubGradeForDirector(entity.getTerm(),pageable);
            //3.3对返回参数判断
            if(StringUtils.isEmpty(returnPage)){
                responsePageEntity.setCode(SYSTEM_ERROR);
                responsePageEntity.setMsg(SYSTEM_ERROR);
                return responsePageEntity;
            }
            //3.4正确参数返回
            responsePageEntity.setTotalCount((int) returnPage.getTotalElements());
            responsePageEntity.setList(returnPage.getContent());
            return responsePageEntity;
        }catch (Exception e){
            responsePageEntity.setCode(FAIL);
            responsePageEntity.setMsg(FAIL);
            log.error("教导主任查看每学科,学年的成绩接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }



    /**
     * 教务处主任可以查看教师-学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    @Transactional
    public ResponsePageEntity findTeaSubGradeForDirector(TeaSubGradeForDirectorRequest entity) throws Exception {
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();

        //2,数据查询
        try {
            //2.1数据查询封装
            Sort sort = Sort.by(Sort.Direction.DESC,TEA_ID);
            Pageable pageable = PageRequest.of(entity.getPage(),entity.getPage_size(),sort);
            //2.2数据查询
            Page<TeaSubGradeForDirectorResponse> returnPage  = courseDao.findTeaSubGradeForDirector(entity.getTeacher_id(),pageable);
            //2.3对返回参数判断
            if(StringUtils.isEmpty(returnPage)){
                responsePageEntity.setCode(SYSTEM_ERROR);
                responsePageEntity.setMsg(SYSTEM_ERROR);
                return responsePageEntity;
            }
            //2.4正确参数返回
            responsePageEntity.setTotalCount((int) returnPage.getTotalElements());
            responsePageEntity.setList(returnPage.getContent());
            return responsePageEntity;
        }catch (Exception e){
            responsePageEntity.setCode(FAIL);
            responsePageEntity.setMsg(FAIL);
            log.error("教导主任查看每学科,学年的成绩接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }

    /**
     * 查询教师本人每学年，学的成绩
     * @param entity
     * @return
     */
    @Transactional
    public ResponsePageEntity findTermSubGradeForTea(TermSubGradeForTeaRequest entity) {
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();

        //2,数据查询
        try {
            //2.1数据查询封装
            Sort sort = Sort.by(Sort.Direction.DESC,TEA_ID);
            Pageable pageable = PageRequest.of(entity.getPage(),entity.getPage_size(),sort);
            //2.2数据查询
            Page<TermSubGradeForTeaResponse> returnPage  = teacherDao.findTermSubGradeForTea(entity.getTeacher_id(),pageable);
            //2.3对返回参数判断
            if(StringUtils.isEmpty(returnPage)){
                responsePageEntity.setCode(SYSTEM_ERROR);
                responsePageEntity.setMsg(SYSTEM_ERROR);
                return responsePageEntity;
            }
            //3.4正确参数返回
            responsePageEntity.setTotalCount((int) returnPage.getTotalElements());
            responsePageEntity.setList(returnPage.getContent());
            return responsePageEntity;
        }catch (Exception e){
            responsePageEntity.setCode(FAIL);
            responsePageEntity.setMsg(FAIL);
            log.error("查询教师本人每学年，学的成绩接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }

    /**
     * 学生可以查询本人每学年各学科成绩
     * @param entity
     * @return
     */
    @Transactional
    public ResponsePageEntity findTermSubGradeForStu(TermSubGradeForStuRequest entity) {
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        //3,数据查询
        try {
            //3.1数据查询封装
            Sort sort = Sort.by(Sort.Direction.DESC,STU_ID);
            Pageable pageable = PageRequest.of(entity.getPage(),entity.getPage_size(),sort);
            //3.2数据查询
            Page<TermSubGradeForStuResponse> returnPage  = studentDao.findTermSubGradeForStu(entity.getStu_id(),pageable);
            //3.3对返回参数判断
            if(StringUtils.isEmpty(returnPage)){
                responsePageEntity.setCode(SYSTEM_ERROR);
                responsePageEntity.setMsg(SYSTEM_ERROR);
                return responsePageEntity;
            }
            //3.4正确参数返回
            responsePageEntity.setTotalCount((int) returnPage.getTotalElements());
            responsePageEntity.setList(returnPage.getContent());
            return responsePageEntity;
        }catch (Exception e){
            responsePageEntity.setCode(FAIL);
            responsePageEntity.setMsg(FAIL);
            log.error("学生可以查询本人每学年各学科成绩接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }
}
