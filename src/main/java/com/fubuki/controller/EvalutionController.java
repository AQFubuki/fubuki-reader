package com.fubuki.controller;

import com.fubuki.entity.Book;
import com.fubuki.entity.Evaluation;
import com.fubuki.service.EvaluationService;
import com.fubuki.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/evaluation")
public class EvalutionController {
    private final EvaluationService evaluationService;

    @Autowired
    public EvalutionController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }
    @GetMapping("/list")
    public ResponseUtils list(long bookId){
        ResponseUtils resp = null;

        try {
            List<Map> evaluations=evaluationService.selectByBookId(bookId);
            resp = new ResponseUtils().put("list",evaluations);
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }
    @PostMapping("/add")
    public ResponseUtils addEvaluation(Long bookId, String content, Integer score, Long memberId){
        ResponseUtils resp = null;
        try {
            Evaluation evaluation= evaluationService.addEvaluation(bookId, content, score, memberId);
            resp = new ResponseUtils().put("evaluation",evaluation);
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }
}
