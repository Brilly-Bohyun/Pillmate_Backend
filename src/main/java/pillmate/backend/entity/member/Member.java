package pillmate.backend.entity.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "password", nullable = true)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Disease> diseases = new ArrayList<>();

    @Column(name = "provider_id", nullable = true)
    private Long providerId;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberType type;

    @Column(name = "usable", nullable = false)
    private Boolean usable;

    @Builder
    public Member(Long id, String email, String name, String password, MemberType type, Long providerId, Boolean usable) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.type = type;
        this.providerId = providerId;
        this.usable = usable;
    }

    public void addRole(Role role) {
        if (role != null) {
            roles.add(role);
            role.setMember(this);
        }
    }

    public void addDisease(Disease disease) {
        if (disease != null) {
            diseases.add(disease);
        }
    }

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(r -> r.getValue().name())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return String.valueOf(id);
    }

    public void updatePassword(String encodedPassword) {
        if (encodedPassword != null && !encodedPassword.isEmpty()) {
            this.password = encodedPassword;
        }
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return usable;
    }
}
