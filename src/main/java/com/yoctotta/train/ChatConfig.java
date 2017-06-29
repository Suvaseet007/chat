package com.yoctotta.train;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = { "classpath:/chat.properties" })
public class ChatConfig {
	@Autowired
	Environment env;

	@Bean
	public StorageLocation getLocation() {
		StorageLocation location = new StorageLocation();
		location.setFolderLocation(env.getProperty("chat.root.path"));
		return location;
	}
}
