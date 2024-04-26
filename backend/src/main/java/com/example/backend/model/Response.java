package com.example.backend.model;

public class Response<T> {
    public int status;
    public T data;

    public Response(int status, T data) {
        this.status = status;
        this.data = data;
    }
}
