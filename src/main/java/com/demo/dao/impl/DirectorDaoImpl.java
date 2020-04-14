package com.demo.dao.impl;

import com.demo.dao.DirectorDao;
import com.demo.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DirectorDaoImpl implements DirectorDao {


    @PersistenceContext
    private EntityManager em;

    /**
     * 教导主任查询每学年,每学科成绩
     * @param entity
     * @return
     */
    public List<TermSubGradeForDirectorResp> findTermSubGradeForDirector(TermSubGradeForDirectorQuery entity) {
        List<TermSubGradeForDirectorResp> returnList = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TermSubGradeForDirectorResp> criteriaQuery = criteriaBuilder.createQuery(TermSubGradeForDirectorResp.class);
        Root<Score> scoreRoot = criteriaQuery.from(Score.class);
        Join<Score, Subject> relation = scoreRoot.join("subList", JoinType.LEFT);
        criteriaQuery.multiselect(
                scoreRoot.get("term").as(Integer.class).alias("term"),
                scoreRoot.get("subId").as(Integer.class).alias("subId"),
                relation.get("subName").as(String.class).alias("subName"),
                criteriaBuilder.avg(scoreRoot.get("subScore").as(Double.class)),
                criteriaBuilder.max(scoreRoot.get("subScore").as(Double.class)),
                criteriaBuilder.min(scoreRoot.get("subScore").as(Double.class))
        );
        Predicate condition = criteriaBuilder.equal(scoreRoot.get("term"),entity.getTerm());
        List scoreRootList = new ArrayList();
        scoreRootList.add(scoreRoot.get("term"));
        scoreRootList.add(scoreRoot.get("subId"));
        criteriaQuery.groupBy(scoreRootList);
        criteriaQuery.where(condition);
        TypedQuery<TermSubGradeForDirectorResp> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setFirstResult(entity.getPage());
        typedQuery.setMaxResults(entity.getPageSize());
        List<TermSubGradeForDirectorResp> result = typedQuery.getResultList();
        if(CollectionUtils.isEmpty(result)){
            return returnList;
        }
        return result;
    }



    /**
     * 教务处主任可以查看教师-学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    public List<TeaSubGradeForDirectorResp> findTeaSubGradeForDirector(TeaSubGradeForDirectorQuery entity){
        List<TeaSubGradeForDirectorResp> returnList = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TeaSubGradeForDirectorResp> criteriaQuery = criteriaBuilder.createQuery(TeaSubGradeForDirectorResp.class);
        Root<Teacher> scoreRoot = criteriaQuery.from(Teacher.class);
        Join<Teacher, TeacherSub> relation1 = scoreRoot.join("teacherSubList", JoinType.LEFT);
        Join<TeacherSub, Score> relation2 = relation1.join("scoreList", JoinType.LEFT);
        criteriaQuery.multiselect(
                scoreRoot.get("teaId").as(Integer.class).alias("stuId"),
                scoreRoot.get("teaName").as(String.class).alias("teaName"),
                relation1.get("subId").as(Integer.class).alias("subId"),
                criteriaBuilder.avg(relation2.get("subScore").as(Double.class)),
                criteriaBuilder.max(relation2.get("subScore").as(Double.class)),
                criteriaBuilder.min(relation2.get("subScore").as(Double.class))
        );
        Predicate condition = criteriaBuilder.equal(scoreRoot.get("teaId"),entity.getTeacherId());
        List scoreRootList = new ArrayList();
        scoreRootList.add(scoreRoot.get("teaId"));
        scoreRootList.add(relation1.get("subId"));
        criteriaQuery.groupBy(scoreRootList);
        criteriaQuery.where(condition);
        TypedQuery<TeaSubGradeForDirectorResp> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setFirstResult(entity.getPage());
        typedQuery.setMaxResults(entity.getPageSize());
        List<TeaSubGradeForDirectorResp> result = typedQuery.getResultList();
        if(CollectionUtils.isEmpty(result)){
            return returnList;
        }
        return result;

    }

    /**
     * 教导主任查看每学科,学年的成绩数量
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Integer sumTermSubGradeForDirector(TermSubGradeForDirectorQuery entity) {
        List<TermSubGradeForDirectorResp> returnList = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TermSubGradeForDirectorResp> criteriaQuery = criteriaBuilder.createQuery(TermSubGradeForDirectorResp.class);
        Root<Score> scoreRoot = criteriaQuery.from(Score.class);
        Join<Score, Subject> relation = scoreRoot.join("subList", JoinType.LEFT);
        criteriaQuery.multiselect(
                scoreRoot.get("term").as(Integer.class).alias("term"),
                scoreRoot.get("subId").as(Integer.class).alias("subId"),
                relation.get("subName").as(String.class).alias("subName"),
                criteriaBuilder.avg(scoreRoot.get("subScore").as(Double.class)),
                criteriaBuilder.max(scoreRoot.get("subScore").as(Double.class)),
                criteriaBuilder.min(scoreRoot.get("subScore").as(Double.class))

        );
        Predicate condition = criteriaBuilder.equal(scoreRoot.get("term"),entity.getTerm());
        List scoreRootList = new ArrayList();
        scoreRootList.add(scoreRoot.get("term"));
        scoreRootList.add(scoreRoot.get("subId"));
        criteriaQuery.groupBy(scoreRootList);
        criteriaQuery.where(condition);
        TypedQuery<TermSubGradeForDirectorResp> typedQuery = em.createQuery(criteriaQuery);
        List<TermSubGradeForDirectorResp> result = typedQuery.getResultList();
        if(CollectionUtils.isEmpty(result)){
            return returnList.size();
        }
        return result.size();
    }


    /**
     * 教导主任查看每学科,学年的成绩数量
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Integer sumTeaSubGradeForDirector(TeaSubGradeForDirectorQuery entity){
        List<TeaSubGradeForDirectorResp> returnList = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TeaSubGradeForDirectorResp> criteriaQuery = criteriaBuilder.createQuery(TeaSubGradeForDirectorResp.class);
        Root<Teacher> scoreRoot = criteriaQuery.from(Teacher.class);
        Join<Teacher, TeacherSub> relation1 = scoreRoot.join("teacherSubList", JoinType.LEFT);
        Join<TeacherSub, Score> relation2 = relation1.join("scoreList", JoinType.LEFT);
        criteriaQuery.multiselect(
                scoreRoot.get("teaId").as(Integer.class).alias("stuId"),
                scoreRoot.get("teaName").as(String.class).alias("teaName"),
                relation1.get("subId").as(Integer.class).alias("subId"),
                criteriaBuilder.avg(relation2.get("subScore").as(Double.class)),
                criteriaBuilder.max(relation2.get("subScore").as(Double.class)),
                criteriaBuilder.min(relation2.get("subScore").as(Double.class))
        );
        Predicate condition = criteriaBuilder.equal(scoreRoot.get("teaId"),entity.getTeacherId());
        List scoreRootList = new ArrayList();
        scoreRootList.add(scoreRoot.get("teaId"));
        scoreRootList.add(relation1.get("subId"));
        criteriaQuery.groupBy(scoreRootList);
        criteriaQuery.where(condition);
        TypedQuery<TeaSubGradeForDirectorResp> typedQuery = em.createQuery(criteriaQuery);
        List<TeaSubGradeForDirectorResp> result = typedQuery.getResultList();
        if(CollectionUtils.isEmpty(result)){
            return returnList.size();
        }
        return result.size();
    }
}
