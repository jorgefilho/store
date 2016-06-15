package com.store.checkout.api.configuration;

import java.util.Collections;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class HttpConfiguration {
	
    @Bean
    public HttpMessageConverters customConverters() {
        return new HttpMessageConverters(false, Collections.<HttpMessageConverter<?> >singleton(new MappingJackson2HttpMessageConverter()));
    }

}
