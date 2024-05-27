package pillmate.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pillmate.backend.common.util.LoggedInMember;
import pillmate.backend.dto.alarm.AlarmInfo;
import pillmate.backend.dto.alarm.AlarmRequest;
import pillmate.backend.service.alarm.AlarmService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alarms")
public class AlarmController {
    private final AlarmService alarmService;

    @PostMapping
    public ResponseEntity<String> createAlarm(@LoggedInMember Long memberId, @RequestBody AlarmRequest alarmRequest) {
        return alarmService.createAlarm(memberId, alarmRequest);
    }

    @GetMapping
    public List<AlarmInfo> showAll(@LoggedInMember Long memberId) {
        return alarmService.showAll(memberId);
    }
}
