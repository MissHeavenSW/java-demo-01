package com.demo.dao.impl;

import com.demo.dao.StudentDao;
import com.demo.domain.Score;
import com.demo.domain.TermSubGradeForStuQuery;
import com.demo.domain.TermSubGradeForStuResp;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager em;

    /**
     * 学生可以查询本人每学年各学科成绩
     * @param query
     * @return
     */
    public List<TermSubGradeForStuResp> findTermSubGradeForStu(TermSubGradeForStuQuery query){
        List<TermSubGradeForStuResp> returnList = new ArrayList<>();
        //使用criteria写法
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TermSubGradeForStuResp> criteriaQuery = criteriaBuilder.createQuery(TermSubGradeForStuResp.class);
        Root<Score> scoreRoot = criteriaQuery.from(Score.class);
        criteriaQuery.multiselect(
                scoreRoot.get("stuId").as(Integer.class).alias("stuId"),
                scoreRoot.get("sub_score").as(Double.class).alias("subScore"),
                scoreRoot.get("term").as(Integer.class).alias("term"),
                scoreRoot.get("subId").as(Integer.class).alias("subId")

        );
        Predicate condition = criteriaBuilder.equal(scoreRoot.get("stuId"),query.getStuId());
        List scoreRootList = new ArrayList();
        scoreRootList.add(scoreRoot.get("stuId"));
        scoreRootList.add(scoreRoot.get("term"));
        scoreRootList.add(scoreRoot.get("subId"));
        criteriaQuery.groupBy(scoreRootList);
        criteriaQuery.where(condition);
        TypedQuery<TermSubGradeForStuResp> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setFirstResult(query.getPage());
        typedQuery.setMaxResults(query.getPageSize());
        List<TermSubGradeForStuResp> result = typedQuery.getResultList();
        if(CollectionUtils.isEmpty(result)){
            return returnList;
        }
        return result;
    }


    /**
     * 学生可以查询本人每学年各学科成绩数量
     * @param query
     * @return
     */
    @Override
    public Integer sumTermSubGradeForStu(TermSubGradeForStuQuery query){
        List<TermSubGradeForStuResp> returnList = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TermSubGradeForStuResp> criteriaQuery = criteriaBuilder.createQuery(TermSubGradeForStuResp.class);
        Root<Score> scoreRoot = criteriaQuery.from(Score.class);
        criteriaQuery.multiselect(
                scoreRoot.get("stuId").as(Integer.class).alias("stuId"),
                scoreRoot.get("subScore").as(Double.class).alias("subScore"),
                scoreRoot.get("term").as(Integer.class).alias("term"),
                scoreRoot.get("subId").as(Integer.class).alias("subId")

        );
        Predicate condition = criteriaBuilder.equal(scoreRoot.get("stuId"),query.getStuId());
        List scoreRootList = new ArrayList();
        scoreRootList.add(scoreRoot.get("stuId"));
        scoreRootList.add(scoreRoot.get("term"));
        scoreRootList.add(scoreRoot.get("subId"));
        criteriaQuery.groupBy(scoreRootList);
        criteriaQuery.where(condition);
        TypedQuery<TermSubGradeForStuResp> typedQuery = em.createQuery(criteriaQuery);
        List<TermSubGradeForStuResp> result = typedQuery.getResultList();
        if(CollectionUtils.isEmpty(result)){
            return returnList.size();
        }
        return result.size();

    }
}
