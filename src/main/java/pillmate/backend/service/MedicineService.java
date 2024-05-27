package pillmate.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pillmate.backend.common.exception.NotFoundException;
import pillmate.backend.common.exception.errorcode.ErrorCode;
import pillmate.backend.dto.alarm.AlarmRequest;
import pillmate.backend.dto.medicine.AddDirectlyRequest;
import pillmate.backend.dto.medicine.UpcomingAlarm;
import pillmate.backend.entity.Alarm;
import pillmate.backend.entity.Medicine;
import pillmate.backend.entity.member.Member;
import pillmate.backend.repository.AlarmRepository;
import pillmate.backend.repository.MedicinePerMemberRepository;
import pillmate.backend.repository.MedicineRepository;
import pillmate.backend.repository.MemberRepository;
import pillmate.backend.service.alarm.AlarmService;

import java.time.LocalTime;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicineService {
    private final AlarmService alarmService;
    private final AlarmRepository alarmRepository;
    private final MedicineRepository medicineRepository;
    private final MemberRepository memberRepository;
    private final MedicinePerMemberRepository medicinePerMemberRepository;


    public UpcomingAlarm getUpcomingAlarm(Long memberId) {
        Alarm alarm = alarmRepository.findUpcomingAlarmsByMemberId(memberId, LocalTime.now()).stream().findFirst().orElse(null);
        return UpcomingAlarm.builder().medicineName(alarm.getMedicine().getName()).time(alarm.getTime()).build();
    }

    public void addDirectly(Long memberId, AddDirectlyRequest addDirectlyRequest) {
        medicinePerMemberRepository.save(addDirectlyRequest.toEntity(findByMemberId(memberId), findByName(addDirectlyRequest.getMedicineName())));

        // 사용자가 선택한 시간대를 기준으로 알람 저장
        LocalTime userTime = findByTime(findByMemberId(memberId), addDirectlyRequest.getTimeOfDay());
        AlarmRequest alarmRequest = AlarmRequest.builder().medicineName(addDirectlyRequest.getMedicineName()).time(userTime).build();
        alarmService.createAlarm(memberId, alarmRequest);
    }

    private Member findByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEMBER));
    }

    private Medicine findByName(String name) {
        return medicineRepository.findByName(name).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEDICINE));
    }

    private LocalTime findByTime(Member member, String time) {
        if (time.equals("기상 직후")) {
            return member.getWakeUp().plusMinutes(5);
        } else if (time.equals("아침 식후")) {
            return member.getMorning().plusMinutes(5);
        } else if (time.equals("점심 식전")) {
            return member.getLunch().minusMinutes(5);
        } else if (time.equals("점심 식후")) {
            return member.getLunch().plusMinutes(5);
        } else if (time.equals("저녁 식전")) {
            return member.getDinner().minusMinutes(5);
        } else if (time.equals("저녁 식후")) {
            return member.getDinner().plusMinutes(5);
        } else {
            return member.getBed().minusMinutes(5);
        }
    }
}
