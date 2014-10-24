package com.multi.springapp.dao;

import org.springframework.security.core.context.SecurityContextHolder;

public class MultiTenantIdentifierResolver implements
		org.hibernate.context.spi.CurrentTenantIdentifierResolver {

	public String resolveCurrentTenantIdentifier() {
		return SecurityContextHolder.getContext().getAuthentication() == null ? "shared" : SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public boolean validateExistingCurrentSessions() {
		return true;
	}

}