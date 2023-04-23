package com.example.springstudybasic.core;

import com.example.springstudybasic.core.discount.DiscountPolicy;
import com.example.springstudybasic.core.discount.FixDiscountPolicy;
import com.example.springstudybasic.core.member.MemberRepository;
import com.example.springstudybasic.core.member.MemoryMemberRepository;
import com.example.springstudybasic.core.order.OrderService;
import com.example.springstudybasic.core.order.OrderServiceImpl;
import com.example.springstudybasic.service.MemberService;
import com.example.springstudybasic.service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
