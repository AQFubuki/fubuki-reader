package com.fubuki;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fubuki.controller.TestController;
import com.fubuki.entity.Category;
import com.fubuki.entity.Test;
import com.fubuki.mapper.TestMapper;
import com.fubuki.service.CategoryService;
import com.fubuki.service.TestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //创建SpringIoC容器,并根据配置文件在容器中实例化对象
        ApplicationContext context=new ClassPathXmlApplicationContext(
                "classpath:app*.xml");
        CategoryService categoryService =
                (CategoryService) context.getBean("categoryServiceImpl");
        List<Category> categories = categoryService.selectAll();
        for (Category category : categories) {
            System.out.println(category);
        }
        //获取容器内所有beanId数组
//        String[] beanNames=context.getBeanDefinitionNames();
//        for(String beanName:beanNames){
//            System.out.println(beanName);
//            System.out.println("类型:"+context.getBean(beanName).getClass().getName());
//            System.out.println("内容:"+context.getBean(beanName));
//        }
        //TestMapper testMapper=(TestMapper) context.getBean("testMapper");
        //testMapper.insert();
        //TestService testService=context.getBean("testService",TestService.class);
        //testService.batchImport();
//        Test test=new Test();
//        test.setContent("plus插入测试");
//        testMapper.insert(test);
//
//        Test test1 = testMapper.selectById(39);
//        test1.setContent("39号测试");
//        testMapper.updateById(test1);
//
//        testMapper.deleteById(38);
//
//        QueryWrapper queryWrapper=new QueryWrapper();
//        queryWrapper.likeLeft("content","测试");
//        queryWrapper.lt("id",41);
//        List<Test> list = testMapper.selectList(queryWrapper);
//        for (Test o : list) {
//            System.out.println(o);
//        }
    }
}
