package org.cqrs.event.storage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type; // like MoneyDepositedEvent
    private String payload; // .json format

    public EventEntity() {}

    public EventEntity(String type, String payload) {
        this.type = type;
        this.payload = payload;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPayload() {
        return payload;
    }
}