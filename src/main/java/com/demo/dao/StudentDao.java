package com.demo.dao;


import com.demo.domain.Score;
import com.demo.domain.Student;
import com.demo.domain.TermSubGradeForStuResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {

    @Query(value = "select new com.demo.domain.TermSubGradeForStuResponse(a.stu_id , a.sub_score, a.term ,a.sub_id) from Score as a where a.stu_id = ?1 GROUP BY a.stu_id,a.term,a.sub_id ")
    Page<TermSubGradeForStuResponse> findTermSubGradeForStu(@Param("studentId") Integer studentId,Pageable pageable);
}
