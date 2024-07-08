package com.example.demo.domain.webtoon.repository;

import com.example.demo.domain.webtoon.entity.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {
}
