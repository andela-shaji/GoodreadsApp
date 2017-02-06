package com.goodreadsapp.model;

public class Request {
    private String authentication;

    private String method;

    private String key;

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "ClassPojo [authentication = "
            + authentication
            + ", method = "
            + method
            + ", key = "
            + key
            + "]";
    }
}

