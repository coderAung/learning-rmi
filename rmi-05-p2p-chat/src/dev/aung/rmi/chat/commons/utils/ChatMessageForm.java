package dev.aung.rmi.chat.commons.utils;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ChatMessageForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String fromId;
    private String toId;
    private String message;
    private LocalDateTime sentAt;

    public ChatMessageForm(String fromId, String toId, String message) {
        this.fromId = fromId;
        this.toId = toId;
        this.message = message;
        this.sentAt = LocalDateTime.now();
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
