package com.ssafy.study.global.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // BaseEntity를 상속한 엔티티들은 아래 필드들을 컬럼으로 인식하게 된다.
@EntityListeners(AuditingEntityListener.class) // Auditing(자동으로 값 매핑) 기능 추가
public abstract class BaseTimeEntity {

	@Column(name = "created_at", updatable = false)
	protected LocalDateTime createdAt;

	@Column(name = "updated_at")
	protected LocalDateTime updatedAt;

	@PrePersist // 해당 엔티티 저장하기 전
	void onPrePersist(){
		this.createdAt = LocalDateTime.now();
		this.updatedAt = createdAt;
	}

	@PreUpdate // 해당 엔티티 수정 하기 전
	void onPreUpdate(){
		this.updatedAt = LocalDateTime.now();
	}

}