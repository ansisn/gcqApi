package com.gcq.gcqclientsdk;



import com.gcq.gcqclientsdk.client.GcqClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ComponentScan
@ConfigurationProperties("gcq.api.client")
@EnableConfigurationProperties(GcqApiClientConfig.class)
public class GcqApiClientConfig {

    private  String accessKey;

    private String secretKey;

    @Bean
    public GcqClient gcqApiClient(){
        return new GcqClient(accessKey,secretKey);
    }

}
