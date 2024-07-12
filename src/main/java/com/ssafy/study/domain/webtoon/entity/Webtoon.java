package com.ssafy.study.domain.webtoon.entity;

import com.ssafy.study.global.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Webtoon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Webtoon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "webtoon_id")
    private Long webtoonId;

    @Column(name = "title")
    private String title;

    @Column(name = "publication_status")
    private String publicationStatus;

    @Column(name = "no_youth")
    private boolean noYouth;

    @Builder
    public Webtoon(String title, String publicationStatus, boolean noYouth) {
        this.title = title;
        this.publicationStatus = publicationStatus;
        this.noYouth = noYouth;
    }

    public void updateAll(String title, String publicationStatus, boolean noYouth) {
        this.title = title;
        this.publicationStatus = publicationStatus;
        this.noYouth = noYouth;
    }
}