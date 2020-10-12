package com.qaiware.messaging.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "messages")
public class Message implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "type")
    private String messageType;

    @Column
    private String payload;

    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt = Instant.now();

    public Message(String messageType, String payload) {
        this.messageType = messageType;
        this.payload = payload;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override public String toString() {
        return "Message{" +
            "id=" + id +
            ", messageType=" + messageType +
            ", payload='" + payload + '\'' +
            ", createdAt=" + createdAt +
            '}';
    }
}
