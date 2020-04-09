package com.demo.service.impl;

import com.demo.domain.*;
import com.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DemoServiceImpl implements DemoService {


    @PersistenceContext
    private EntityManager em;


    /**
     * 教导主任查询每学年,每学科成绩
     * @param entity
     * @return
     */
    public List<TermSubGradeForDirectorResp> findTermSubGradeForDirector(TermSubGradeForDirectorQuery entity) throws Exception {
        List<TermSubGradeForDirectorResp> returnList = new ArrayList<>();
        //3,数据查询
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<TermSubGradeForDirectorResp> criteriaQuery = criteriaBuilder.createQuery(TermSubGradeForDirectorResp.class);
            Root<Score> scoreRoot = criteriaQuery.from(Score.class);
            Join<Score, Subject> relation = scoreRoot.join("subList", JoinType.LEFT);
            criteriaQuery.multiselect(
                    scoreRoot.get("term").as(Integer.class).alias("term"),
                    scoreRoot.get("sub_id").as(Integer.class).alias("subId"),
                    relation.get("sub_name").as(String.class).alias("subName"),
                    criteriaBuilder.avg(scoreRoot.get("sub_score").as(Double.class)),
                    criteriaBuilder.max(scoreRoot.get("sub_score").as(Double.class)),
                    criteriaBuilder.min(scoreRoot.get("sub_score").as(Double.class))

            );
            Predicate condition = criteriaBuilder.equal(scoreRoot.get("term"),entity.getTerm());
            List scoreRootList = new ArrayList();
            scoreRootList.add(scoreRoot.get("term"));
            scoreRootList.add(scoreRoot.get("sub_id"));
            criteriaQuery.groupBy(scoreRootList);
            criteriaQuery.where(condition);
            TypedQuery<TermSubGradeForDirectorResp> typedQuery = em.createQuery(criteriaQuery);
            typedQuery.setFirstResult(entity.getPage());
            typedQuery.setMaxResults(entity.getPage_size());
            List<TermSubGradeForDirectorResp> result = typedQuery.getResultList();
            if(CollectionUtils.isEmpty(result)){
                return returnList;
            }
            return result;
        }catch (Exception e){
            log.error("教导主任查看每学科,学年的成绩接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }



    /**
     * 教务处主任可以查看教师-学科平均成绩，最高分，最低分
     * @param entity
     * @return
     */
    public List<TeaSubGradeForDirectorResp> findTeaSubGradeForDirector(TeaSubGradeForDirectorQuery entity) throws Exception {
        List<TeaSubGradeForDirectorResp> returnList = new ArrayList<>();

        //2,数据查询
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<TeaSubGradeForDirectorResp> criteriaQuery = criteriaBuilder.createQuery(TeaSubGradeForDirectorResp.class);
            Root<Teacher> scoreRoot = criteriaQuery.from(Teacher.class);
            Join<Teacher, TeacherSub> relation1 = scoreRoot.join("teacherSubList", JoinType.LEFT);
            Join<TeacherSub, Score> relation2 = relation1.join("scoreList", JoinType.LEFT);
            criteriaQuery.multiselect(
                    scoreRoot.get("tea_id").as(Integer.class).alias("stuId"),
                    scoreRoot.get("tea_name").as(String.class).alias("teaName"),
                    relation1.get("sub_id").as(Integer.class).alias("subId"),
                    criteriaBuilder.avg(relation2.get("sub_score").as(Double.class)),
                    criteriaBuilder.max(relation2.get("sub_score").as(Double.class)),
                    criteriaBuilder.min(relation2.get("sub_score").as(Double.class))

            );
            Predicate condition = criteriaBuilder.equal(scoreRoot.get("tea_id"),entity.getTeacher_id());
            List scoreRootList = new ArrayList();
            scoreRootList.add(scoreRoot.get("tea_id"));
            scoreRootList.add(relation1.get("sub_id"));
            criteriaQuery.groupBy(scoreRootList);
            criteriaQuery.where(condition);
            TypedQuery<TeaSubGradeForDirectorResp> typedQuery = em.createQuery(criteriaQuery);
            typedQuery.setFirstResult(entity.getPage());
            typedQuery.setMaxResults(entity.getPage_size());
            List<TeaSubGradeForDirectorResp> result = typedQuery.getResultList();
            if(CollectionUtils.isEmpty(result)){
                return returnList;
            }
            return result;
        }catch (Exception e){
            log.error("教导主任查看每学科,学年的成绩接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }

    /**
     * 查询教师本人每学年，学的成绩
     * @param entity
     * @return
     */
    public List<TermSubGradeForTeaResp> findTermSubGradeForTea(TermSubGradeForTeaQuery entity) throws Exception{
        List<TermSubGradeForTeaResp> returnList = new ArrayList<>();
        //2,数据查询
        try {
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
            Predicate condition = criteriaBuilder.equal(scoreRoot.get("tea_id"),entity.getTeacher_id());
            List scoreRootList = new ArrayList();
            scoreRootList.add(scoreRoot.get("tea_id"));
            scoreRootList.add(relation.get("term"));
            scoreRootList.add(scoreRoot.get("sub_id"));
            criteriaQuery.groupBy(scoreRootList);
            criteriaQuery.where(condition);
            TypedQuery<TermSubGradeForTeaResp> typedQuery = em.createQuery(criteriaQuery);
            typedQuery.setFirstResult(entity.getPage());
            typedQuery.setMaxResults(entity.getPage_size());
            List<TermSubGradeForTeaResp> result = typedQuery.getResultList();
            if(CollectionUtils.isEmpty(result)){
                return returnList;
            }
            return result;
        }catch (Exception e){
            log.error("查询教师本人每学年，学的成绩接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }

    /**
     * 学生可以查询本人每学年各学科成绩
     * @param entity
     * @return
     */
    public List<TermSubGradeForStuResp> findTermSubGradeForStu(TermSubGradeForStuQuery entity) throws Exception{
        List<TermSubGradeForStuResp> returnList = new ArrayList<>();
        //3,数据查询
        try {
           //使用criteria写法
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<TermSubGradeForStuResp> criteriaQuery = criteriaBuilder.createQuery(TermSubGradeForStuResp.class);
            Root<Score> scoreRoot = criteriaQuery.from(Score.class);
            criteriaQuery.multiselect(
                    scoreRoot.get("stu_id").as(Integer.class).alias("stuId"),
                    scoreRoot.get("sub_score").as(Double.class).alias("subScore"),
                    scoreRoot.get("term").as(Integer.class).alias("term"),
                    scoreRoot.get("sub_id").as(Integer.class).alias("subId")

            );
            Predicate condition = criteriaBuilder.equal(scoreRoot.get("stu_id"),entity.getStu_id());
            List scoreRootList = new ArrayList();
            scoreRootList.add(scoreRoot.get("stu_id"));
            scoreRootList.add(scoreRoot.get("term"));
            scoreRootList.add(scoreRoot.get("sub_id"));
            criteriaQuery.groupBy(scoreRootList);
            criteriaQuery.where(condition);
            TypedQuery<TermSubGradeForStuResp> typedQuery = em.createQuery(criteriaQuery);
            typedQuery.setFirstResult(entity.getPage());
            typedQuery.setMaxResults(entity.getPage_size());
            List<TermSubGradeForStuResp> result = typedQuery.getResultList();
            if(CollectionUtils.isEmpty(result)){
                return returnList;
            }
            return result;
        }catch (Exception e){
            log.error("学生可以查询本人每学年各学科成绩接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }


    /**
     * 教导主任查看每学科,学年的成绩数量
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Integer sumTermSubGradeForDirector(TermSubGradeForDirectorQuery entity) throws Exception {
        List<TermSubGradeForDirectorResp> returnList = new ArrayList<>();
        //3,数据查询
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<TermSubGradeForDirectorResp> criteriaQuery = criteriaBuilder.createQuery(TermSubGradeForDirectorResp.class);
            Root<Score> scoreRoot = criteriaQuery.from(Score.class);
            Join<Score, Subject> relation = scoreRoot.join("subList", JoinType.LEFT);
            criteriaQuery.multiselect(
                    scoreRoot.get("term").as(Integer.class).alias("term"),
                    scoreRoot.get("sub_id").as(Integer.class).alias("subId"),
                    relation.get("sub_name").as(String.class).alias("subName"),
                    criteriaBuilder.avg(scoreRoot.get("sub_score").as(Double.class)),
                    criteriaBuilder.max(scoreRoot.get("sub_score").as(Double.class)),
                    criteriaBuilder.min(scoreRoot.get("sub_score").as(Double.class))

            );
            Predicate condition = criteriaBuilder.equal(scoreRoot.get("term"),entity.getTerm());
            List scoreRootList = new ArrayList();
            scoreRootList.add(scoreRoot.get("term"));
            scoreRootList.add(scoreRoot.get("sub_id"));
            criteriaQuery.groupBy(scoreRootList);
            criteriaQuery.where(condition);
            TypedQuery<TermSubGradeForDirectorResp> typedQuery = em.createQuery(criteriaQuery);
            List<TermSubGradeForDirectorResp> result = typedQuery.getResultList();
            if(CollectionUtils.isEmpty(result)){
                return returnList.size();
            }
            return result.size();
        }catch (Exception e){
            log.error("教导主任查看每学科,学年的成绩数量接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }


    /**
     * 教导主任查看每学科,学年的成绩数量
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Integer sumTeaSubGradeForDirector(TeaSubGradeForDirectorQuery entity) throws Exception {
        List<TeaSubGradeForDirectorResp> returnList = new ArrayList<>();

        //2,数据查询
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<TeaSubGradeForDirectorResp> criteriaQuery = criteriaBuilder.createQuery(TeaSubGradeForDirectorResp.class);
            Root<Teacher> scoreRoot = criteriaQuery.from(Teacher.class);
            Join<Teacher, TeacherSub> relation1 = scoreRoot.join("teacherSubList", JoinType.LEFT);
            Join<TeacherSub, Score> relation2 = relation1.join("scoreList", JoinType.LEFT);
            criteriaQuery.multiselect(
                    scoreRoot.get("tea_id").as(Integer.class).alias("stuId"),
                    scoreRoot.get("tea_name").as(String.class).alias("teaName"),
                    relation1.get("sub_id").as(Integer.class).alias("subId"),
                    criteriaBuilder.avg(relation2.get("sub_score").as(Double.class)),
                    criteriaBuilder.max(relation2.get("sub_score").as(Double.class)),
                    criteriaBuilder.min(relation2.get("sub_score").as(Double.class))

            );
            Predicate condition = criteriaBuilder.equal(scoreRoot.get("tea_id"),entity.getTeacher_id());
            List scoreRootList = new ArrayList();
            scoreRootList.add(scoreRoot.get("tea_id"));
            scoreRootList.add(relation1.get("sub_id"));
            criteriaQuery.groupBy(scoreRootList);
            criteriaQuery.where(condition);
            TypedQuery<TeaSubGradeForDirectorResp> typedQuery = em.createQuery(criteriaQuery);
            List<TeaSubGradeForDirectorResp> result = typedQuery.getResultList();
            if(CollectionUtils.isEmpty(result)){
                return returnList.size();
            }
            return result.size();
        }catch (Exception e){
            log.error("教导主任查看每学科,学年的成绩数量接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }

    /**
     * 查询教师本人每学年，学的成绩数量
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Integer sumTermSubGradeForTea(TermSubGradeForTeaQuery entity) throws Exception {
        List<TermSubGradeForTeaResp> returnList = new ArrayList<>();
        //2,数据查询
        try {
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
            Predicate condition = criteriaBuilder.equal(scoreRoot.get("tea_id"),entity.getTeacher_id());
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
        }catch (Exception e){
            log.error("查询教师本人每学年，学的成绩数量接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }


    /**
     * 学生可以查询本人每学年各学科成绩数量
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Integer sumTermSubGradeForStu(TermSubGradeForStuQuery entity) throws Exception {
        List<TermSubGradeForStuResp> returnList = new ArrayList<>();
        //3,数据查询
        try {
            //使用criteria写法
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<TermSubGradeForStuResp> criteriaQuery = criteriaBuilder.createQuery(TermSubGradeForStuResp.class);
            Root<Score> scoreRoot = criteriaQuery.from(Score.class);
            criteriaQuery.multiselect(
                    scoreRoot.get("stu_id").as(Integer.class).alias("stuId"),
                    scoreRoot.get("sub_score").as(Double.class).alias("subScore"),
                    scoreRoot.get("term").as(Integer.class).alias("term"),
                    scoreRoot.get("sub_id").as(Integer.class).alias("subId")

            );
            Predicate condition = criteriaBuilder.equal(scoreRoot.get("stu_id"),entity.getStu_id());
            List scoreRootList = new ArrayList();
            scoreRootList.add(scoreRoot.get("stu_id"));
            scoreRootList.add(scoreRoot.get("term"));
            scoreRootList.add(scoreRoot.get("sub_id"));
            criteriaQuery.groupBy(scoreRootList);
            criteriaQuery.where(condition);
            TypedQuery<TermSubGradeForStuResp> typedQuery = em.createQuery(criteriaQuery);
            List<TermSubGradeForStuResp> result = typedQuery.getResultList();
            if(CollectionUtils.isEmpty(result)){
                return returnList.size();
            }
            return result.size();
        }catch (Exception e){
            log.error("学生可以查询本人每学年各学科成绩数量接口数据连接层出现异常，异常信息为{}",e);
            throw e;
        }
    }
}
