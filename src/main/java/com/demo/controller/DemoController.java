package com.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.demo.common.*;
import com.demo.domain.TeaSubGradeForDirectorRequest;
import com.demo.domain.TermSubGradeForDirectorRequest;
import com.demo.domain.TermSubGradeForStuRequest;
import com.demo.domain.TermSubGradeForTeaRequest;
import com.demo.service.DemoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoController{

    @Autowired
    private DemoService demoService;

    private static final String SUCCESS = "成功";
    private static final String FAIL = "失败";

    /**
     * 教务处主任可以查看每学年每学科 平均,最高,最低成绩
     * @param entity
     * @return
     */
    @ApiOperation(value="教导主任查询")
    @RequestMapping("/findTermSubGradeForDirector")
    public ResponsePageEntity findTermSubGradeForDirector(@RequestBody TermSubGradeForDirectorRequest entity){
        log.info("教导主任查看每学科,学年的成绩接口入参:{}", JSONObject.toJSONString(entity));
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        try {
            responsePageEntity = demoService.findTermSubGradeForDirector(entity);
            responsePageEntity.setPageIndex(entity.getPage());
            responsePageEntity.setPageSize(entity.getPage_size());
            responsePageEntity.setCode(SUCCESS);
            responsePageEntity.setMsg(SUCCESS);
        }catch (Exception e){
            responsePageEntity.setCode(FAIL);
            responsePageEntity.setMsg(FAIL);
            log.error("教导主任查看每学科,学年的成绩接口业务层出现异常，异常信息为{}",e);
        }
        return responsePageEntity;
    }


    /**
     * 教务处主任可以查看教师-学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    @ApiOperation(value="教导主任-老师查询")
    @RequestMapping("/findTeaSubGradeForDirector")
    public ResponsePageEntity findTeaSubGradeForDirector(@RequestBody TeaSubGradeForDirectorRequest entity){
        log.info("教导主任查看教师-学科的成绩接口入参:{}", JSONObject.toJSONString(entity));
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        try {
            responsePageEntity = demoService.findTeaSubGradeForDirector(entity);
            responsePageEntity.setPageIndex(entity.getPage());
            responsePageEntity.setPageSize(entity.getPage_size());
            responsePageEntity.setCode(SUCCESS);
            responsePageEntity.setMsg(SUCCESS);
        }catch (Exception e){
            responsePageEntity.setCode(FAIL);
            responsePageEntity.setMsg(FAIL);
            log.error("教导主任查看教师-学科的成绩接口业务层出现异常，异常信息为{}",e);
        }
        return responsePageEntity;
    }


    /**
     * 教师关联学科，可以查询教师本人每学年，学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    @ApiOperation(value="教师查询")
    @RequestMapping("/findTermSubGradeForTea")
    public ResponsePageEntity findTermSubGradeForTea(@RequestBody TermSubGradeForTeaRequest entity){
        log.info("查询教师本人每学年，学的成绩接口入参:{}", JSONObject.toJSONString(entity));
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        try {
            responsePageEntity = demoService.findTermSubGradeForTea(entity);
            responsePageEntity.setPageIndex(entity.getPage());
            responsePageEntity.setPageSize(entity.getPage_size());
            responsePageEntity.setCode(SUCCESS);
            responsePageEntity.setMsg(SUCCESS);
        }catch (Exception e){
            responsePageEntity.setCode(FAIL);
            responsePageEntity.setMsg(FAIL);
            log.error("查询教师本人每学年，学的成绩接口业务层出现异常，异常信息为{}",e);
        }
        return responsePageEntity;
    }


    /**
     * 学生可以查询本人每学年各学科成绩
     * @param entity
     * @return
     */
    @ApiOperation(value="学生查询")
    @RequestMapping("/findTermSubGradeForStu")
    public ResponsePageEntity findTermSubGradeForStu(@RequestBody TermSubGradeForStuRequest entity){
        log.info("学生可以查询本人每学年各学科成绩接口入参:{}", JSONObject.toJSONString(entity));
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        try {
            responsePageEntity = demoService.findTermSubGradeForStu(entity);
            responsePageEntity.setPageIndex(entity.getPage());
            responsePageEntity.setPageSize(entity.getPage_size());
            responsePageEntity.setCode(SUCCESS);
            responsePageEntity.setMsg(SUCCESS);
        }catch (Exception e){
            responsePageEntity.setCode(FAIL);
            responsePageEntity.setMsg(FAIL);
            log.error("学生可以查询本人每学年各学科成绩接口业务层出现异常，异常信息为{}",e);
        }
        return responsePageEntity;
    }


}
