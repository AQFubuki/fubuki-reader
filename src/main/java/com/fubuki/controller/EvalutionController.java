package com.fubuki.controller;

import com.fubuki.entity.Book;
import com.fubuki.service.EvaluationService;
import com.fubuki.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
