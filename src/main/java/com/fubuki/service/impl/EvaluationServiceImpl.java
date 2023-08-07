package com.fubuki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fubuki.entity.Category;
import com.fubuki.entity.Evaluation;
import com.fubuki.entity.MemberReadState;
import com.fubuki.mapper.EvaluationMapper;
import com.fubuki.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class EvaluationServiceImpl implements EvaluationService {
    private final EvaluationMapper evaluationMapper;

    @Autowired
    public EvaluationServiceImpl(EvaluationMapper evaluationMapper) {
        this.evaluationMapper = evaluationMapper;
    }

    @Override
    public List<Map> selectByBookId(Long bookId) {
        return evaluationMapper.selectByBookId(bookId);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Evaluation addEvaluation(Long bookId, String content, Integer score, Long memberId) {
        Evaluation evaluation=new Evaluation();
        evaluation.setContent(content);
        evaluation.setBookId(bookId);
        evaluation.setScore(score);
        evaluation.setMemberId(memberId);
        evaluation.setCreateTime(new Date());
        evaluation.setEnjoy(0);
        evaluation.setState("enable");
        evaluation.setDisableReason(null);
        evaluation.setDisableTime(null);
        evaluationMapper.insert(evaluation);
        return evaluation;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Evaluation enjoy(Long evaluationId) {
//        QueryWrapper<Evaluation> wrapper = new QueryWrapper<>();
//        wrapper.eq("evaluation_id",evaluationId);
//        Evaluation evaluation = evaluationMapper.selectOne(wrapper);
        Evaluation evaluation = evaluationMapper.selectById(evaluationId);
        evaluation.setEnjoy(evaluation.getEnjoy()+1);
        evaluationMapper.updateById(evaluation);
        return evaluation;
    }

//    @Override
//    public List<Evaluation> selectByBookId(Long bid) {
//        QueryWrapper queryWrapper=new QueryWrapper();
//        queryWrapper.orderByDesc("evaluation_id");
//        queryWrapper.eq("book_id",bid);
//        List<Evaluation> evaluations=evaluationMapper.selectList(queryWrapper);
//        return evaluations;
//    }

}
