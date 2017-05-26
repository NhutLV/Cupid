package com.signatic.model;

import java.util.Date;

/**
 * Created by DefaultAccount on 10/21/2016.
 */
public class Chat {
    private String message;
    private String id;
    private Date dateChat;
    private String userChat;
    private String UrlImage;
    public Chat() {
    }

    public Chat(String message, String id,Date dateChat,String userChat,String UrlImage) {
        this.message = message;
        this.id = id;
        this.dateChat=dateChat;
        this.userChat=userChat;
        this.UrlImage=UrlImage;
    }

    public String getUrlImage() {
        return UrlImage;
    }

    public void setUrlImage(String urlImage) {
        UrlImage = urlImage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateChat() {
        return dateChat;
    }

    public void setDateChat(Date dateChat) {
        this.dateChat = dateChat;
    }

    public String getUserChat() {
        return userChat;
    }

    public void setUserChat(String userChat) {
        this.userChat = userChat;
    }
}
