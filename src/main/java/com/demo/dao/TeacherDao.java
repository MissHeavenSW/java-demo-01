package com.demo.dao;


import com.demo.domain.Score;
import com.demo.domain.Teacher;
import com.demo.domain.TermSubGradeForTeaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface TeacherDao extends JpaRepository<Teacher, Long> {


    /**
     * 教师-学年-学科 平均分,最高分,最低分
     * @return
     */
    @Query(value = "select new com.demo.domain.TermSubGradeForTeaResponse(a.tea_id as tea_id , b.term as term  ,a.sub_id as sub_id , AVG(b.sub_score) as avg_score ,MAX(b.sub_score) as max_score ,MIN(b.sub_score) as min_score )" +
            "from TeacherSub a " +
            "LEFT JOIN Score b on a.sub_id = b.sub_id where a.tea_id = ?1 " +
            "GROUP BY a.tea_id , b.term,  b.sub_id  ",
    countQuery = "select count (a.tea_id) from TeacherSub a LEFT JOIN Score b on a.sub_id = b.sub_id where a.tea_id = ?1 GROUP BY a.tea_id , b.term,  b.sub_id ")
    Page<TermSubGradeForTeaResponse> findTermSubGradeForTea(@Param("teacher_id") Integer teacher_id, Pageable pageable);

}
