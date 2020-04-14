package com.demo.service.impl;

import com.demo.dao.StudentDao;
import com.demo.domain.*;
import com.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class StudentServiceImpl implements StudentService {


    @Autowired
    private StudentDao studentDao;


    /**
     * 学生可以查询本人每学年各学科成绩
     * @param entity
     * @return
     */
    public List<TermSubGradeForStuResp> findTermSubGradeForStu(TermSubGradeForStuQuery entity){
        List<TermSubGradeForStuResp> returnList = studentDao.findTermSubGradeForStu(entity);
        return returnList;
    }


    /**
     * 学生可以查询本人每学年各学科成绩数量
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Integer sumTermSubGradeForStu(TermSubGradeForStuQuery entity) {
       Integer returnCount = studentDao.sumTermSubGradeForStu(entity);
       return returnCount;
    }
}
