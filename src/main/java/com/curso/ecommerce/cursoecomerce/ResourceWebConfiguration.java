package com.curso.ecommerce.cursoecomerce;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Cognotación de configuración 
@Configuration
public class ResourceWebConfiguration implements WebMvcConfigurer {
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //Registro para mandar las ubicaciones de las imagenes
		registry.addResourceHandler("/images/**").addResourceLocations("file:images/");
	}
}
