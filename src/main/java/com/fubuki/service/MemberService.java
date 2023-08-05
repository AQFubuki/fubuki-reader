package com.fubuki.service;

import com.fubuki.entity.Member;

public interface MemberService {
    public Member createMember(String username, String password, String nickname);
}
