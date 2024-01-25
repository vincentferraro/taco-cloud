package taco.com.tacocloud.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix="taco.orders")
public class OrderProps {
    private int pageSize = 20;
}
