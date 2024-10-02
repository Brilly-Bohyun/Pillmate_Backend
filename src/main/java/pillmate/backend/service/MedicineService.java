package pillmate.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pillmate.backend.common.exception.BadRequestException;
import pillmate.backend.common.exception.NotFoundException;
import pillmate.backend.common.exception.errorcode.ErrorCode;
import pillmate.backend.dto.medicine.AddRequest;
import pillmate.backend.dto.medicine.MedicineBasicInfo;
import pillmate.backend.dto.medicine.MedicineInfo;
import pillmate.backend.dto.medicine.ModifyMedicineInfo;
import pillmate.backend.dto.medicine.PrescriptionRequest;
import pillmate.backend.dto.medicine.UpcomingAlarm;
import pillmate.backend.entity.Alarm;
import pillmate.backend.entity.Medicine;
import pillmate.backend.entity.MedicinePerMember;
import pillmate.backend.entity.member.Member;
import pillmate.backend.repository.AlarmRepository;
import pillmate.backend.repository.MedicinePerMemberRepository;
import pillmate.backend.repository.MedicineRepository;
import pillmate.backend.repository.MemberRepository;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Alarm alarm = alarmRepository.findNextUpcomingAlarmByMember(memberId, LocalTime.now()).orElse(null);
        if (alarm == null) {
            throw new NotFoundException(ErrorCode.NOT_FOUND_ALARM);
        }

        return UpcomingAlarm.builder().medicineName(alarm.getMedicinePerMember().getMedicine().getName())
                                        .time(alarm.getMedicinePerMember().getTimeSlots().get(0).getPickerTime()).build();
    }

    public List<MedicineBasicInfo> getMedicineInfo(Long memberId, List<PrescriptionRequest> nameList) {
        return nameList.stream()
                .map(p -> medicineRepository.findByName(p.getName())
                        .map(m -> MedicineBasicInfo.builder()
                                .name(m.getName())
                                .photo(m.getPhoto())
                                .category(m.getCategory())
                                .build()
                        )
                        .orElse(MedicineBasicInfo.builder()
                                .name(p.getName())
                                .photo("db에서 해당 약을 찾을 수 없습니다")
                                .category("db에서 해당 약을 찾을 수 없습니다")
                                .build())
                )
                .collect(Collectors.toList());
    }

    @Transactional
    public void add(Long memberId, AddRequest addRequest) {
        Optional<Medicine> foundmedicine = medicineRepository.findByName(addRequest.getMedicineName());
        Medicine newMedicine;
        newMedicine = foundmedicine.orElseGet(() -> medicineRepository.save(Medicine.builder()
                .name(addRequest.getMedicineName())
                .category(addRequest.getDisease())
                .photo("white")
                .build()));

        MedicinePerMember medicinePerMember = addRequest.toEntity(findByMemberId(memberId), newMedicine);
        saveMedicinePerMember(medicinePerMember);
        alarmRepository.save(Alarm.builder().medicinePerMember(medicinePerMember).build());


        //saveAlarmList(addRequest.getTimeSlotList(), addRequest.getMedicineName(), memberId);
    }

    private void saveMedicinePerMember(MedicinePerMember addRequest) {
        medicinePerMemberRepository.save(addRequest);
    }

    public List<MedicineInfo> showAll(Long memberId) {
        return findAllByMemberId(memberId).stream()
                .map(medicinePerMember -> MedicineInfo.builder()
                        .id(medicinePerMember.getMedicine().getId())
                        .picture(medicinePerMember.getMedicine().getPhoto())
                        .name(medicinePerMember.getMedicine().getName())
                        .category(medicinePerMember.getMedicine().getCategory())
                        .amount(medicinePerMember.getAmount())
                        .timesPerDay(medicinePerMember.getTimes())
                        .day(medicinePerMember.getDay())
                        .timeSlotList(medicinePerMember.getTimeSlots())
                        .build())
                .sorted(Comparator.comparing(MedicineInfo::getName))
                .collect(Collectors.toList());
    }

    @Transactional
    public void modify(Long memberId, ModifyMedicineInfo modifyMedicineInfo) {
        Medicine medicine = findByName(modifyMedicineInfo.getOldMedicineName());
        if ("white".equals(medicine.getPhoto())) {
            MedicinePerMember medicinePerMember = findByMemberIdAndMedicineId(memberId, medicine.getId());
            medicinePerMember.update(modifyMedicineInfo.getAmount(),
                    modifyMedicineInfo.getTimesPerDay(),
                    modifyMedicineInfo.getDay(),
                    modifyMedicineInfo.getTimeSlotList());
            medicinePerMember.getMedicine().updateName(modifyMedicineInfo.getNewMedicineName());
        } else {
            throw new BadRequestException(ErrorCode.INVALID_MEDICINE);
        }
    }

    @Transactional
    public void delete(Long memberId, Long medicineId) {
        MedicinePerMember medicinePerMember = findByMemberIdAndMedicineId(memberId, medicineId);
        medicinePerMemberRepository.deleteById(medicinePerMember.getId());
        alarmService.deleteAlarm(memberId, medicinePerMember.getMedicine().getName());
    }

    private List<MedicinePerMember> findAllByMemberId(Long memberId) {
        return medicinePerMemberRepository.findAllByMemberId(memberId);
    }

    private MedicinePerMember findByMemberIdAndMedicineId(Long memberId, Long medicineId) {
        return medicinePerMemberRepository.findByMemberIdAndMedicineId(memberId, medicineId);
    }

    private Member findByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEMBER));
    }

    private Medicine findByName(String name) {
        return medicineRepository.findByName(name).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEDICINE));
    }
}
