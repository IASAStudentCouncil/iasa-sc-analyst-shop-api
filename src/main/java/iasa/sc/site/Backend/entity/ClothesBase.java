package iasa.sc.site.Backend.entity;

import iasa.sc.site.Backend.entity.enums.ClothesBaseType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "clothes_bases")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClothesBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "text", unique = false, nullable = true)
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, unique = false)
    private ClothesBaseType type;
    @Column(name = "price", nullable = false, unique = false)
    private Integer price;
    @OneToMany(mappedBy = "base")
    private List<ClothesBaseInfo> clothesBaseInfo;

}
