package com.example.chaekimjeo7;

public class ChatRoomItem {
    private String userName;
    private String lastMessage;
    private String date;

    public ChatRoomItem(String userName, String lastMessage, String date) {
        this.userName = userName;
        this.lastMessage = lastMessage;
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getDate() {
        return date;
    }
}
