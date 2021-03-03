package com.genesys.application.config;


import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * data source config to enable transaction
 *
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
	
}