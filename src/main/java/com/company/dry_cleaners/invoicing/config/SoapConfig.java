package com.company.dry_cleaners.invoicing.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapConfig{

    @Bean
    ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext context) {

        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        servlet.setTransformSchemaLocations(true);

        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "boletas")
    DefaultWsdl11Definition defaultWsdl11Definition(
            XsdSchema boletaSchema) {

        DefaultWsdl11Definition wsdl =
                new DefaultWsdl11Definition();

        wsdl.setPortTypeName("BoletaPort");
        wsdl.setLocationUri("/ws");
        wsdl.setTargetNamespace("http://ms.boleta.soap");
        wsdl.setSchema(boletaSchema);

        return wsdl;
    }

    @Bean
    XsdSchema boletaSchema() {
        return new SimpleXsdSchema(
                new ClassPathResource("xsd/boleta.xsd"));
    }
}