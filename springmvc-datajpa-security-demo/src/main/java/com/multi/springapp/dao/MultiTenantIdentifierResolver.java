package com.multi.springapp.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.multi.springapp.web.config.TenantContext;

public class MultiTenantIdentifierResolver implements org.hibernate.context.spi.CurrentTenantIdentifierResolver {

	@Autowired
	private TenantContext tenantContext;

	public String resolveCurrentTenantIdentifier() {
		return tenantContext.getTenantId();
	}

	public boolean validateExistingCurrentSessions() {
		return true;
	}
}