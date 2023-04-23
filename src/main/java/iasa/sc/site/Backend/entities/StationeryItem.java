package iasa.sc.site.Backend.entities;

import iasa.sc.site.Backend.entities.enums.StationeryItemType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.util.List;
import java.util.UUID;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "stationery")
public class StationeryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer Id;

    @Column(name = "uuid", nullable = false, unique = true, columnDefinition = "uuid")
    @Generated(value = GenerationTime.INSERT)
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, unique = false)
    private StationeryItemType type;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "price", nullable = false, unique = false)
    private int price;

    @OneToMany
    @JoinColumn(name = "uuid", referencedColumnName = "uuid")
    private List<Image> images;
}