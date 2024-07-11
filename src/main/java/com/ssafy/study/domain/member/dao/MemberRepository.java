package com.ssafy.study.domain.member.dao;

import com.ssafy.study.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
