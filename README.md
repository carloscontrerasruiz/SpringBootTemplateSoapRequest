# SpringBootTemplateSoapRequest
Proyecto Venta Tarjetas conexion servicio SOAP

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Las clase creadas para el servicio soap fueron creadas con el comando wsimport
* Este proyecto es una aplicacion REST que a su vez consume una Aplicacion SOAP la aplicacion ocupa usuario y contraseña como Authorizacion basica
* , las clases del servicio SOAP se generan mediante el wsimport y el wsdl del servicio.
*La conexion con el servicio SOAP se realiza usando WebServiceTemplate
