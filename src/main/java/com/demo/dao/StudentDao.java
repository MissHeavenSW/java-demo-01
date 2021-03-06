package com.demo.dao;
import com.demo.domain.TermSubGradeForStuQuery;
import com.demo.domain.TermSubGradeForStuResp;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentDao  {


    /**
     * 学生查询成绩
     * @param entity
     * @return
     */
    List<TermSubGradeForStuResp> findTermSubGradeForStu(TermSubGradeForStuQuery entity);

    /**
     * 学生查询成绩数量
     * @param entity
     * @return
     */
    Integer sumTermSubGradeForStu(TermSubGradeForStuQuery entity);
}
