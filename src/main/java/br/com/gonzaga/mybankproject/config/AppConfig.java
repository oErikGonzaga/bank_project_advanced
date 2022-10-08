package br.com.gonzaga.mybankproject.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients({"br.com.gonzaga.mybankproject.client"})

public class AppConfig {
}
