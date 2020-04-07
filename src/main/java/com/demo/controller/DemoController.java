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
public class DemoController extends BaseController{

    @Autowired
    private DemoService demoService;

    /**
     * 教务处主任可以查看每学年每学科 平均,最高,最低成绩
     * @param entity
     * @return
     */
    @ApiOperation(value="教导主任查询")
    @RequestMapping("/getTermSubGradeForDirector")
    public PageMessage getTermSubGradeForDirector(@RequestBody RequestPageEntity<TermSubGradeForDirectorRequest> entity){
        log.info("教导主任查看每学科,学年的成绩接口入参:{}", JSONObject.toJSONString(entity));
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        try {
            responsePageEntity = demoService.selectTermSubGradeForDirector(entity);
        }catch (Exception e){
            responsePageEntity.setResultCode(ResponseEnum.FAIL.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.FAIL.getMessage());
            log.error("教导主任查看每学科,学年的成绩接口业务层出现异常，异常信息为{}",e);
        }
        return convert(entity.getHeader(), responsePageEntity);
    }


    /**
     * 教务处主任可以查看教师-学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    @ApiOperation(value="教导主任-老师查询")
    @RequestMapping("/getTeaSubGradeForDirector")
    public PageMessage getTeaSubGradeForDirector(@RequestBody RequestPageEntity<TeaSubGradeForDirectorRequest> entity){
        log.info("教导主任查看教师-学科的成绩接口入参:{}", JSONObject.toJSONString(entity));
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        try {
            responsePageEntity = demoService.selectTeaSubGradeForDirector(entity);
        }catch (Exception e){
            responsePageEntity.setResultCode(ResponseEnum.FAIL.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.FAIL.getMessage());
            log.error("教导主任查看教师-学科的成绩接口业务层出现异常，异常信息为{}",e);
        }
        return convert(entity.getHeader(), responsePageEntity);
    }


    /**
     * 教师关联学科，可以查询教师本人每学年，学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    @ApiOperation(value="教师查询")
    @RequestMapping("/getTermSubGradeForTea")
    public PageMessage getTermSubGradeForTea(@RequestBody RequestPageEntity<TermSubGradeForTeaRequest> entity){
        log.info("查询教师本人每学年，学的成绩接口入参:{}", JSONObject.toJSONString(entity));
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        try {
            responsePageEntity = demoService.selectTermSubGradeForTea(entity);
        }catch (Exception e){
            responsePageEntity.setResultCode(ResponseEnum.FAIL.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.FAIL.getMessage());
            log.error("查询教师本人每学年，学的成绩接口业务层出现异常，异常信息为{}",e);
        }
        return convert(entity.getHeader(), responsePageEntity);
    }


    /**
     * 学生可以查询本人每学年各学科成绩
     * @param entity
     * @return
     */
    @ApiOperation(value="学生查询")
    @RequestMapping("/getTermSubGradeForStu")
    public PageMessage getTermSubGradeForStu(@RequestBody RequestPageEntity<TermSubGradeForStuRequest> entity){
        log.info("学生可以查询本人每学年各学科成绩接口入参:{}", JSONObject.toJSONString(entity));
        ResponsePageEntity responsePageEntity = new ResponsePageEntity();
        try {
            responsePageEntity = demoService.selectTermSubGradeForStu(entity);
        }catch (Exception e){
            responsePageEntity.setResultCode(ResponseEnum.FAIL.getCode());
            responsePageEntity.setResultDesc(ResponseEnum.FAIL.getMessage());
            log.error("学生可以查询本人每学年各学科成绩接口业务层出现异常，异常信息为{}",e);
        }
        return convert(entity.getHeader(), responsePageEntity);
    }


}
