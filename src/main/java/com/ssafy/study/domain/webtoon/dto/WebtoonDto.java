package com.ssafy.study.domain.webtoon.dto;

import jakarta.persistence.Column;

public class WebtoonDto {

    private String title;
    private String publicationStatus;
    private boolean noYouth;

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationStatus() {
        return publicationStatus;
    }

    public void setPublicationStatus(String publicationStatus) {
        this.publicationStatus = publicationStatus;
    }

    public boolean isNoYouth() {
        return noYouth;
    }

    public void setNoYouth(boolean noYouth) {
        this.noYouth = noYouth;
    }
}
