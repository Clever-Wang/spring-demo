package com.foo;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class JCacheNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        super.registerBeanDefinitionDecoratorForAttribute("cache-name",
                new JCacheInitializingBeanDefinitionDecorator());
    }
}
