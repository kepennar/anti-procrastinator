package org.kepennar.aproc.config;

import groovy.util.logging.Log

import org.kepennar.aproc.thymeleaf.components.PageWrapper;
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.PageRequest
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.datatype.joda.JodaModule

@Configuration
@Log
class MvcConfiguration extends WebMvcConfigurerAdapter {

	
	@Override
	void addInterceptors(InterceptorRegistry registry) {
		log.info "Registering interceptor"
		registry.addInterceptor(dateInterceptor())
	}
	
	HandlerInterceptor dateInterceptor() {
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
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver()
		resolver.setFallbackPageable(new PageRequest(0, PageWrapper.MAX_PAGE_ITEM_DISPLAY))
		resolver.setOneIndexedParameters(true)
		argumentResolvers.add(resolver)
	}
	
	
	@Bean Module jodaModule() {
		return new JodaModule()
	}
}
