package com.school.exception;

public class ItemAlreadyInUseException extends Exception {
    private String response;

    public ItemAlreadyInUseException(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}