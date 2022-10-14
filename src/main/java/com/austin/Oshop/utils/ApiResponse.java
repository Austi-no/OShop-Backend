package com.austin.Oshop.utils;

import com.fasterxml.jackson.annotation.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 23/Sep/2022 - 8:02 PM
 * @project reservation
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "response", "status", "message", "date"
})
public class ApiResponse<T> {

    @JsonProperty("status")
    private String response;

    @JsonProperty("message")
    private String message;
    @JsonProperty("status")
    private int status;
    @JsonProperty("data")
    private T data;


    public ApiResponse() {
    }

    public ApiResponse(int status, String response, String message) {
        this.status = status;
        this.response = response;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(int status, String response, T data) {
        this.status = status;
        this.response = response;
        this.data = data;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
