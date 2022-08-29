package com.yxh.weshare.bean.pojo;

public class Authority {
    private Integer wsUserAuthority;

    private String wsAuthorityDescription;

    public Integer getWsUserAuthority() {
        return wsUserAuthority;
    }

    public void setWsUserAuthority(Integer wsUserAuthority) {
        this.wsUserAuthority = wsUserAuthority;
    }

    public String getWsAuthorityDescription() {
        return wsAuthorityDescription;
    }

    public void setWsAuthorityDescription(String wsAuthorityDescription) {
        this.wsAuthorityDescription = wsAuthorityDescription == null ? null : wsAuthorityDescription.trim();
    }
}