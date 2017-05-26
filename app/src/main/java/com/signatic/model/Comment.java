package com.signatic.model;

import java.util.UUID;

/**
 * Created by NhutDu on 22/09/2016.
 */
public class Comment {

    //region Properties

    private UUID mId;
    private String content;
    private User mUserComment;
    private String username;

    //endregion

    //region Getter and Setter


    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUserComment() {
        return mUserComment;
    }

    public void setUserComment(User userComment) {
        mUserComment = userComment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //endregion

    //region Constructor

    public Comment(UUID id, String content, User userComment, String username) {
        mId = id;
        this.content = content;
        mUserComment = userComment;
        this.username = username;
    }

    //endregion
}
