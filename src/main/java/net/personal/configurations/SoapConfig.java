package net.personal.configurations;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import mx.com.security.DesencriptaPropiedades;


@Configuration
public class SoapConfig {
	
	@Autowired
	ConfigurationsValues configurationsValues;
	
	
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath("net.personal.clientesoap.bajaventa");
		return jaxb2Marshaller;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(jaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
		webServiceTemplate.setDefaultUri(configurationsValues.getSoapUrl());
		// set a HttpComponentsMessageSender which provides support for basic authentication
		webServiceTemplate.setMessageSender(httpComponentsMessageSender());

		return webServiceTemplate;
	}

	@Bean
	public HttpComponentsMessageSender httpComponentsMessageSender() {
		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
		// set the basic authorization credentials
		httpComponentsMessageSender.setConnectionTimeout(configurationsValues.getConnectionTimeout());
		httpComponentsMessageSender.setReadTimeout(configurationsValues.getReadTimeout());
		httpComponentsMessageSender.setCredentials(usernamePasswordCredentials());

		return httpComponentsMessageSender;
	}

	@Bean
	public UsernamePasswordCredentials usernamePasswordCredentials() {
		// pass the user name and password to be used
		return new UsernamePasswordCredentials(configurationsValues.getSoapUsername(),
				DesencriptaPropiedades.desencripta(configurationsValues.getSoapKey(), configurationsValues.getSoapPass()));
	}

}
