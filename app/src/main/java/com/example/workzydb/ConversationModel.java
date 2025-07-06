package com.example.workzydb;

public class ConversationModel {
    private String message;
    private String sender;

    public ConversationModel(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }
}
