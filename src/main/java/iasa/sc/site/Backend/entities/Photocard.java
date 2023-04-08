package iasa.sc.site.Backend.entities;

import iasa.sc.site.Backend.entities.enums.PhotocardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "photocards")
public class Photocard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer Id;

    @Column(name = "uuid", nullable = false, unique = true, columnDefinition = "uuid")
    @Generated(value = GenerationTime.INSERT)
    private UUID uuid;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PhotocardType type;
}
