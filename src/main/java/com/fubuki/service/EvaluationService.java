package com.fubuki.service;

import com.fubuki.entity.Evaluation;

import java.util.List;
import java.util.Map;

public interface EvaluationService {
    //public List<Evaluation> selectByBookId(Long bid);
    public List<Map> selectByBookId(Long bookId);
}
