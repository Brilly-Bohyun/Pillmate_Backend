package pillmate.backend.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import pillmate.backend.common.exception.handler.FCMInterceptor;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FCMConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new FCMInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}
