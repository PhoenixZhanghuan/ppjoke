package com.example.libnetwork;

/**
 * @author zhanghuan
 */
public class PostRequest<T> extends Request<T, PostRequest> {
    public PostRequest(String url) {
        super(url);
    }
}
