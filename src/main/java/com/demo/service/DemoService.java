package com.demo.service;


import com.alibaba.fastjson.JSONObject;
import com.demo.dao.DemoDao;
import com.demo.dao.SubDao;
import com.demo.dao.TeaDao;
import com.demo.domain.Request;
import com.demo.domain.Score;
import com.demo.domain.Subject;
import com.demo.domain.Teacher;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DemoService {

    @Autowired
    private DemoDao demoDao;

    @Autowired
    private TeaDao teaDao;

    @Autowired
    private SubDao subDao;

    /**
     * 没有分页的简单的查询
     * @param request
     * @return
     */
    public Map<String, Object> getGrade(Request request) {
        log.info("调用查询接口入参:"+ JSONObject.toJSONString(request));
        Map<String,Object> returnMap = new HashMap<>();
        //对参数进行判断
        if(!request.getPersonId().isEmpty()&&!request.getPersonId().equals("")){
            if(request.getPersonId().equals("1")){
                //是主任，查询每学年学科平均成绩
                List<Map> avgMapList = demoDao.selectGrade(request.getTerm());
                avgMapList.stream().forEach(o->{
                     String subName = (String) o.get("subName");
                    Double avgScore = (Double) o.get("avgScore");
                     Double max = (Double)o.get("max");
                    Double min = (Double)o.get("min");
                     log.info("学科"+subName+"成绩是:"+avgScore+"最高分数是:"+max+"最低分数是:"+min);
                     returnMap.put("avgMap",avgMapList);
                });

                //教导主任可以查看教师-学科成绩,就是每个教师的每门学科成绩的avg max min(树结构方便查看成绩)
                List<Map> teaSubGradeList = demoDao.selectTeacherSubGrade();
                List<Teacher> teacherList = teaDao.findAll();
                List<Subject> subjectList = subDao.findAll();
                //处理该集合获得每个老师的学科成绩
                if(!CollectionUtils.isEmpty(teacherList)&&!CollectionUtils.isEmpty(teaSubGradeList)&&!CollectionUtils.isEmpty(subjectList)){
                   Map hashMap = new HashMap();
                    for(int i = 0;i<teacherList.size();i++){
                       List<Map> teacherList2 = new ArrayList();
                      for(int j = 0;j<teaSubGradeList.size();j++){
                          if(teacherList.get(i).getTeaId()==(int)teaSubGradeList.get(j).get("teaId")){
                              teacherList2.add(teaSubGradeList.get(j));
                          }
                      }
                       //尝试把每个老师的成绩中的学科区分出来
                        Map hashMap2 = new HashMap();
                        for(int k = 0;k<teacherList2.size();k++){
                           List<Map> teacherList3 = new ArrayList<>();
                          for(int m = 0;m<subjectList.size();m++){
                               if(String.valueOf(teacherList2.get(k).get("subId")).equals(String.valueOf(subjectList.get(m).getSubId()))){
                                   teacherList3.add(teacherList2.get(k));
                                   hashMap2.put(String.valueOf(teacherList2.get(k).get("stuId")),teacherList3);
                               }
                           }
                        }
                        hashMap.put(String.valueOf(teacherList.get(i).getTeaId()),hashMap2);
                   }
                    returnMap.put("teaSubTree",hashMap); //老师-学科-成绩(学生)树
                }


                //直接数据库查询最高成绩最低成绩,平均成绩出来
                List<Map> allList  = demoDao.selectAllTeacherSubGrade();
                if(!CollectionUtils.isEmpty(allList)){
                    returnMap.put("teaSub",allList);
                }

            }
        }


        //非主任可以查看的分数
        List<Map> teaSubGradeList = demoDao.selectTeaSubGrade();
        if(!CollectionUtils.isEmpty(teaSubGradeList)){
            returnMap.put("teaSub2",teaSubGradeList);
        }

        //学生可以查询本人每年的各学科的成绩
        if(request.getStuId()!=null){
            List<Map> stuTermSubGradeList = demoDao.selectStuTermSubGrade(request.getStuId());
            if(!CollectionUtils.isEmpty(stuTermSubGradeList)){
                returnMap.put("stuTermSub",stuTermSubGradeList);
            }
        }

        log.info("查询接口的出参:"+JSONObject.toJSONString(returnMap));
        return returnMap;
    }


    /**
     * 使用了jpa的分页
     * @param request
     * @return
     */
    public Map<String, Object> getGrade2(Request request){
        log.info("调用分页查询接口入参:"+ JSONObject.toJSONString(request));
        Map<String,Object> returnMap = new HashMap<>();
        //对参数进行判断
        if(!request.getPersonId().isEmpty()&&!request.getPersonId().equals("")){
            if(request.getPersonId().equals("1")){
                //是主任，查询每学年学科平均成绩
                Sort sort = Sort.by(Sort.Direction.DESC,"sub_id");
                Pageable pageable = PageRequest.of(0,10,sort); //暂时写死,可以传参数的其实
                Page<Map> avgMapList = demoDao.selectGrade2(request.getTerm(),pageable);
                avgMapList.stream().forEach(o->{
                    String subName = (String) o.get("subName");
                    Double avgScore = (Double) o.get("avgScore");
                    Double max = (Double)o.get("max");
                    Double min = (Double)o.get("min");
                    log.info("学科"+subName+"成绩是:"+avgScore+"最高分数是:"+max+"最低分数是:"+min);
                    returnMap.put("avgMap",avgMapList);
                });

                //直接数据库查询最高成绩最低成绩,平均成绩出来
                Sort sort2 = Sort.by(Sort.Direction.DESC,"tea_id");
                Pageable pageable2 = PageRequest.of(0,10,sort2); //暂时写死,可以传参数的其实
                Page<Map> allList  = demoDao.selectAllTeacherSubGrade2(pageable2);
                if(!CollectionUtils.isEmpty(allList.getContent())){
                    returnMap.put("teaSub",allList.getContent());
                }

            }
        }


        //非主任可以查看的分数
        List<Map> teaSubGradeList = demoDao.selectTeaSubGrade();
        if(!CollectionUtils.isEmpty(teaSubGradeList)){
            returnMap.put("teaSub2",teaSubGradeList);
        }

        //学生可以查询本人每年的各学科的成绩
        if(request.getStuId()!=null){
            Sort sort3 = Sort.by(Sort.Direction.DESC,"stu_id");
            Pageable pageable3 = PageRequest.of(0,10,sort3); //暂时写死,可以传参数的其实
            Page<Map> stuTermSubGradeList = demoDao.selectStuTermSubGrade2(request.getStuId(),pageable3);
            if(!CollectionUtils.isEmpty(stuTermSubGradeList.getContent())){
                returnMap.put("stuTermSub",stuTermSubGradeList);
            }
        }

        log.info("分页查询接口的出参:"+JSONObject.toJSONString(returnMap));
        return returnMap;
    }
}
