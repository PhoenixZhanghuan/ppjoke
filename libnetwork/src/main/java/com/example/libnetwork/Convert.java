package com.example.libnetwork;

import java.lang.reflect.Type;

/**
 * @author zhanghuan
 */
public interface Convert<T> {
    T convert(String reponse, Type type);
}
