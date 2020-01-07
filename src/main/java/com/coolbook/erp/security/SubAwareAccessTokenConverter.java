package com.coolbook.erp.security;

import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;



public class SubAwareAccessTokenConverter extends DefaultAccessTokenConverter {

	@Override
	public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
		return super.extractAuthentication(map);
	}

	public static String getCurrentCustomer() {

		if (SecurityContextHolder.getContext().getAuthentication() instanceof OAuth2Authentication) {
			OAuth2AuthenticationDetails det = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext()
					.getAuthentication().getDetails();
			return ((UserDetails) det.getDecodedDetails()).getUsername();
		} else if (SecurityContextHolder.getContext()
				.getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
			return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		}
		return null;

	}
}
