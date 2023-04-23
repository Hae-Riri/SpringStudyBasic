package com.example.springstudybasic.service;

import com.example.springstudybasic.core.member.Member;

public interface MemberService {
    void join(Member member);
    Member findMember(Long memberId);
}
