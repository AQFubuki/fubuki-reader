package com.fubuki.service;

import com.fubuki.entity.Evaluation;

import java.util.List;
import java.util.Map;

public interface EvaluationService {
    //public List<Evaluation> selectByBookId(Long bid);
    public List<Map> selectByBookId(Long bookId);

    public Evaluation addEvaluation(Long bookId, String content,
                                    Integer score, Long memberId);
}
