/**
 * 
 */
package com.gft.dataservice.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer
{

	@Value("${init-db:false}")
	private String initDatabase;

	@PostConstruct
	public void init()
	{
		try {
			if (Boolean.parseBoolean(initDatabase)) {
				initDatabase();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void initDatabase()
	{

//		logger.info("Initializing Database with sample data");
		
//		Role role1 = new Role("ROLE_USER");
//		Role role2 = new Role("ROLE_ADMIN");
		
		

	}
}
