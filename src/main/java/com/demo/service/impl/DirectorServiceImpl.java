package com.demo.service.impl;

import com.demo.dao.DirectorDao;
import com.demo.domain.*;
import com.demo.service.DirectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class DirectorServiceImpl implements DirectorService {


    @Autowired
    private DirectorDao directorDao;


    private static final Logger LOGGER = LoggerFactory.getLogger(DirectorServiceImpl.class);



    /**
     * 教导主任查询每学年,每学科成绩
     * @param entity
     * @return
     */
    public List<TermSubGradeForDirectorResp> findTermSubGradeForDirector(TermSubGradeForDirectorQuery entity) {
        List<TermSubGradeForDirectorResp> returnList = directorDao.findTermSubGradeForDirector(entity);
        return returnList;
    }



    /**
     * 教务处主任可以查看教师-学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    public List<TeaSubGradeForDirectorResp> findTeaSubGradeForDirector(TeaSubGradeForDirectorQuery entity) {
        List<TeaSubGradeForDirectorResp> returnList = directorDao.findTeaSubGradeForDirector(entity);
        return returnList;
    }

    /**
     * 教导主任查看每学科,学年的成绩数量
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Integer sumTermSubGradeForDirector(TermSubGradeForDirectorQuery entity){
        Integer returnCount = directorDao.sumTermSubGradeForDirector(entity);
        return returnCount;
    }


    /**
     * 教导主任查看每学科,学年的成绩数量
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Integer sumTeaSubGradeForDirector(TeaSubGradeForDirectorQuery entity) {
        Integer returnCount = directorDao.sumTeaSubGradeForDirector(entity);
        return returnCount;
    }
}
