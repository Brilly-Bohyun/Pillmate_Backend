package pillmate.backend.entity.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "wake_up", nullable = false)
    private LocalTime wakeUp;

    @Column(name = "morning", nullable = false)
    private LocalTime morning;

    @Column(name = "lunch", nullable = false)
    private LocalTime lunch;

    @Column(name = "dinner", nullable = false)
    private LocalTime dinner;

    @Column(name = "bed", nullable = false)
    private LocalTime bed;

    @ElementCollection
    @CollectionTable(name = "member_diseases", joinColumns = @JoinColumn(name = "member_id"))
    @MapKeyColumn(name = "disease")
    @Column(name = "diagnosis_date")
    private Map<String, LocalDate> diseases;

    @Column(name = "provider_id", nullable = true)
    private Long providerId;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberType type;

    @Builder
    public Member(Long id, String email, String name, String password, LocalTime wakeUp, LocalTime morning, LocalTime lunch, LocalTime dinner, LocalTime bed, MemberType type, Long providerId, Map<String, LocalDate> diseases) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.wakeUp = wakeUp;
        this.morning = morning;
        this.lunch = lunch;
        this.dinner = dinner;
        this.bed = bed;
        this.diseases = diseases;
        this.type = type;
        this.providerId = providerId;
    }

    public void addRole(Role role) {
        if (role != null) {
            roles.add(role);
            role.setMember(this);
        }
    }

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(r -> r.getValue().name())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public void updatePassword(String encodedPassword) {
        if (encodedPassword != null && !encodedPassword.isEmpty()) {
            this.password = encodedPassword;
        }
    }
}
