package com.jakmall.jakscraper.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception {

		http.cors();
		http.csrf().disable();

		return http.build();
	}
	
	@Bean
	public List<RequestMatcher> matchers() {
		final List<RequestMatcher> matchers = new ArrayList<>();
		matchers.add(new AntPathRequestMatcher("/login", HttpMethod.POST.toString()));
		
		matchers.add(new AntPathRequestMatcher("/suppliers/**", HttpMethod.GET.toString()));
		matchers.add(new AntPathRequestMatcher("/suppliers/**", HttpMethod.POST.toString()));

		matchers.add(new AntPathRequestMatcher("/stores/**", HttpMethod.GET.toString()));
		matchers.add(new AntPathRequestMatcher("/stores/**", HttpMethod.POST.toString()));
		
		matchers.add(new AntPathRequestMatcher("/products/**", HttpMethod.GET.toString()));
		matchers.add(new AntPathRequestMatcher("/products/**", HttpMethod.POST.toString()));

		matchers.add(new AntPathRequestMatcher("/solds/**", HttpMethod.GET.toString()));
		matchers.add(new AntPathRequestMatcher("/solds/**", HttpMethod.POST.toString()));
		
		matchers.add(new AntPathRequestMatcher("/invoices/**", HttpMethod.GET.toString()));
		matchers.add(new AntPathRequestMatcher("/invoices/**", HttpMethod.POST.toString()));

		matchers.add(new AntPathRequestMatcher("/excels", HttpMethod.POST.toString()));

		matchers.add(new AntPathRequestMatcher("/scrape/**", HttpMethod.GET.toString()));
		matchers.add(new AntPathRequestMatcher("/scrape/**", HttpMethod.POST.toString()));
		return matchers;
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> matchers().forEach(r -> {
			web.ignoring().requestMatchers(r);
		});
	}
	
	@Bean
	public WebMvcConfigurer mvcConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedOrigins("http://localhost:4200", "http://localhost")
					.allowedMethods(
						HttpMethod.GET.name(),
						HttpMethod.POST.name(),
						HttpMethod.PUT.name(),
						HttpMethod.PATCH.name());
			}
		};
	}
}