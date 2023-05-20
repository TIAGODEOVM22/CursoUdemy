package br.com.tiago.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		 /*______________via query param___________*/
		
		 // http://localhost:8080/api/person?mediaType=json 
		configurer.favorParameter(true) /*aceita parametros*/
				.parameterName("mediaType")
				.ignoreAcceptHeader(true); /*nome e ignor parametros no header*/
		configurer.favorParameter(true).parameterName("mediaType")
				.ignoreAcceptHeader(true)
				.useRegisteredExtensionsOnly(false)
				.defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("json", MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_XML);
	}
	
	/*____________________via header param__________________*/	
	
		//http://localhost:8080/api/person/v1
	/*	configurer.favorParameter(false)
		.ignoreAcceptHeader(false)
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
			.mediaType("json", MediaType.APPLICATION_JSON)
			.mediaType("xml", MediaType.APPLICATION_XML);
	}*/
}
