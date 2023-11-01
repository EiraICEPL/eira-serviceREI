package com.hummersoft.eira.exception.advice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

    @JsonProperty
    private int statusCode;

    @JsonProperty
    private String statusDescription;

    public Message(int statusCode, String statusDescription) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }


}
