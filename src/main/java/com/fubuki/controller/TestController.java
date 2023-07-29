package com.fubuki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {
    @PostMapping("/t/t1")
    @ResponseBody
    public Map test(String context){
        Map map=new HashMap();
        map.put("test","测试："+context);
        return map;
    }
}
