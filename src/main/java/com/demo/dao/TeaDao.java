package com.demo.dao;

import com.demo.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeaDao extends JpaRepository<Teacher, Long> {

}
