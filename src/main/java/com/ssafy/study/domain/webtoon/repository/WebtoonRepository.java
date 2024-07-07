package com.ssafy.study.domain.webtoon.repository;

import com.ssafy.study.domain.webtoon.entity.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {
}
