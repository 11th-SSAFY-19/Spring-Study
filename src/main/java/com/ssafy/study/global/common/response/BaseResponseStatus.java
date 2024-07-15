package com.ssafy.study.global.common.response;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 200 : 요청 성공
     */
    SUCCESS(200, "요청에 성공하였습니다."),
    SUCCESS_CREATING(201, "생성에 성공하였습니다."),

    /**
     * 400 : Request 오류
     */
    // Common
    REQUEST_ERROR( 400, "입력값을 확인해주세요."),
    EMPTY_JWT( 401, "JWT를 입력해주세요."),
    INVALID_JWT( 402, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(403,"권한이 없는 유저의 접근입니다."),

    // members
    EMPTY_MEMBER_ID( 404, "유저 아이디 값을 확인해주세요."),

    // webtoons

    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(3000, "값을 불러오는데 실패하였습니다."),

    // members
    MEMBER_NOT_FOUND(404, "회원을 찾을 수 없습니다."),

    // webtoons
    WEBTOON_NOT_FOUND(404, "회원을 찾을 수 없습니다."),

    // episdoes
    EPISODE_NOT_FOUND(404, "에피소드을 찾을 수 없습니다."),

    // comment
    COMMENT_NOT_FOUND(404, "코멘트를 찾을 수 없습니다."),


    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR( 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(4001, "서버와의 연결에 실패하였습니다.");

    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요

    private final int code;
    private final String message;

    private BaseResponseStatus(int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.code = code;
        this.message = message;
    }
}
