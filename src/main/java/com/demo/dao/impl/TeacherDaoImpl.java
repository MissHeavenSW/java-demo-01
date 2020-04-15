package com.demo.dao.impl;


import com.demo.dao.TeacherDao;
import com.demo.domain.Score;
import com.demo.domain.TeacherSub;
import com.demo.domain.TermSubGradeForTeaQuery;
import com.demo.domain.TermSubGradeForTeaResp;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TeacherDaoImpl implements TeacherDao {

    @PersistenceContext
    private EntityManager em;


    /**
     * 查询教师本人每学年，学的成绩
     * @param query
     * @return
     */
    public List<TermSubGradeForTeaResp> findTermSubGradeForTea(TermSubGradeForTeaQuery query){
        List<TermSubGradeForTeaResp> returnList = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TermSubGradeForTeaResp> criteriaQuery = criteriaBuilder.createQuery(TermSubGradeForTeaResp.class);
        Root<TeacherSub> scoreRoot = criteriaQuery.from(TeacherSub.class);
        Join<TeacherSub, Score> relation = scoreRoot.join("scoreList", JoinType.LEFT);
        criteriaQuery.multiselect(
                scoreRoot.get("teaId").as(Integer.class).alias("stuId"),
                scoreRoot.get("subId").as(Integer.class).alias("subId"),
                relation.get("term").as(Integer.class).alias("term"),
                criteriaBuilder.avg(relation.get("subScore").as(Double.class)),
                criteriaBuilder.max(relation.get("subScore").as(Double.class)),
                criteriaBuilder.min(relation.get("subScore").as(Double.class))

        );
        Predicate condition = criteriaBuilder.equal(scoreRoot.get("teaId"),query.getTeacherId());
        List scoreRootList = new ArrayList();
        scoreRootList.add(scoreRoot.get("teaId"));
        scoreRootList.add(relation.get("term"));
        scoreRootList.add(scoreRoot.get("subId"));
        criteriaQuery.groupBy(scoreRootList);
        criteriaQuery.where(condition);
        TypedQuery<TermSubGradeForTeaResp> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setFirstResult(query.getPage());
        typedQuery.setMaxResults(query.getPageSize());
        List<TermSubGradeForTeaResp> result = typedQuery.getResultList();
        if(CollectionUtils.isEmpty(result)){
            return returnList;
        }
        return result;
    }

    /**
     * 查询教师本人每学年，学的成绩数量
     * @param query
     * @return
     */
    @Override
    public Integer sumTermSubGradeForTea(TermSubGradeForTeaQuery query) {
        List<TermSubGradeForTeaResp> returnList = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TermSubGradeForTeaResp> criteriaQuery = criteriaBuilder.createQuery(TermSubGradeForTeaResp.class);
        Root<TeacherSub> scoreRoot = criteriaQuery.from(TeacherSub.class);
        Join<TeacherSub, Score> relation = scoreRoot.join("scoreList", JoinType.LEFT);
        criteriaQuery.multiselect(
                scoreRoot.get("teaId").as(Integer.class).alias("stuId"),
                scoreRoot.get("subId").as(Integer.class).alias("subId"),
                relation.get("term").as(Integer.class).alias("term"),
                criteriaBuilder.avg(relation.get("subScore").as(Double.class)),
                criteriaBuilder.max(relation.get("subScore").as(Double.class)),
                criteriaBuilder.min(relation.get("subScore").as(Double.class))

        );
        Predicate condition = criteriaBuilder.equal(scoreRoot.get("teaId"),query.getTeacherId());
        List scoreRootList = new ArrayList();
        scoreRootList.add(scoreRoot.get("teaId"));
        scoreRootList.add(relation.get("term"));
        scoreRootList.add(scoreRoot.get("subId"));
        criteriaQuery.groupBy(scoreRootList);
        criteriaQuery.where(condition);
        TypedQuery<TermSubGradeForTeaResp> typedQuery = em.createQuery(criteriaQuery);
        List<TermSubGradeForTeaResp> result = typedQuery.getResultList();
        if(CollectionUtils.isEmpty(result)){
            return returnList.size();
        }
        return result.size();
    }
}
