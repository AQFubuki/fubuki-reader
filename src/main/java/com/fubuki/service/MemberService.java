package com.fubuki.service;

import com.fubuki.entity.Member;
import com.fubuki.entity.MemberReadState;

public interface MemberService {
    public Member createMember(String username, String password, String nickname);

    public Member checkLogin(String username, String password);

    public Member selectById(Long memberId);

    public MemberReadState selectMemberReadState(Long bookId, Long memberId);

    public MemberReadState createMemberReadState(Long bookId, Long memberId, int readState);

    public MemberReadState updateMemberReadState(Long bookId, Long memberId, int readState);

    public MemberReadState updateMemberReadState2(Long bookId, Long memberId, int readState);
}
