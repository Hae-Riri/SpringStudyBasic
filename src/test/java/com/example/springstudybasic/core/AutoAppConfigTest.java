package com.example.springstudybasic.core;

import com.example.springstudybasic.core.member.MemberRepository;
import com.example.springstudybasic.core.order.OrderServiceImpl;
import com.example.springstudybasic.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class AutoAppConfigTest {

    @Test
    void basic() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = orderService.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
    }
}