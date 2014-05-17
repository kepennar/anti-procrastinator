package org.kepennar.aproc.config;

import groovy.util.logging.Log

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.datatype.joda.JodaModule

@Configuration
@Log
class MvcConfiguration extends WebMvcConfigurerAdapter {

	
	@Override
	void addInterceptors(InterceptorRegistry registry) {
		log.info "Registering interceptor"
		registry.addInterceptor(interceptor())
	}
	
	@Bean
	HandlerInterceptor interceptor() {
		log.info "Creating interceptor"
		[
			postHandle: { request, response, handler, mav ->
				if (mav) {
					mav.getModelMap().
						  addAttribute("date", new Date())
				}
			}
		] as HandlerInterceptorAdapter
	}
	
	@Bean Module jodaModule() {
		return new JodaModule();
	}
}
