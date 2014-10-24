package com.multi.springapp.web.config.impl;

import org.springframework.stereotype.Component;

@Component
public class TenantContext {

	public String getTenantId() {
		return "teste";
	}
}
