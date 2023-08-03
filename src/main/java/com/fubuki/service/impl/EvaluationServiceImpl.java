package com.fubuki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fubuki.entity.Category;
import com.fubuki.entity.Evaluation;
import com.fubuki.mapper.EvaluationMapper;
import com.fubuki.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

//    @Override
//    public List<Evaluation> selectByBookId(Long bid) {
//        QueryWrapper queryWrapper=new QueryWrapper();
//        queryWrapper.orderByDesc("evaluation_id");
//        queryWrapper.eq("book_id",bid);
//        List<Evaluation> evaluations=evaluationMapper.selectList(queryWrapper);
//        return evaluations;
//    }

}
