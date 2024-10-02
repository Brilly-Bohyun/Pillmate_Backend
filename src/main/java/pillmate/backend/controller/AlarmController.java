package pillmate.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pillmate.backend.common.util.LoggedInMember;
import pillmate.backend.dto.alarm.AlarmInfo;
import pillmate.backend.service.AlarmService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alarms")
public class AlarmController {
    private final AlarmService alarmService;

    @GetMapping
    public List<AlarmInfo> showAll(@LoggedInMember Long memberId) {
        return alarmService.showAll(memberId);
    }

    @PatchMapping("{alarmId}/{available}")
    public ResponseEntity<String> updateAlarmOnOff(@PathVariable("alarmId") Long alarmId,
                                                   @PathVariable("available") Boolean available,
                                                   @LoggedInMember Long memberId) {
        return alarmService.updateAvailability(alarmId, available, memberId);
    }
}
