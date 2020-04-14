package com.demo.controller;

import com.demo.domain.*;
import com.demo.service.DirectorService;
import com.demo.service.StudentService;
import com.demo.service.TeacherService;
import com.demo.util.CommonPage;
import com.nhsoft.provider.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "成绩查询")
public class DemoController{

    @Autowired
    private DirectorService directorService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    /**
     * 教务处主任可以查看每学年每学科 平均,最高,最低成绩
     * @param entity
     * @return
     */
    @ApiOperation(value="教导主任查询")
    @RequestMapping("/nhsoft.demo.termSubGradeForDirector.find")
    public Response findTermSubGradeForDirector(@RequestBody TermSubGradeForDirectorQuery entity){
        CommonPage<TermSubGradeForDirectorResp> page = new CommonPage<>();
        List<TermSubGradeForDirectorResp> returnList = directorService.findTermSubGradeForDirector(entity);
        Integer returnCount = directorService.sumTermSubGradeForDirector(entity);
        if (!CollectionUtils.isEmpty(returnList)&&returnCount!=null) {
             page = CommonPage.restPage(returnList, entity.getPage(), entity.getPageSize(), returnCount);
        }
        return Response.data(page);

    }


    /**
     * 教务处主任可以查看教师-学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    @ApiOperation(value="教导主任-老师查询")
    @RequestMapping("/nhsoft.demo.teaSubGradeForDirector.find")
    public Response findTeaSubGradeForDirector(@RequestBody TeaSubGradeForDirectorQuery entity){
        CommonPage<TeaSubGradeForDirectorResp> page = new CommonPage<>();
        List<TeaSubGradeForDirectorResp> returnList = directorService.findTeaSubGradeForDirector(entity);
        Integer returnCount = directorService.sumTeaSubGradeForDirector(entity);
        if (!CollectionUtils.isEmpty(returnList)&&returnCount!=null) {
            page = CommonPage.restPage(returnList, entity.getPage(), entity.getPageSize(), returnCount);
        }
        return Response.data(page);
    }


    /**
     * 教师关联学科，可以查询教师本人每学年，学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    @ApiOperation(value="教师查询")
    @RequestMapping("/nhsoft.demo.termSubGradeForTea.find")
    public Response findTermSubGradeForTea(@RequestBody TermSubGradeForTeaQuery entity){
        CommonPage<TermSubGradeForTeaResp> page = new CommonPage<>();
        List<TermSubGradeForTeaResp> returnList = teacherService.findTermSubGradeForTea(entity);
        Integer returnCount = teacherService.sumTermSubGradeForTea(entity);
        if (!CollectionUtils.isEmpty(returnList)&&returnCount!=null) {
            page = CommonPage.restPage(returnList, entity.getPage(), entity.getPageSize(), returnCount);
        }
        return Response.data(page);
    }


    /**
     * 学生可以查询本人每学年各学科成绩
     * @param entity
     * @return
     */
    @ApiOperation(value="学生查询")
    @RequestMapping("/nhsoft.demo.TermSubGradeForStu.find")
    public Response findTermSubGradeForStu(@RequestBody TermSubGradeForStuQuery entity){
        CommonPage<TermSubGradeForStuResp> page = new CommonPage<>();
        List<TermSubGradeForStuResp> returnList = studentService.findTermSubGradeForStu(entity);
        Integer returnCount = studentService.sumTermSubGradeForStu(entity);
        if (!CollectionUtils.isEmpty(returnList)&&returnCount!=null) {
            page = CommonPage.restPage(returnList, entity.getPage(), entity.getPageSize(), returnCount);
        }
        return Response.data(page);
    }

}
