package com.qaiware.messaging.enums;

public enum MessageType {

    TEXT ("send_text"),
    EMOTION ("send_emotion");

    private String type;

   MessageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static MessageType get(String type) {
       switch(type) {
           case "send_text": return TEXT;
           case "send_emotion": return EMOTION;
           default: break;
       }
       return null;
    }
}
