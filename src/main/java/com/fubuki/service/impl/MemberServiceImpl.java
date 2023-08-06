package com.fubuki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fubuki.entity.Member;
import com.fubuki.entity.MemberReadState;
import com.fubuki.mapper.MemberMapper;
import com.fubuki.mapper.MemberReadStateMapper;
import com.fubuki.service.MemberService;
import com.fubuki.service.exception.MemberException;
import com.fubuki.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final MemberReadStateMapper memberReadStateMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper, MemberReadStateMapper memberReadStateMapper) {
        this.memberMapper = memberMapper;
        this.memberReadStateMapper = memberReadStateMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Member createMember(String username, String password, String nickname) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        List<Member> members = memberMapper.selectList(wrapper);
        if (members.size() > 0) {
            throw new MemberException("用户已存在");
        }
        Member member = new Member();
        member.setUsername(username);
        member.setNickname(nickname);
        member.setCreateTime(new Date());
        int salt = new Random().nextInt(1000) + 1000;
        member.setSalt(salt);
        String md5 = Md5Utils.md5Digest(password, salt);
        member.setPassword(md5);
        memberMapper.insert(member);
        return member;
    }

    @Override
    public Member checkLogin(String username, String password) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
//        List<Member> members = memberMapper.selectList(wrapper);
//        if (members.size() == 0) {
//            throw new MemberException("用户不存在");
//        }
//        Member member = members.get(0);
        Member member=memberMapper.selectOne(wrapper);
        if (member == null) {
            throw new MemberException("用户不存在");
        }
        String md5 = Md5Utils.md5Digest(password, member.getSalt());
        if (!md5.equals(member.getPassword())) {
            throw new MemberException("密码错误");
        }
        return member;
    }

    @Override
    public Member selectById(Long memberId) {
//        QueryWrapper<Member> wrapper = new QueryWrapper<>();
//        wrapper.eq("member_id",memberId);
//        return memberMapper.selectOne(wrapper);
        return memberMapper.selectById(memberId);
    }

    @Override
    public MemberReadState selectMemberReadState(Long bookId, Long memberId) {
        QueryWrapper<MemberReadState> wrapper = new QueryWrapper<>();
        wrapper.eq("book_id", bookId);
        wrapper.eq("member_id", memberId);
        return memberReadStateMapper.selectOne(wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberReadState createMemberReadState(Long bookId, Long memberId, int readState) {
        QueryWrapper<MemberReadState> wrapper = new QueryWrapper<>();
        wrapper.eq("book_id", bookId);
        wrapper.eq("member_id", memberId);
        List<MemberReadState> mrss = memberReadStateMapper.selectList(wrapper);
        if (mrss.size() > 0) {
            throw new MemberException("评价状态已存在");
        }
        MemberReadState memberReadState = new MemberReadState();
        memberReadState.setMemberId(memberId);
        memberReadState.setBookId(bookId);
        memberReadState.setReadState(readState);
        memberReadState.setCreateTime(new Date());
        memberReadStateMapper.insert(memberReadState);
        return memberReadState;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberReadState updateMemberReadState(Long bookId, Long memberId, int readState) {
        QueryWrapper<MemberReadState> wrapper = new QueryWrapper<>();
        wrapper.eq("book_id", bookId);
        wrapper.eq("member_id", memberId);
        List<MemberReadState> mrss = memberReadStateMapper.selectList(wrapper);
        if (mrss.size() == 0) {
            //其实也可以把createMemberReadState整合进这里
            throw new MemberException("评价状态不存在");
        }
        MemberReadState memberReadState=mrss.get(0);
        memberReadState.setReadState(readState);
        memberReadState.setCreateTime(new Date());
        memberReadStateMapper.updateById(memberReadState);
        return memberReadState;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberReadState updateMemberReadState2(Long bookId, Long memberId, int readState) {
        QueryWrapper<MemberReadState> wrapper = new QueryWrapper<>();
        wrapper.eq("book_id", bookId);
        wrapper.eq("member_id", memberId);
        MemberReadState memberReadState = memberReadStateMapper.selectOne(wrapper);
        if (memberReadState == null) {
            memberReadState=new MemberReadState();
            memberReadState.setMemberId(memberId);
            memberReadState.setBookId(bookId);
            memberReadState.setReadState(readState);
            memberReadState.setCreateTime(new Date());
            memberReadStateMapper.insert(memberReadState);
        }else {
            memberReadState.setReadState(readState);
            memberReadState.setCreateTime(new Date());
            memberReadStateMapper.updateById(memberReadState);
        }
        return memberReadState;
    }

}
