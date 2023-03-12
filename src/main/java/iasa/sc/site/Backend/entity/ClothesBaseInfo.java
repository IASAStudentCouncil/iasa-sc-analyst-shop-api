package iasa.sc.site.Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "clothes_bases_info")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ClothesBaseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "count_on_storage", unique = false, nullable = true)
    private Integer countOnStorage;
    @Column(name = "color", unique = false, nullable = false)
    private String color;
    @OneToMany(mappedBy = "baseOwner")
    private List<ClothesBaseImage> images;
    @ManyToOne
    @JoinColumn(name = "clothes_base_id", unique = false, nullable = false)
    private ClothesBase base;

}
