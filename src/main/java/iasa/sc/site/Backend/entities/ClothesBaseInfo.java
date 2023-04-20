package iasa.sc.site.Backend.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import iasa.sc.site.Backend.entities.enums.ClothesBaseColor;
import iasa.sc.site.Backend.entities.enums.ClothesBaseSize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "clothes_bases_info")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonIgnoreProperties({"id", "base"})
public class ClothesBaseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @JsonAlias("count_on_storage")
    @Column(name = "count_on_storage", unique = false, nullable = true)
    private Integer countOnStorage;

    @JsonAlias("color")
    @Enumerated(EnumType.STRING)
    @Column(name = "color", unique = false, nullable = false)
    private ClothesBaseColor color;

    @JsonAlias("size")
    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false, unique = false)
    private ClothesBaseSize clothesBaseSize;

    @Column(name = "uuid", unique = true, nullable = false, columnDefinition = "uuid")
    @Generated(value = GenerationTime.INSERT)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "clothes_base_id", unique = false, nullable = false)
    private ClothesBase base;

    @OneToMany
    @JoinColumn(name = "uuid", referencedColumnName = "uuid")
    private Set<Image> images;
}
