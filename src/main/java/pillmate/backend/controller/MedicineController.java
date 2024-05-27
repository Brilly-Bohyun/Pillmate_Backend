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
import pillmate.backend.dto.medicine.AddDirectlyRequest;
import pillmate.backend.dto.medicine.UpcomingAlarm;
import pillmate.backend.service.MedicineService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/medicines")
public class MedicineController {
    private final MedicineService medicineService;

    @GetMapping
    public UpcomingAlarm getUpcomingAlarm(@LoggedInMember Long memberId) {
        return medicineService.getUpcomingAlarm(memberId);
    }

    @PostMapping("/directly")
    public ResponseEntity<String> addDirectly(@LoggedInMember Long memberId, @RequestBody AddDirectlyRequest addDirectlyRequest) {
        medicineService.addDirectly(memberId, addDirectlyRequest);
        return ResponseEntity.ok("알약 저장이 완료되었습니다.");
    }
}
