package com.coolbook.erp.config.security;

import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.coolbook.erp.security.SubAwareAccessTokenConverter;
import com.coolbook.erp.security.exception.CertificateLoadingException;

@Configuration
@ConfigurationProperties("oauth2")
@EnableResourceServer
public class AuthorizationResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String CERTIFICATE_TYPE = "X.509";
	@Value("${oauth.certificate.path}")
	private String certificatePath;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().and().authorizeRequests()
				.antMatchers("/initialSetup", "/swagger-ui.html", "/configuration/ui", "/swagger-resources/**", "/**/api-docs",
						"/health", "/hystrix.stream", "/version","/downloadFile/**")
				.permitAll().anyRequest().authenticated().and().headers().cacheControl().disable();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer config) {
		config.resourceId("resourceIds").tokenStore(tokenStore()).tokenServices(tokenServices());
//		config.tokenServices(tokenServices());
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setAccessTokenConverter(new SubAwareAccessTokenConverter());
		try {
			CertificateFactory fact = CertificateFactory.getInstance(CERTIFICATE_TYPE);
			InputStream inputStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(certificatePath);
			X509Certificate cer = (X509Certificate) fact.generateCertificate(inputStream);
			RSAPublicKey key = (RSAPublicKey) cer.getPublicKey();
			converter.setVerifier(new RsaVerifier(key));
		} catch (Exception e) {
			throw new CertificateLoadingException(e.getMessage(), e);
		}
		return converter;
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		return defaultTokenServices;
	}
	
	
}
