package iasa.sc.site.Backend.entity;

import iasa.sc.site.Backend.entity.enums.PrintType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prints")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Print {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "print_type", nullable = false, unique = true)
    PrintType printType;
    @Column(name = "photo_url", nullable = false, unique = false)
    private String photoUrl;
}
