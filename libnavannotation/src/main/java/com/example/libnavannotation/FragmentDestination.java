package com.example.libnavannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author zhanghuan
 */
@Target(ElementType.TYPE)
public @interface FragmentDestination {

    String pageUrl();

    boolean needLogin() default false;

    boolean asStarter() default false;

}
