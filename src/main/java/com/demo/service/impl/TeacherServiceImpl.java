package com.demo.service.impl;

import com.demo.dao.TeacherDao;
import com.demo.domain.*;
import com.demo.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;


    /**
     * 查询教师本人每学年，学的成绩
     * @param entity
     * @return
     */
    public List<TermSubGradeForTeaResp> findTermSubGradeForTea(TermSubGradeForTeaQuery entity){
        List<TermSubGradeForTeaResp> returnList =teacherDao.findTermSubGradeForTea(entity);
        return returnList;
    }

    /**
     * 查询教师本人每学年，学的成绩数量
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Integer sumTermSubGradeForTea(TermSubGradeForTeaQuery entity) {
       Integer returnCount = teacherDao.sumTermSubGradeForTea(entity);
       return returnCount;
    }
}
