package com.ssafy.study.domain.episode.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import com.ssafy.study.domain.comment.entity.Comment;
import com.ssafy.study.domain.webtoon.entity.Webtoon;
import com.ssafy.study.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Episode")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Episode extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "episode_id")
    private Long episodeId;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id")
    private Webtoon webtoon;

    @Column(name = "view_count")
    private Integer viewCount;

    @OneToMany(mappedBy = "episode")
    private List<Comment> commentList= new ArrayList<>();

    @Builder
    private Episode(String title, Webtoon webtoon) {
        this.title = title;
        this.webtoon = webtoon;
        this.viewCount = 0;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "episodeId=" + episodeId +
                ", title='" + title + '\'' +
                ", webtoon=" + webtoon +
                ", viewCount=" + viewCount +
                ", commentList=" + commentList +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}