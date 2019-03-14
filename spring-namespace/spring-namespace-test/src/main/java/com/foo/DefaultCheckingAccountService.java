package com.foo;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: wangsaichao
 * @date: 2019/3/14
 * @description:
 */
public class DefaultCheckingAccountService {

    private String url ;

    @Autowired
    private JCacheInitializer jCacheInitializer ;

    public JCacheInitializer getjCacheInitializer() {
        return jCacheInitializer;
    }

    public void setjCacheInitializer(JCacheInitializer jCacheInitializer) {
        this.jCacheInitializer = jCacheInitializer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
