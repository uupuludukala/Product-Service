package com.coolbook.erp.config.security;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.Data;

@Configuration
@ConfigurationProperties("cors")
@Data
public class CORSConfiguration {

	private boolean allowCredentials;
	private List<String> allowedHeaders;
	private List<String> allowedMethods;
	private List<String> allowedOrigins;
	private List<String> exposedHeaders;
	private long maxAge;
	private String path;

	@Bean
	public FilterRegistrationBean createCorsConfiguration() {
		final CorsConfiguration configuration = new CorsConfiguration();

		getAllowedOrigins().forEach(configuration::addAllowedOrigin);

		getAllowedHeaders().forEach(configuration::addAllowedHeader);
		
		getExposedHeaders().forEach(configuration::addExposedHeader);

		configuration.setAllowCredentials(isAllowCredentials());
		configuration.setMaxAge(getMaxAge());
		configuration.setAllowedMethods(getAllowedMethods());

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration(getPath(), configuration);

		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
