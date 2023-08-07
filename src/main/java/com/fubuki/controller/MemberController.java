package com.fubuki.controller;


import com.fubuki.entity.Evaluation;
import com.fubuki.entity.Member;
import com.fubuki.entity.MemberReadState;
import com.fubuki.service.EvaluationService;
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
    private final EvaluationService evaluationService;

    @Autowired
    public MemberController(MemberService memberService, EvaluationService evaluationService) {
        this.memberService = memberService;
        this.evaluationService = evaluationService;
    }

    @PostMapping("/registe")
    public ResponseUtils registe(String username, String password, String nickname, String vc,
                                 HttpServletRequest request) {
        //获取验证码
        String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        ResponseUtils resp;
        if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)) {
            resp = new ResponseUtils("VerifyCodeError", "验证码错误");
        } else {
            try {
                //校验成功后生成新用户
                memberService.createMember(username, password, nickname);
                resp = new ResponseUtils();
            } catch (Exception e) {
                e.printStackTrace();
                resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
            }
        }
        return resp;
    }

    @PostMapping("/check_login")
    public ResponseUtils checkLogin(String username, String password, String nickname, String vc,
                                    HttpServletRequest request) {
        //获取验证码
        String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        ResponseUtils resp;
        if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)) {
            resp = new ResponseUtils("VerifyCodeError", "验证码错误");
        } else {
            try {
                //校验成功后检查用户名和密码
                Member member = memberService.checkLogin(username, password);
                member.setSalt(null);
                member.setPassword(null);
                resp = new ResponseUtils().put("member", member);
            } catch (Exception e) {
                e.printStackTrace();
                resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
            }
        }
        return resp;
    }

    @GetMapping("/select_by_id")
    public ResponseUtils selectById(Long memberId) {
        ResponseUtils resp = null;
        try {
            Member member = memberService.selectById(memberId);
            resp = new ResponseUtils().put("member", member);
        } catch (Exception e) {
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }

    @GetMapping("/select_read_state")
    public ResponseUtils selectMemberReadState(Long bookId, Long memberId) {
        ResponseUtils resp;
        try {
            MemberReadState memberReadState =
                    memberService.selectMemberReadState(bookId, memberId);
            resp = new ResponseUtils().put("readState", memberReadState);
        } catch (Exception e) {
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }

    @GetMapping("/change_read_state")
    public ResponseUtils changeMemberReadState(Long bookId, Long memberId,
                                               int readState) {
        ResponseUtils resp;
        try {
            MemberReadState memberReadState =
                    memberService.selectMemberReadState(bookId, memberId);
            if (memberReadState == null) {
                memberReadState =
                        memberService.createMemberReadState(bookId, memberId, readState);
            } else {
                memberReadState =
                        memberService.updateMemberReadState(bookId, memberId, readState);
            }
            resp = new ResponseUtils().put("readState", memberReadState);
        } catch (Exception e) {
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }
    @PostMapping("/update_read_state")
    public ResponseUtils updateMemberReadState(Long bookId, Long memberId,
                                               int readState) {
        ResponseUtils resp;
        try {
            MemberReadState memberReadState =
                    memberService.updateMemberReadState2(bookId, memberId, readState);
            resp = new ResponseUtils().put("readState", memberReadState);
        } catch (Exception e) {
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }

    @PostMapping("/evaluate")
    public ResponseUtils evaluation(Long bookId, String content, Integer score, Long memberId){
        ResponseUtils resp = null;
        try {
            Evaluation evaluation= evaluationService.addEvaluation(bookId, content, score, memberId);
            resp = new ResponseUtils().put("evaluation",evaluation);
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }

    @PostMapping("/enjoy")
    public ResponseUtils enjoy(Long evaluationId){
        ResponseUtils resp = null;
        try {
            Evaluation evaluation= evaluationService.enjoy(evaluationId);
            resp = new ResponseUtils().put("evaluation",evaluation);
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }
}
