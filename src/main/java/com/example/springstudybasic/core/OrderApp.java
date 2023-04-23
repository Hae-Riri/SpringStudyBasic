package com.example.springstudybasic.core;

import com.example.springstudybasic.core.member.Grade;
import com.example.springstudybasic.core.member.Member;
import com.example.springstudybasic.core.order.Order;
import com.example.springstudybasic.core.order.OrderService;
import com.example.springstudybasic.service.MemberService;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order " + order);
    }
}
