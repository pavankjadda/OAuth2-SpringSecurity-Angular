package com.springtesting.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import java.io.ObjectInputFilter;

public class Initializer extends AbstractHttpSessionApplicationInitializer
{
    public Initializer()
    {
        super(ObjectInputFilter.Config.class);
    }
}
