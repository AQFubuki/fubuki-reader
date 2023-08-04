package com.fubuki.controller;


import com.fubuki.utils.ResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/member")
public class MemberController {
    @PostMapping("/registe")
    public ResponseUtils registe(String username, String password , String nickname , String vc ,
                                 HttpServletRequest request){
        //获取验证码
        String verifyCode = (String)request.getSession().getAttribute("kaptchaVerifyCode");
        ResponseUtils resp;
        if(vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)){
            resp = new ResponseUtils("VerifyCodeError", "验证码错误");
        }else{
            resp = new ResponseUtils();
        }
        return resp;
    }
}
