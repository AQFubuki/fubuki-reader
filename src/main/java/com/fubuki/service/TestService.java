package com.fubuki.service;

import com.fubuki.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
    private final TestMapper testMapper;
    //使用构造函数进行注入
    @Autowired
    public TestService(TestMapper testMapper) {
        this.testMapper = testMapper;
    }
    @Transactional(rollbackFor = Exception.class) //事务注解。执行成功则提交数据，执行失败则进行回滚
    public void batchImport(){
        for(int i=0;i<6;i++){
            if(i==3){
                //throw new RuntimeException("未知错误");
            }
            //this.testMapper.insert();
        }
    }

    public TestMapper getTestMapper() {
        return testMapper;
    }
}
