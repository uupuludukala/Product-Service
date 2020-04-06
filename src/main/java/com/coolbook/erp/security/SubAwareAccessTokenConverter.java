package com.coolbook.erp.security;

import java.util.Map;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

public class SubAwareAccessTokenConverter extends DefaultAccessTokenConverter {

	private static final String COMPANY_CODE = "CompanyCode";
	private static final String BRANCH_CODE = "BranchCode";
	private static final String COMAPNY_ID = "CompanyId";

	@Override
	public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
		String companyCode = extractClaimUsingKeyString(map, COMPANY_CODE);
		String branchCode = extractClaimUsingKeyString(map, BRANCH_CODE);
		long companyId = extractClaimUsingKeyLong(map, COMAPNY_ID);

		OAuth2Authentication oAuth2Authentication = super.extractAuthentication(map);
		oAuth2Authentication.setDetails(new User(companyCode, branchCode, companyId));
		return oAuth2Authentication;
	}

	private String extractClaimUsingKeyString(Map<String, ?> map, String key) {
		if (map.containsKey(key)) {
			Object claimObj = map.get(key);
			if (String.class.isInstance(claimObj)) {
				return (String) claimObj;
			}
		}
		return null;
	}
	
	private long extractClaimUsingKeyLong(Map<String, ?> map, String key) {
		if (map.containsKey(key)) {
			Object claimObj = map.get(key);
			if (Integer.class.isInstance(claimObj)) {
				return (Integer) claimObj;
			}
		}
		return 0;
	}

}
