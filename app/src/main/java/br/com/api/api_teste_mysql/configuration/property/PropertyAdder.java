package br.com.api.api_teste_mysql.configuration.property;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Order
public class PropertyAdder implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            environment.getPropertySources()
                    .addFirst(new MapPropertySource("properties-to-logging",
                            Map.of("machine.host", InetAddress.getLocalHost().getHostName())));
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
