CREATE TABLE `Member` (
	`member_id`	BIGINT	NOT NULL,
	`username`	VARCHAR(256)	NULL,
	`password`	VARCHAR(256)	NULL,
	`created_at`	DATETIME	NULL,
	`updated_at`	DATETIME	NULL,
	`cookie_count`	INT	NULL
);

CREATE TABLE `CookieChargeHistory` (
	`cookie_charge_history_id`	BIGINT	NOT NULL,
	`amount`	INT	NULL,
	`created_at`	DATETIME	NULL,
	`updated_at`	DATETIME	NULL,
	`member_id`	BIGINT	NOT NULL,
	`payment`	INT	NULL
);

CREATE TABLE `CookieConsumeHistory` (
	`cookie_consume_history_id`	BIGINT	NOT NULL,
	`created_at`	DATETIME	NULL,
	`updated_at`	DATETIME	NULL,
	`amount`	INT	NULL,
	`member_id`	BIGINT	NOT NULL,
	`episode_id`	BIGINT	NOT NULL
);

CREATE TABLE `Webtoon` (
	`웹툰아이디`	BIGINT	NOT NULL,
	`생성일자`	DATETIME	NULL,
	`수정일자`	DATETIME	NULL,
	`제목`	VARCHAR	NULL,
	`썸네일`	VARCHAR	NULL,
	`설명`	VARCHAR	NULL,
	`연재상태`	VARCHAR	NULL,
	`작가아이디`	BIGINT	NOT NULL
);

CREATE TABLE `Episode` (
	`에피소드아이디`	BIGINT	NOT NULL,
	`제목`	VARCHAR	NULL,
	`내용`	VARCHAR	NULL,
	`추신`	VARCHAR	NULL,
	`웹툰아이디`	BIGINT	NOT NULL,
	`생성일자`	DATETIME	NULL,
	`수정일자`	DATETIME	NULL,
	`조회수`	INT	NULL,
	`공개상태`	BOOLEAN	NULL,
	`필요한쿠키양`	INT	NULL,
	`무료공개일`	DATETIME	NULL
);

CREATE TABLE `Author` (
	`작가아이디`	BIGINT	NOT NULL,
	`이름`	VARCHAR	NULL,
	`생성일자`	DATETIME	NULL,
	`수정일자`	DATETIME	NULL
);

CREATE TABLE `PublishingDay` (
	`연재일아이디`	BIGINT	NOT NULL,
	`생성일자`	DATETIME	NULL,
	`수정일자`	DATETIME	NULL,
	`요일`	VARCHAR	NULL
);

CREATE TABLE `HashTag` (
	`해시태그아이디`	BIGINT	NOT NULL,
	`이름`	VARCHAR	NULL,
	`생성일자`	DATETIME	NULL,
	`수정일자`	DATETIME	NULL
);

CREATE TABLE `WebtoonHashTag` (
	`웹툰해시태그아이디`	BIGINT	NOT NULL,
	`해시태그아이디`	BIGINT	NOT NULL,
	`웹툰아이디`	BIGINT	NOT NULL,
	`생성일자`	DATETIME	NULL,
	`수정일자`	DATETIME	NULL
);

CREATE TABLE `WebtoonPublishingDay` (
	`웹툰연재일아이디`	BIGINT	NOT NULL,
	`생성일자`	DATETIME	NULL,
	`수정일자`	DATETIME	NULL,
	`연재일아이디`	BIGINT	NOT NULL,
	`웹툰아이디`	BIGINT	NOT NULL
);

CREATE TABLE `InterestedWebtoon` (
	`관심웹툰아이디`	BIGINT	NOT NULL,
	`웹툰아이디`	BIGINT	NOT NULL,
	`회원아이디`	BIGINT	NOT NULL,
	`생성일자`	DATETIME	NULL,
	`수정일자`	DATETIME	NULL
);

CREATE TABLE `EpisodeLike` (
	`좋아요아이디`	BIGINT	NOT NULL,
	`회원아이디`	BIGINT	NOT NULL,
	`에피소드아이디`	BIGINT	NOT NULL,
	`생성일자`	DATETIME	NULL,
	`수정일자`	DATETIME	NULL
);

CREATE TABLE `Star` (
	`별점아이디`	BIGINT	NOT NULL,
	`점수`	FLOAT	NULL,
	`회원아이디`	BIGINT	NOT NULL,
	`에피소드아이디`	BIGINT	NOT NULL,
	`생성일자`	DATETIME	NULL,
	`수정일자`	DATETIME	NULL
);

CREATE TABLE `Comment` (
	`댓글아이디`	BIGINT	NOT NULL,
	`내용`	VARCHAR	NULL,
	`회원아이디`	BIGINT	NOT NULL,
	`에피소드아이디`	BIGINT	NOT NULL,
	`생성일자`	DATETIME	NULL,
	`수정일자`	DATETIME	NULL
);

CREATE TABLE `ReComment` (
	`대댓글아이디`	BIGINT	NOT NULL,
	`내용`	VARCHAR	NULL,
	`댓글아이디`	BIGINT	NOT NULL,
	`회원아이디`	BIGINT	NOT NULL,
	`생성일자`	DATETIME	NULL,
	`수정일자`	DATETIME	NULL
);

CREATE TABLE `CommentEmotion` (
	`댓글감정아이디`	BIGINT	NOT NULL,
	`감정상태`	VARCHAR	NULL,
	`댓글아이디`	BIGINT	NOT NULL,
	`회원아이디`	BIGINT	NULL,
	`생성일자`	DATETIME	NULL,
	`수정일자`	DATETIME	NULL
);

CREATE TABLE `ReCommentEmotion` (
	`대댓글감정아이디`	BIGINT	NOT NULL,
	`감정상태`	VARCHAR	NULL,
	`회원아이디`	BIGINT	NULL,
	`대댓글아이디`	BIGINT	NOT NULL,
	`생성일자`	DATETIME	NULL,
	`수정일자`	DATETIME	NULL
);

CREATE TABLE `ReadEpisode` (
	`본웹툰아이디`	BIGINT	NOT NULL,
	`최근에피소드읽음여부`	BOOLEAN	NULL,
	`회원아이디`	BIGINT	NOT NULL,
	`에피소드아이디`	BIGINT	NOT NULL
);

CREATE TABLE `RecommendedWebtoon` (
	`추천웹툰아이디`	BIGINT	NOT NULL,
	`생성일자`	DATETIME	NULL,
	`수정일자`	DATETIME	NULL,
	`웹툰아이디`	BIGINT	NOT NULL
);

ALTER TABLE `Member` ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
	`회원아이디`
);

ALTER TABLE `CookieChargeHistory` ADD CONSTRAINT `PK_COOKIECHARGEHISTORY` PRIMARY KEY (
	`쿠키충전기록아이디`
);

ALTER TABLE `CookieConsumeHistory` ADD CONSTRAINT `PK_COOKIECONSUMEHISTORY` PRIMARY KEY (
	`쿠키사용내역아이디`
);

ALTER TABLE `Webtoon` ADD CONSTRAINT `PK_WEBTOON` PRIMARY KEY (
	`웹툰아이디`
);

ALTER TABLE `Episode` ADD CONSTRAINT `PK_EPISODE` PRIMARY KEY (
	`에피소드아이디`
);

ALTER TABLE `Author` ADD CONSTRAINT `PK_AUTHOR` PRIMARY KEY (
	`작가아이디`
);

ALTER TABLE `PublishingDay` ADD CONSTRAINT `PK_PUBLISHINGDAY` PRIMARY KEY (
	`연재일아이디`
);

ALTER TABLE `HashTag` ADD CONSTRAINT `PK_HASHTAG` PRIMARY KEY (
	`해시태그아이디`
);

ALTER TABLE `WebtoonHashTag` ADD CONSTRAINT `PK_WEBTOONHASHTAG` PRIMARY KEY (
	`웹툰해시태그아이디`
);

ALTER TABLE `WebtoonPublishingDay` ADD CONSTRAINT `PK_WEBTOONPUBLISHINGDAY` PRIMARY KEY (
	`웹툰연재일아이디`
);

ALTER TABLE `InterestedWebtoon` ADD CONSTRAINT `PK_INTERESTEDWEBTOON` PRIMARY KEY (
	`관심웹툰아이디`
);

ALTER TABLE `EpisodeLike` ADD CONSTRAINT `PK_EPISODELIKE` PRIMARY KEY (
	`좋아요아이디`
);

ALTER TABLE `Star` ADD CONSTRAINT `PK_STAR` PRIMARY KEY (
	`별점아이디`
);

ALTER TABLE `Comment` ADD CONSTRAINT `PK_COMMENT` PRIMARY KEY (
	`댓글아이디`
);

ALTER TABLE `ReComment` ADD CONSTRAINT `PK_RECOMMENT` PRIMARY KEY (
	`대댓글아이디`
);

ALTER TABLE `CommentEmotion` ADD CONSTRAINT `PK_COMMENTEMOTION` PRIMARY KEY (
	`댓글감정아이디`
);

ALTER TABLE `ReCommentEmotion` ADD CONSTRAINT `PK_RECOMMENTEMOTION` PRIMARY KEY (
	`대댓글감정아이디`
);

ALTER TABLE `ReadEpisode` ADD CONSTRAINT `PK_READEPISODE` PRIMARY KEY (
	`본웹툰아이디`
);

ALTER TABLE `RecommendedWebtoon` ADD CONSTRAINT `PK_RECOMMENDEDWEBTOON` PRIMARY KEY (
	`추천웹툰아이디`
);

