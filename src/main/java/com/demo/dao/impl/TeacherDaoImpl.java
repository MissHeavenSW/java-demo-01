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
     * @param entity
     * @return
     */
    public List<TermSubGradeForTeaResp> findTermSubGradeForTea(TermSubGradeForTeaQuery entity){
        List<TermSubGradeForTeaResp> returnList = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TermSubGradeForTeaResp> criteriaQuery = criteriaBuilder.createQuery(TermSubGradeForTeaResp.class);
        Root<TeacherSub> scoreRoot = criteriaQuery.from(TeacherSub.class);
        Join<TeacherSub, Score> relation = scoreRoot.join("scoreList", JoinType.LEFT);
        criteriaQuery.multiselect(
                scoreRoot.get("tea_id").as(Integer.class).alias("stuId"),
                scoreRoot.get("sub_id").as(Integer.class).alias("subId"),
                relation.get("term").as(Integer.class).alias("term"),
                criteriaBuilder.avg(relation.get("sub_score").as(Double.class)),
                criteriaBuilder.max(relation.get("sub_score").as(Double.class)),
                criteriaBuilder.min(relation.get("sub_score").as(Double.class))

        );
        Predicate condition = criteriaBuilder.equal(scoreRoot.get("tea_id"),entity.getTeacherId());
        List scoreRootList = new ArrayList();
        scoreRootList.add(scoreRoot.get("tea_id"));
        scoreRootList.add(relation.get("term"));
        scoreRootList.add(scoreRoot.get("sub_id"));
        criteriaQuery.groupBy(scoreRootList);
        criteriaQuery.where(condition);
        TypedQuery<TermSubGradeForTeaResp> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setFirstResult(entity.getPage());
        typedQuery.setMaxResults(entity.getPageSize());
        List<TermSubGradeForTeaResp> result = typedQuery.getResultList();
        if(CollectionUtils.isEmpty(result)){
            return returnList;
        }
        return result;
    }

    /**
     * 查询教师本人每学年，学的成绩数量
     * @param entity
     * @return
     */
    @Override
    public Integer sumTermSubGradeForTea(TermSubGradeForTeaQuery entity) {
        List<TermSubGradeForTeaResp> returnList = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TermSubGradeForTeaResp> criteriaQuery = criteriaBuilder.createQuery(TermSubGradeForTeaResp.class);
        Root<TeacherSub> scoreRoot = criteriaQuery.from(TeacherSub.class);
        Join<TeacherSub, Score> relation = scoreRoot.join("scoreList", JoinType.LEFT);
        criteriaQuery.multiselect(
                scoreRoot.get("tea_id").as(Integer.class).alias("stuId"),
                scoreRoot.get("sub_id").as(Integer.class).alias("subId"),
                relation.get("term").as(Integer.class).alias("term"),
                criteriaBuilder.avg(relation.get("sub_score").as(Double.class)),
                criteriaBuilder.max(relation.get("sub_score").as(Double.class)),
                criteriaBuilder.min(relation.get("sub_score").as(Double.class))

        );
        Predicate condition = criteriaBuilder.equal(scoreRoot.get("tea_id"),entity.getTeacherId());
        List scoreRootList = new ArrayList();
        scoreRootList.add(scoreRoot.get("tea_id"));
        scoreRootList.add(relation.get("term"));
        scoreRootList.add(scoreRoot.get("sub_id"));
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