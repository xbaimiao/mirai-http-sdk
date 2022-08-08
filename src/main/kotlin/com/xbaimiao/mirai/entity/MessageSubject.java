package com.xbaimiao.mirai.entity;

import com.google.gson.annotations.SerializedName;

public class MessageSubject {
    @SerializedName("id")
    private int id;

    @SerializedName("kind")
    private String kind;

    public int getId() {
        return id;
    }

    public SubjectKind getKind() {
        return SubjectKind.valueOf(kind);
    }

    enum SubjectKind {
        Group,
        Friend
    }
}
