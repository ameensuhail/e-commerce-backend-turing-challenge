package com.shopping.config;

	

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.shopping.controller","com.shopping.exceptionhandling" })
public class WebConfig extends WebMvcConfigurerAdapter{

}


