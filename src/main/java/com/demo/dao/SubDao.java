package com.demo.dao;

import com.demo.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubDao extends JpaRepository<Subject, Long> {

}
