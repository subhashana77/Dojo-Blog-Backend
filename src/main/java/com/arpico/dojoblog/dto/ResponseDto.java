package com.arpico.dojoblog.dto;

/**
 * @author dilshan.r
 * @created 6/1/2022 - 12:54 PM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */
public class ResponseDto {
    private boolean success;
    private String message;
    private Object data;

    public ResponseDto() {
    }

    public ResponseDto(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
