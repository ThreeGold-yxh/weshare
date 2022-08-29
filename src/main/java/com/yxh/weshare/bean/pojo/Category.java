package com.yxh.weshare.bean.pojo;

public class Category {
    private Integer wsCategoryId;

    private String wsCategoryName;

    public Integer getWsCategoryId() {
        return wsCategoryId;
    }

    public void setWsCategoryId(Integer wsCategoryId) {
        this.wsCategoryId = wsCategoryId;
    }

    public String getWsCategoryName() {
        return wsCategoryName;
    }

    public void setWsCategoryName(String wsCategoryName) {
        this.wsCategoryName = wsCategoryName == null ? null : wsCategoryName.trim();
    }
}