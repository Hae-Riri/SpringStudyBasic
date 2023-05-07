package com.example.springstudybasic.core;

import com.example.springstudybasic.core.member.MemberRepository;
import com.example.springstudybasic.core.order.OrderServiceImpl;
import com.example.springstudybasic.service.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest {

    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        System.out.println("MemberService 에서 호출하는 memberRepository : " + memberService.getMemberRepository());
        System.out.println("OrderService 에서 호출하는 memberRepository : " + orderService.getMemberRepository());
        System.out.println("bean 등록 메소드 땜에 호출된 memberRepository : " + memberRepository);

        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean으로 등록된 AppConfig 출력 : " + bean);

    }
}
