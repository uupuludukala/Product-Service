package com.coolbook.erp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import com.coolbook.erp.security.exception.NoUserAuthenticatedException;

@Component
public class SecurityFacade {

	public String getCurrentAuthorizationToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object details = authentication.getDetails();
			if (OAuth2AuthenticationDetails.class.isInstance(details)) {
				return "Bearer " + ((OAuth2AuthenticationDetails) details).getTokenValue();
			}
		}
		return null;
	}

	public User getCurrentUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			throw new NoUserAuthenticatedException();
		}
		Object details = authentication.getDetails();
		if (OAuth2AuthenticationDetails.class.isInstance(details)
				&& User.class.isInstance(((OAuth2AuthenticationDetails) details).getDecodedDetails())) {
			return (User) ((OAuth2AuthenticationDetails) details).getDecodedDetails();
		}
		throw new NoUserAuthenticatedException();

	}
	
	 public boolean isUserConnected() {
	        return SecurityContextHolder.getContext().getAuthentication() != null;
	    }

}
