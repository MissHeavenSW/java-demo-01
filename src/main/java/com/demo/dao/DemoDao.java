package com.demo.dao;


import com.demo.domain.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DemoDao extends JpaRepository<Score, Long> {

    /**
     * 主任查询每学年每学科平均成绩,最高成绩,和最差成绩
     * @param pageable
     * @return
     */
    @Query(value = "select a.term term ,a.sub_id subId,b.sub_name subName,AVG(a.sub_score) avgScore ,MAX(a.sub_score) max ,MIN(a.sub_score) min FROM stu_score a\n" +
            "left join sub b on a.sub_id = b.sub_id" +
            " GROUP BY a.sub_id ,a.term \n#pageable\n " ,nativeQuery = true)
    Page<Map> selectTermSubGradeForDirector(Pageable pageable);


    /**
     * 主任查询教师-学科 平均分,最高分,最低分
     * @return
     */
    @Query(value = "select a.tea_id teaId, a.tea_name teaName, b.sub_id subId, avg(c.sub_score) avgScore,MAX(c.sub_score) maxScore,MIN(c.sub_score) minScore\n" +
            "from tea a\n" +
            "LEFT JOIN tea_sub b on a.tea_id = b.tea_id\n" +
            "LEFT JOIN stu_score c on c.sub_id  = b.sub_id\n" +
            "GROUP BY a.tea_id,c.sub_id \n#pageable\n ",nativeQuery = true)
    Page<Map> selectTeaSubGradeForDirector(Pageable pageable);



    /**
     * 教师-学年-学科 平均分,最高分,最低分
     * @return
     */
    @Query(value = "select a.tea_id teaId, b.term term ,a.sub_id subId, AVG(b.sub_score) avgScore,MAX(b.sub_score) maxScore,MIN(b.sub_score) minScore\n" +
            "from tea_sub a \n" +
            "LEFT JOIN stu_score b on a.sub_id = b.sub_id\n" +
            "GROUP BY a.tea_id , b.term,  b.sub_id \n#pageable\n ",nativeQuery = true)
    Page<Map> selectTermSubGradeForTea(Pageable pageable);



    /**
     * 查询学生本人每学年每学科成绩
     * @return
     */
    @Query(value = "select a.stu_id as stuId, a.sub_score as subScore, a.term as term,a.sub_id as subId from stu_score as a GROUP BY a.stu_id,a.term,a.sub_id \n#pageable\n ",
            countQuery = "select count(a.stu_id) from stu_score as a GROUP BY a.stu_id,a.term,a.sub_id ",nativeQuery = true)
    Page<Map> selectTermSubGradeForStu(Pageable pageable);
}
