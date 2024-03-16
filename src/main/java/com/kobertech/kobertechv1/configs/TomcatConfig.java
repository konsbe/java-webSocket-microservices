package com.kobertech.kobertechv1.configs;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> containerCustomizer() {
        return (container -> {
            if (container instanceof TomcatServletWebServerFactory) {
                TomcatServletWebServerFactory tomcat = (TomcatServletWebServerFactory) container;
                tomcat.addConnectorCustomizers(connector -> {
                    if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol) {
                        AbstractHttp11Protocol<?> protocolHandler = (AbstractHttp11Protocol<?>) connector.getProtocolHandler();
                        
                        // Set the maximum HTTP header size (bytes)
                        protocolHandler.setMaxHttpHeaderSize(8192); // Example size: 8192 bytes
                        
                        // Set the maximum swallow size (bytes)
                        protocolHandler.setMaxSwallowSize(-1); // Example size: -1 (unlimited)
                    }
                });
            }
        });
    }
}
