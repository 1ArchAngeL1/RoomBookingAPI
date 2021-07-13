package com.example.meetingroom.DTO;


public class Response {
    private Object result;

    public Response(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
