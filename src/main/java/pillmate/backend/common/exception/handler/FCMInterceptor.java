package pillmate.backend.common.exception.handler;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class FCMInterceptor implements ClientHttpRequestInterceptor {
    private static final String FCM_SERVER_KEY = "YOUR_FCM_SERVER_KEY"; // Replace with your server key

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("Authorization", "key=" + FCM_SERVER_KEY);
        request.getHeaders().add("Content-Type", "application/json");
        return execution.execute(request, body);
    }
}
