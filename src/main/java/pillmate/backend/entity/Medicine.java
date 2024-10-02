package pillmate.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = true)
    private String category;

    @Column(name = "photo", nullable = true)
    private String photo;

    @Builder
    public Medicine(Long id, String name, String category, String photo) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.photo = photo;
    }

    public void updateName(String name) {
        if (name != null) {
            this.name = name;
        }
    }
}
