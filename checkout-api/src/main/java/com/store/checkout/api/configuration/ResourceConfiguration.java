package com.store.checkout.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ResourceConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * This configuration enable all chars on PathVariable of Rest Controllers 
	 */
	@Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }
	
}
