package com.demo.service.impl;

import com.demo.common.RequestPageEntity;
import com.demo.common.ResponseEnum;
import com.demo.common.ResponsePageEntity;
import com.demo.dao.DemoDao;
import com.demo.domain.TeaSubGradeForDirectorRequest;
import com.demo.domain.TermSubGradeForDirectorRequest;
import com.demo.domain.TermSubGradeForStuRequest;
import com.demo.domain.TermSubGradeForTeaRequest;
import com.demo.service.DemoService;
import com.demo.util.CodeHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDao demoDao;


    //定义常亮
    private static final String DIRECTOR_DEFINE_ID = "1";
    private static final String TEA_DEFINE_ID = "2";
    private static final String STU_DEFINE_ID = "3";
    private static final String SUB_ID = "sub_id";
    private static final String TEA_ID = "tea_id";
    private static final String STU_ID = "stu_id";


    /**
     * 教导主任查询每学年,每学科成绩
     * @param entity
     * @return
     */
    public ResponsePageEntity selectTermSubGradeForDirector(RequestPageEntity<TermSubGradeForDirectorRequest> entity) throws Exception {
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        //1.对入参的非空判断
        if(CodeHelper.isNullOrEmpty(entity.getBody().getPersonId())){
            responsePageEntity.setResultCode(ResponseEnum.DIRECTOR_ID_IS_NOT_NULL.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.DIRECTOR_ID_IS_NOT_NULL.getMessage());
            return responsePageEntity;
        }
        //2.再对传入参数做等值判断
        if(!DIRECTOR_DEFINE_ID.equals(entity.getBody().getPersonId())){
            //为1时才为教导主任
            responsePageEntity.setResultCode(ResponseEnum.REQUEST_ID_IS_NOT_DIRECTOR.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.REQUEST_ID_IS_NOT_DIRECTOR.getMessage());
            return responsePageEntity;
        }

        //3,数据查询
        try {
            //3.1数据查询封装
            Sort sort = Sort.by(Sort.Direction.DESC, SUB_ID);
            Pageable pageable = PageRequest.of(entity.getPageIndex(),entity.getPageSize(),sort);
            //3.2数据查询
            Page<Map> returnPage = demoDao.selectTermSubGradeForDirector(pageable);
            //3.3对返回参数判断
            if(CodeHelper.isNull(returnPage)){
                responsePageEntity.setResultCode(ResponseEnum.SYSTEM_ERROR.getCode());
                responsePageEntity.setResultDesc(ResponseEnum.SYSTEM_ERROR.getMessage());
                return responsePageEntity;
            }
            //3.4正确参数返回
            responsePageEntity.setPageIndex(entity.getPageIndex());
            responsePageEntity.setPageSize(entity.getPageSize());
            responsePageEntity.setTotalCount((int) returnPage.getTotalElements());
            responsePageEntity.setTotalPage(returnPage.getTotalPages());
            responsePageEntity.setData(returnPage.getContent());
            responsePageEntity.setResultCode(ResponseEnum.SUCCESS.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.SUCCESS.getMessage());
            return responsePageEntity;
        }catch (Exception e){
            responsePageEntity.setResultCode(ResponseEnum.FAIL.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.FAIL.getMessage());
            log.error("教导主任查看每学科,学年的成绩接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }



    /**
     * 教务处主任可以查看教师-学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    public ResponsePageEntity selectTeaSubGradeForDirector(RequestPageEntity<TeaSubGradeForDirectorRequest> entity) throws Exception {
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        //1.对入参的非空判断
        if(CodeHelper.isNullOrEmpty(entity.getBody().getPersonId())){
            responsePageEntity.setResultCode(ResponseEnum.DIRECTOR_ID_IS_NOT_NULL.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.DIRECTOR_ID_IS_NOT_NULL.getMessage());
            return responsePageEntity;
        }
        //2.再对传入参数做等值判断
        if(!DIRECTOR_DEFINE_ID.equals(entity.getBody().getPersonId())){
            //为1时才为教导主任
            responsePageEntity.setResultCode(ResponseEnum.REQUEST_ID_IS_NOT_DIRECTOR.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.REQUEST_ID_IS_NOT_DIRECTOR.getMessage());
            return responsePageEntity;
        }

        //3,数据查询
        try {
            //3.1数据查询封装
            Sort sort = Sort.by(Sort.Direction.DESC,TEA_ID);
            Pageable pageable = PageRequest.of(entity.getPageIndex(),entity.getPageSize(),sort);
            //3.2数据查询
            Page<Map> returnPage  = demoDao.selectTeaSubGradeForDirector(pageable);
            //3.3对返回参数判断
            if(CodeHelper.isNull(returnPage)){
                responsePageEntity.setResultCode(ResponseEnum.SYSTEM_ERROR.getCode());
                responsePageEntity.setResultDesc(ResponseEnum.SYSTEM_ERROR.getMessage());
                return responsePageEntity;
            }
            //3.4正确参数返回
            responsePageEntity.setPageIndex(entity.getPageIndex());
            responsePageEntity.setPageSize(entity.getPageSize());
            responsePageEntity.setTotalCount((int) returnPage.getTotalElements());
            responsePageEntity.setTotalPage(returnPage.getTotalPages());
            responsePageEntity.setData(returnPage.getContent());
            responsePageEntity.setResultCode(ResponseEnum.SUCCESS.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.SUCCESS.getMessage());
            return responsePageEntity;
        }catch (Exception e){
            responsePageEntity.setResultCode(ResponseEnum.FAIL.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.FAIL.getMessage());
            log.error("教导主任查看每学科,学年的成绩接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }

    /**
     * 查询教师本人每学年，学的成绩
     * @param entity
     * @return
     */
    public ResponsePageEntity selectTermSubGradeForTea(RequestPageEntity<TermSubGradeForTeaRequest> entity) {
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        //1.对入参的非空判断
        if(CodeHelper.isNullOrEmpty(entity.getBody().getPersonId())){
            responsePageEntity.setResultCode(ResponseEnum.TEA_ID_IS_NOT_NULL.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.TEA_ID_IS_NOT_NULL.getMessage());
            return responsePageEntity;
        }
        //2.再对传入参数做等值判断
        if(!TEA_DEFINE_ID.equals(entity.getBody().getPersonId())){
            //为1时才为教导主任
            responsePageEntity.setResultCode(ResponseEnum.REQUEST_ID_IS_NOT_TEA.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.REQUEST_ID_IS_NOT_TEA.getMessage());
            return responsePageEntity;
        }

        //3,数据查询
        try {
            //3.1数据查询封装
            Sort sort = Sort.by(Sort.Direction.DESC,TEA_ID);
            Pageable pageable = PageRequest.of(entity.getPageIndex(),entity.getPageSize(),sort);
            //3.2数据查询
            Page<Map> returnPage  = demoDao.selectTermSubGradeForTea(pageable);
            //3.3对返回参数判断
            if(CodeHelper.isNull(returnPage)){
                responsePageEntity.setResultCode(ResponseEnum.SYSTEM_ERROR.getCode());
                responsePageEntity.setResultDesc(ResponseEnum.SYSTEM_ERROR.getMessage());
                return responsePageEntity;
            }
            //3.4正确参数返回
            responsePageEntity.setPageIndex(entity.getPageIndex());
            responsePageEntity.setPageSize(entity.getPageSize());
            responsePageEntity.setTotalCount((int) returnPage.getTotalElements());
            responsePageEntity.setTotalPage(returnPage.getTotalPages());
            responsePageEntity.setData(returnPage.getContent());
            responsePageEntity.setResultCode(ResponseEnum.SUCCESS.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.SUCCESS.getMessage());
            return responsePageEntity;
        }catch (Exception e){
            responsePageEntity.setResultCode(ResponseEnum.FAIL.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.FAIL.getMessage());
            log.error("查询教师本人每学年，学的成绩接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }

    /**
     * 学生可以查询本人每学年各学科成绩
     * @param entity
     * @return
     */
    public ResponsePageEntity selectTermSubGradeForStu(RequestPageEntity<TermSubGradeForStuRequest> entity) {
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        //1.对入参的非空判断
        if(CodeHelper.isNullOrEmpty(entity.getBody().getPersonId())){
            responsePageEntity.setResultCode(ResponseEnum.STU_ID_IS_NOT_NULL.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.STU_ID_IS_NOT_NULL.getMessage());
            return responsePageEntity;
        }
        //2.再对传入参数做等值判断
        if(!STU_DEFINE_ID.equals(entity.getBody().getPersonId())){
            //为1时才为教导主任
            responsePageEntity.setResultCode(ResponseEnum.REQUEST_ID_IS_NOT_STU.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.REQUEST_ID_IS_NOT_STU.getMessage());
            return responsePageEntity;
        }

        //3,数据查询
        try {
            //3.1数据查询封装
            Sort sort = Sort.by(Sort.Direction.DESC,STU_ID);
            Pageable pageable = PageRequest.of(entity.getPageIndex(),entity.getPageSize(),sort);
            //3.2数据查询
            Page<Map> returnPage  = demoDao.selectTermSubGradeForStu(pageable);
            //3.3对返回参数判断
            if(CodeHelper.isNull(returnPage)){
                responsePageEntity.setResultCode(ResponseEnum.SYSTEM_ERROR.getCode());
                responsePageEntity.setResultDesc(ResponseEnum.SYSTEM_ERROR.getMessage());
                return responsePageEntity;
            }
            //3.4正确参数返回
            responsePageEntity.setPageIndex(entity.getPageIndex());
            responsePageEntity.setPageSize(entity.getPageSize());
            responsePageEntity.setTotalCount((int) returnPage.getTotalElements());
            responsePageEntity.setTotalPage(returnPage.getTotalPages());
            responsePageEntity.setData(returnPage.getContent());
            responsePageEntity.setResultCode(ResponseEnum.SUCCESS.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.SUCCESS.getMessage());
            return responsePageEntity;
        }catch (Exception e){
            responsePageEntity.setResultCode(ResponseEnum.FAIL.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.FAIL.getMessage());
            log.error("学生可以查询本人每学年各学科成绩接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }
}
