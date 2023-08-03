package com.fubuki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fubuki.entity.Category;
import com.fubuki.entity.Evaluation;

import java.util.List;
import java.util.Map;

public interface EvaluationMapper extends BaseMapper<Evaluation> {
    //查询结果需要附带用户昵称，因此需要使用关联查询
    public List<Map> selectByBookId(Long bookId);
}
