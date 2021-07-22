package br.eletrosom.portalcolaborador.config;

import org.springframework.web.multipart.support.StandardServletMultipartResolver;

//@Configuration
public class AppConfig {
	
	/*@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(1024 * 2048);
	    return multipartResolver;
	}*/
	
	//@Bean
	public StandardServletMultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}

}
