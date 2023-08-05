package com.fubuki.controller;

import com.google.code.kaptcha.Producer;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class KaptchaController {

    private final Producer kaptchaProducer;
    @Autowired
    public KaptchaController(Producer kaptchaProducer) {
        this.kaptchaProducer = kaptchaProducer;
    }

    @GetMapping("/api/verify_code")
    public void createVerifyCode(HttpServletRequest request , HttpServletResponse response) throws IOException {
        //响应立即过期
        response.setDateHeader("Expires" , 0);
        //不缓存任何图片数据
        response.setHeader("Cache-Control" , "no-store,no-cache,must-revalidate");
       //关闭前置检查和后置检查
        response.setHeader("Cache-Control" ,"post-check=0,pre-check=0");
       //不开启缓存
        response.setHeader("Pragma" , "no-cache");
        response.setContentType("image/png");
        //生成验证码图片
         //获取验证码文本用于后续验证
        String text = kaptchaProducer.createText();
        request.getSession().setAttribute("kaptchaVerifyCode" , text);
        //生成验证码图片
        BufferedImage image = kaptchaProducer.createImage(text);
        //传输图片
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
