package com.fubuki.controller;


import com.fubuki.entity.Member;
import com.fubuki.entity.MemberReadState;
import com.fubuki.service.MemberService;
import com.fubuki.utils.ResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/check_login")
    public ResponseUtils checkLogin(String username, String password , String nickname , String vc ,
                                 HttpServletRequest request){
        //获取验证码
        String verifyCode = (String)request.getSession().getAttribute("kaptchaVerifyCode");
        ResponseUtils resp;
        if(vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)){
            resp = new ResponseUtils("VerifyCodeError", "验证码错误");
        }else{
            try {
                //校验成功后检查用户名和密码
                Member member = memberService.checkLogin(username, password);
                member.setSalt(null);
                member.setPassword(null);
                resp = new ResponseUtils().put("member",member);
            } catch (Exception e) {
                e.printStackTrace();
                resp=new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
            }
        }
        return resp;
    }

    @GetMapping("/select_by_id")
    public ResponseUtils selectById(Long memberId){
        ResponseUtils resp=null;
        try {
            Member member= memberService.selectById(memberId);
            resp=new ResponseUtils().put("member",member);
        } catch (Exception e) {
            resp=new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }

    @GetMapping("/select_read_state")
    public ResponseUtils selectMemberReadState(Long bookId,Long memberId){
        ResponseUtils resp;
        try {
            MemberReadState memberReadState=
                    memberService.selectMemberReadState(bookId,memberId);
            resp=new ResponseUtils().put("readState",memberReadState);
        } catch (Exception e) {
            resp=new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }
}
