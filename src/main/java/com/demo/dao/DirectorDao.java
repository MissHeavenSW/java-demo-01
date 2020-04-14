package com.demo.dao;
import com.demo.domain.TeaSubGradeForDirectorQuery;
import com.demo.domain.TeaSubGradeForDirectorResp;
import com.demo.domain.TermSubGradeForDirectorQuery;
import com.demo.domain.TermSubGradeForDirectorResp;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DirectorDao  {


    /**
     * 教导主任查询成绩
     * @param entity
     * @return
     */
    List<TermSubGradeForDirectorResp> findTermSubGradeForDirector(TermSubGradeForDirectorQuery entity);

    /**
     * 教导主任查询老师成绩
     * @param entity
     * @return
     */
    List<TeaSubGradeForDirectorResp> findTeaSubGradeForDirector(TeaSubGradeForDirectorQuery entity);

    /**
     * 教导主任查询成绩数量
     * @param entity
     * @return
     */
    Integer sumTermSubGradeForDirector(TermSubGradeForDirectorQuery entity);


    /**
     * 教导主任查询老师成绩数量
     * @param entity
     * @return
     */
    Integer sumTeaSubGradeForDirector(TeaSubGradeForDirectorQuery entity);
}
