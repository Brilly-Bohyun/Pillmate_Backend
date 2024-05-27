package pillmate.backend.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pillmate.backend.entity.member.Member;
import pillmate.backend.entity.member.MemberRole;
import pillmate.backend.entity.member.Role;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SignUpRequest {
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    private String password;

    @NotBlank(message = "이름은 공백일 수 없습니다.")
    private String name;

    private LocalTime wakeUp;

    private LocalTime morning;

    private LocalTime lunch;

    private LocalTime dinner;

    private LocalTime bed;

    private Map<String, LocalDate> diseases;

    @NotNull(message = "권한은 필수입니다.")
    @Size(min = 1, message = "권한은 최소 1개 이상이여야 합니다.")
    private List<String> roles;

    public Member toEntity(String encodedPassword) {
        Member member = Member.builder()
                .email(email)
                .password(encodedPassword)
                .name(name)
                .wakeUp(wakeUp)
                .morning(morning)
                .lunch(lunch)
                .dinner(dinner)
                .bed(bed)
                .diseases(diseases)
                .build();

        roles.stream().map(
                s -> Role.builder()
                        .value(MemberRole.valueOf(s))
                        .build()
        ).forEach(member::addRole);

        return member;
    }
}
