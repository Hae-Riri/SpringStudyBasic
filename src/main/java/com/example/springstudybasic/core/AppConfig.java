package com.example.springstudybasic.core;

import com.example.springstudybasic.core.discount.DiscountPolicy;
import com.example.springstudybasic.core.discount.FixDiscountPolicy;
import com.example.springstudybasic.core.member.MemberRepository;
import com.example.springstudybasic.core.member.MemoryMemberRepository;
import com.example.springstudybasic.core.order.OrderService;
import com.example.springstudybasic.core.order.OrderServiceImpl;
import com.example.springstudybasic.service.MemberService;
import com.example.springstudybasic.service.MemberServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
