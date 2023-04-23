package com.example.springstudybasic.core.discount;

import com.example.springstudybasic.core.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
