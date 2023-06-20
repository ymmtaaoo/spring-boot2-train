package com.example.demo.core.exception;

public class SystemException extends RuntimeException {
    private String messageId;
    private String field;
    public SystemException(String messageId, String field, Throwable cause) {
        super(cause);
        this.messageId = messageId;
        this.field = field;
    }

    public SystemException(String messageId, Throwable cause) {
        super(cause);
        this.messageId = messageId;
    }

    public SystemException(String messageId, String field) {
        this.messageId = messageId;
        this.field = field;
    }

    public SystemException(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public String getField() {
        return this.field;
    }
}
