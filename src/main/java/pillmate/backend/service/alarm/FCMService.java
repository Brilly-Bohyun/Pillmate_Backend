package pillmate.backend.service.alarm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pillmate.backend.entity.Medicine;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class FCMService {
    private RestTemplate restTemplate;

    private static final String FCM_API_URL = "https://fcm.googleapis.com/fcm/send";

    public void sendNotification(Long userId, Medicine medicine) {
        Map<String, Object> notification = new HashMap<>();
        notification.put("title", "Medicine Reminder");
        notification.put("body", "Time to take your medicine: " + medicine.getName());

        Map<String, Object> message = new HashMap<>();
        message.put("notification", notification);
        message.put("to", "/topics/user_" + userId); // Assuming you subscribe users to a topic based on userId

        restTemplate.postForObject(FCM_API_URL, message, String.class);
    }
}
