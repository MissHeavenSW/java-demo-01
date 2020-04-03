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
     * 主任查询每学年平均成绩,最高成绩,和最差成绩
     * @param term
     * @return
     */
    @Query(value = "select b.sub_name subName,AVG(a.sub_score) avgScore ,MAX(a.sub_score) max ,MIN(a.sub_score) min FROM stu_score a\n" +
            "left join sub b on a.sub_id = b.sub_id  where if(?1 !='',a.term=?1,1=1) " +
            "GROUP BY a.sub_id" ,nativeQuery = true)
    List<Map> selectGrade(String term);


    /**
     * 用于生成树的查询
     * @return
     */
    @Query(value = "select a.stu_id stuId,\n" +
            "a.sub_id subId,\n" +
            "a.sub_score subScore,\n" +
            "b.tea_id teaId from stu_score a \n" +
            "left join tea_sub b on a.sub_id = b.sub_id",nativeQuery = true)
    List<Map> selectTeacherSubGrade();


    /**
     * 教师-学科 平均分,最高分,最低分
     * @return
     */
    @Query(value = "select a.tea_id teaId, a.tea_name teaName, b.sub_id subId, avg(c.sub_score) avgScore,MAX(c.sub_score) maxScore,MIN(c.sub_score) minScore\n" +
            "from tea a\n" +
            "LEFT JOIN tea_sub b on a.tea_id = b.tea_id\n" +
            "LEFT JOIN stu_score c on c.sub_id  = b.sub_id\n" +
            "GROUP BY a.tea_id,c.sub_id",nativeQuery = true)
    List<Map> selectAllTeacherSubGrade();

    /**
     * 教师-学年-学科 平均分,最高分,最低分
     * @return
     */
    @Query(value = "select b.tea_id teaId, a.term term ,a.sub_id subId, AVG(a.sub_score) avgScore,MAX(a.sub_score) maxScore,MIN(a.sub_score) minScore\n" +
            "from stu_score a \n" +
            "LEFT JOIN tea_sub b on a.sub_id = b.sub_id\n" +
            "GROUP BY b.tea_id , a.term,  a.sub_id",nativeQuery = true)
    List<Map> selectTeaSubGrade();



    /**
     * 主任查询每学年平均成绩,最高成绩,和最差成绩
     * @param term
     * @return
     */
    @Query(value = "select b.sub_name subName,AVG(a.sub_score) avgScore ,MAX(a.sub_score) max ,MIN(a.sub_score) min FROM stu_score a\n" +
            "left join sub b on a.sub_id = b.sub_id  where if(?1 !='',a.term=?1,1=1) " +
            "GROUP BY a.sub_id \n#pageable\n " ,nativeQuery = true)
    Page<Map> selectGrade2(String term , Pageable pageable);



    /**
     * 教师-学科 平均分,最高分,最低分
     * @return
     */
    @Query(value = "select a.tea_id teaId, a.tea_name teaName, b.sub_id subId, avg(c.sub_score) avgScore,MAX(c.sub_score) maxScore,MIN(c.sub_score) minScore\n" +
            "from tea a\n" +
            "LEFT JOIN tea_sub b on a.tea_id = b.tea_id\n" +
            "LEFT JOIN stu_score c on c.sub_id  = b.sub_id\n" +
            "GROUP BY a.tea_id,c.sub_id \n#pageable\n ",nativeQuery = true)
    Page<Map> selectAllTeacherSubGrade2(Pageable pageable);
}
