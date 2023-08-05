package com.fubuki.controller;


import com.fubuki.service.MemberService;
import com.fubuki.utils.ResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/registe")
    public ResponseUtils registe(String username, String password , String nickname , String vc ,
                                 HttpServletRequest request){
        //获取验证码
        String verifyCode = (String)request.getSession().getAttribute("kaptchaVerifyCode");
        ResponseUtils resp;
        if(vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)){
            resp = new ResponseUtils("VerifyCodeError", "验证码错误");
        }else{
            try {
                //校验成功后生成新用户
                memberService.createMember(username, password, nickname);
                resp = new ResponseUtils();
            } catch (Exception e) {
                e.printStackTrace();
                resp=new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
            }
        }
        return resp;
    }
}
