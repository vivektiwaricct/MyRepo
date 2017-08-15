package com.rest.resttest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.rest.resttest")
public class MVCConfiguration  extends WebMvcConfigurerAdapter{
	
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		//
		super.configureContentNegotiation(configurer);
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.beanName();
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}