package com.fubuki;

import com.fubuki.service.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class newMain {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext(
                "classpath:m*.xml");
        MemberService memberService=
                (MemberService) context.getBean("memberServiceImpl");
        memberService.createMember("fubuki","123456","依依不舍");
    }
}
