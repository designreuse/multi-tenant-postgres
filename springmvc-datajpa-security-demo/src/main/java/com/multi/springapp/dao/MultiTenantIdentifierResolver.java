package com.multi.springapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multi.springapp.web.config.TenantContext;

@Component
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