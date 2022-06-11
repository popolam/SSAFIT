package com.ssafy.ssafit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.ssafy.ssafit.interceptor.JWTInterceptor;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
		.allowedMethods("GET","POST","PUT","DELETE")
		.maxAge(6000);
		
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
		registry.addResourceHandler("/file/**").addResourceLocations("classpath:/static/file/");
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	@Autowired
	private JWTInterceptor jwtInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor)
				.addPathPatterns("/api/**")
				.excludePathPatterns("/api/login", 
										"/api/review", 
										"/api/join", 
										"/api/video/**", 
										"/api/figure/**", 
										"/api/follow/**", 
										"/api/isfollow/**", 
										"/api/mark/**", 
										"/api/ismark/**", 
										"/api/upload/**", 
										"/api/mark/**", 
										"/api/ranking",
										"/api/todo/**"
									);
	}
}







