package com.fubuki.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class TestMapperTest {

    @Test
    void insert() {
        //创建SpringIoC容器,并根据配置文件在容器中实例化对象
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:app*.xml");
        //获取容器内所有beanId数组
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
            System.out.println("类型:" + context.getBean(beanName).getClass().getName());
            System.out.println("内容:" + context.getBean(beanName));
        }
    }
}