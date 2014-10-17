package com.multi.springapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.hibernate.HikariConnectionProvider;

public class MultiTenantProvider implements MultiTenantConnectionProvider, ServiceRegistryAwareService  {
 
	private static final long serialVersionUID = 4368575201221677384L;
	
	private HikariConnectionProvider connectionProvider = null;
 
	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}
 
	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {
		@SuppressWarnings("rawtypes")
		Map lSettings = serviceRegistry.getService(ConfigurationService.class).getSettings();
		
		connectionProvider = new HikariConnectionProvider();
		connectionProvider.configure(lSettings);
	}
 
	@Override
	public boolean isUnwrappableAs(@SuppressWarnings("rawtypes") Class clazz) {
		return false;
	}
 
	@Override
	public <T> T unwrap(Class<T> clazz) {
		return null;
	}
 
	@Override
	public Connection getAnyConnection() throws SQLException {
		final Connection connection = connectionProvider.getConnection();
		return connection;
	}
 
	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		final Connection connection = getAnyConnection();
		try {
			ResultSet resultSet = connection.createStatement().executeQuery("SELECT 1 from pg_database WHERE datname='" + tenantIdentifier + "'");
			if(resultSet.getInt(0) == 0 ){
				
			}
			connection.createStatement().execute("SET SCHEMA '" + tenantIdentifier + "'");
		}
		catch (SQLException e) {
			throw new HibernateException("Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]", e);
		}
		return connection;
	}
 
	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		try {
			connection.createStatement().execute("SET SCHEMA 'public'");
		}
		catch (SQLException e) {
			throw new HibernateException("Could not alter JDBC connection to specified schema [public]", e);
		}
		connectionProvider.closeConnection(connection);
	}
 
	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		releaseAnyConnection(connection);
	}
}