package pillmate.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pillmate.backend.common.util.LoggedInMember;
import pillmate.backend.dto.alarm.AlarmRequest;
import pillmate.backend.service.AlarmService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alarms")
public class AlarmController {

    @Autowired
    private final AlarmService alarmService;

    @PostMapping
    public ResponseEntity<String> createAlarm(@LoggedInMember Long memberId, @RequestBody AlarmRequest alarmRequest) {
        return alarmService.createAlarm(memberId, alarmRequest);
    }
}
