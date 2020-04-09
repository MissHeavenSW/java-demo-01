package com.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.demo.common.*;
import com.demo.domain.*;
import com.demo.service.DemoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Slf4j
public class DemoController{

    @Autowired
    private DemoService demoService;

    private static final String SUCCESS = "成功";
    private static final String SUCCESS_CODE = "0";
    private static final String FAIL = "失败";
    private static final String FAIL_CODE = "失败";

    /**
     * 教务处主任可以查看每学年每学科 平均,最高,最低成绩
     * @param entity
     * @return
     */
    @Transactional
    @ApiOperation(value="教导主任查询")
    @RequestMapping("/nhsoft.demo.director.findTermSubGradeForDirector")
    public ResponsePageEntity findTermSubGradeForDirector(@RequestBody TermSubGradeForDirectorQuery entity){
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        try {
            List<TermSubGradeForDirectorResp> returnList = demoService.findTermSubGradeForDirector(entity);
            Integer returnCount = demoService.sumTermSubGradeForDirector(entity);
            responsePageEntity.setList(returnList);
            responsePageEntity.setTotalCount(returnCount);
            responsePageEntity.setPageIndex(entity.getPage());
            responsePageEntity.setPageSize(entity.getPage_size());
            responsePageEntity.setCode(SUCCESS_CODE);
            responsePageEntity.setMsg(SUCCESS);
        }catch (Exception e){
            responsePageEntity.setCode(FAIL_CODE);
            responsePageEntity.setMsg(FAIL);
            log.error("教导主任查看每学科,学年的成绩接口业务层出现异常，入参为{}异常信息为{}",JSONObject.toJSONString(entity),e);

        }
        return responsePageEntity;
    }


    /**
     * 教务处主任可以查看教师-学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    @Transactional
    @ApiOperation(value="教导主任-老师查询")
    @RequestMapping("/nhsoft.demo.director.findTeaSubGradeForDirector")
    public ResponsePageEntity findTeaSubGradeForDirector(@RequestBody TeaSubGradeForDirectorQuery entity){
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        try {
            List<TeaSubGradeForDirectorResp> returnList = demoService.findTeaSubGradeForDirector(entity);
            Integer returnCount = demoService.sumTeaSubGradeForDirector(entity);
            responsePageEntity.setList(returnList);
            responsePageEntity.setTotalCount(returnCount);
            responsePageEntity.setPageIndex(entity.getPage());
            responsePageEntity.setPageSize(entity.getPage_size());
            responsePageEntity.setCode(SUCCESS_CODE);
            responsePageEntity.setMsg(SUCCESS);
        }catch (Exception e){
            responsePageEntity.setCode(FAIL_CODE);
            responsePageEntity.setMsg(FAIL);
            log.error("教导主任查看教师-学科的成绩接口业务层出现异常，入参为{}异常信息为{}",JSONObject.toJSONString(entity),e);
        }
        return responsePageEntity;
    }


    /**
     * 教师关联学科，可以查询教师本人每学年，学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    @Transactional
    @ApiOperation(value="教师查询")
    @RequestMapping("/nhsoft.demo.teacher.findTermSubGradeForTea")
    public ResponsePageEntity findTermSubGradeForTea(@RequestBody TermSubGradeForTeaQuery entity){
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        try {
            List<TermSubGradeForTeaResp> returnList  = demoService.findTermSubGradeForTea(entity);
            Integer returnCount = demoService.sumTermSubGradeForTea(entity);
            responsePageEntity.setList(returnList);
            responsePageEntity.setTotalCount(returnCount);
            responsePageEntity.setPageIndex(entity.getPage());
            responsePageEntity.setPageSize(entity.getPage_size());
            responsePageEntity.setCode(SUCCESS_CODE);
            responsePageEntity.setMsg(SUCCESS);
        }catch (Exception e){
            responsePageEntity.setCode(FAIL_CODE);
            responsePageEntity.setMsg(FAIL);
            log.error("查询教师本人每学年，学的成绩接口业务层出现异常，入参为{}异常信息为{}",JSONObject.toJSONString(entity),e);
        }
        return responsePageEntity;
    }


    /**
     * 学生可以查询本人每学年各学科成绩
     * @param entity
     * @return
     */
    @Transactional
    @ApiOperation(value="学生查询")
    @RequestMapping("/nhsoft.demo.student.findTermSubGradeForStu")
    public ResponsePageEntity findTermSubGradeForStu(@RequestBody TermSubGradeForStuQuery entity){
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        try {
            List<TermSubGradeForStuResp> returnList  = demoService.findTermSubGradeForStu(entity);
            Integer returnCount = demoService.sumTermSubGradeForStu(entity);
            responsePageEntity.setTotalCount(returnCount);
            responsePageEntity.setList(returnList);
            responsePageEntity.setPageIndex(entity.getPage());
            responsePageEntity.setPageSize(entity.getPage_size());
            responsePageEntity.setCode(SUCCESS_CODE);
            responsePageEntity.setMsg(SUCCESS);
        }catch (Exception e){
            responsePageEntity.setCode(FAIL_CODE);
            responsePageEntity.setMsg(FAIL);
            log.error("学生可以查询本人每学年各学科成绩接口业务层出现异常，入参为{}异常信息为{}",JSONObject.toJSONString(entity),e);
        }
        return responsePageEntity;
    }


}
