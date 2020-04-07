package com.demo.dao;


import com.demo.domain.Subject;
import com.demo.domain.TeaSubGradeForDirectorResponse;
import com.demo.domain.TermSubGradeForDirectorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseDao extends JpaRepository<Subject, Long> {

    /**
     * 主任查询每学年每学科平均成绩,最高成绩,和最差成绩
     * @param pageable
     * @return
     */
    @Query(value = "select new com.demo.domain.TermSubGradeForDirectorResponse( a.term as term ,a.sub_id as sub_id,b.sub_name as sub_name,AVG(a.sub_score) as avg_score ,MAX(a.sub_score) as max_score ,MIN(a.sub_score)as  min_score) FROM Score a" +
            " left join Subject b on a.sub_id = b.sub_id where a.term = ?1" +
            " GROUP BY a.sub_id ,a.term  " ,
    countQuery = "select count(a.term) from Score a left join Subject b on a.sub_id = b.sub_id where a.term = ?1 GROUP BY a.sub_id ,a.term ")
    Page<TermSubGradeForDirectorResponse> findTermSubGradeForDirector(@Param("term") Integer term, Pageable pageable);



    /**
     * 主任查询教师-学科 平均分,最高分,最低分
     * @return
     */
    @Query(value = "select new com.demo.domain.TeaSubGradeForDirectorResponse(a.tea_id as tea_id, a.tea_name as tea_name, b.sub_id as sub_id, avg(c.sub_score) as  avg_score,MAX(c.sub_score) as max_score,MIN(c.sub_score) as  min_score) " +
            "from Teacher a " +
            "LEFT JOIN TeacherSub b on a.tea_id = b.tea_id " +
            " LEFT JOIN Score c on c.sub_id  = b.sub_id where a.tea_id = ?1 " +
            "GROUP BY a.tea_id,c.sub_id  ",
    countQuery = "select count(a.tea_id) from Teacher a LEFT JOIN TeacherSub b on a.tea_id = b.tea_id LEFT JOIN Score c on c.sub_id  = b.sub_id where a.tea_id = ?1 GROUP BY a.tea_id,c.sub_id " )
    Page<TeaSubGradeForDirectorResponse> findTeaSubGradeForDirector(@Param("teacher_id") Integer  teacher_id, Pageable pageable);


}
