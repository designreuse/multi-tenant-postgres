package com.multi.springapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;

import com.zaxxer.hikari.hibernate.HikariConnectionProvider;

public class MultiTenantProvider implements MultiTenantConnectionProvider, ServiceRegistryAwareService  {
 
	private static final long serialVersionUID = 4368575201221677384L;
	
	private static final String DRIVER_CLASS_NAME = "driverClassName";
	private static final String URL = "url";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
	@SuppressWarnings("rawtypes")
	private Map settings = null;
 
	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}
 
	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {
		settings = serviceRegistry.getService(ConfigurationService.class).getSettings();
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
		HikariConnectionProvider connectionProvider = new HikariConnectionProvider();
		connectionProvider.configure(settings);
		try {
			return connectionProvider.getConnection();
		}catch (SQLException e) {
			throw new HibernateException("Could not alter JDBC connection to specified schema [shared]", e);
		}
	}
 
	@SuppressWarnings("unchecked")
	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
			HikariConnectionProvider connectionProvider = new HikariConnectionProvider();
			
			@SuppressWarnings("rawtypes")
			Map settingsMultiTenacy = new HashMap();
			settingsMultiTenacy.put("hibernate.connection.driver_class",settings.get("hibernate.connection.driver_class"));
			settingsMultiTenacy.put("hibernate.connection.username",settings.get("hibernate.connection.username"));
			settingsMultiTenacy.put("hibernate.connection.password",settings.get("hibernate.connection.password"));
			settingsMultiTenacy.put("hibernate.connection.url", "jdbc:postgresql://127.0.0.1:5432/"+tenantIdentifier);
			
		try {
			ResultSet resultSet = getAnyConnection().createStatement().executeQuery("SELECT 1 from pg_database WHERE datname='" + tenantIdentifier + "'");
			if(!resultSet.next()){
				getAnyConnection().createStatement().executeQuery("create database " + tenantIdentifier + "");
			}
			connectionProvider.configure(settingsMultiTenacy);
			return connectionProvider.getConnection();
		}
		catch (SQLException e) {
			throw new HibernateException("Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]", e);
		}
	}
 
	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		new HikariConnectionProvider().closeConnection(connection);
	}
 
	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		releaseAnyConnection(connection);
	}
}