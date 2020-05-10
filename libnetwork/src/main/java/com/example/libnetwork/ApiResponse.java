package com.example.libnetwork;

/**
 * @author zhanghuan
 */
public class ApiResponse<T> {
    public boolean success;
    public int status;
    public String message;
    public T body;
}
