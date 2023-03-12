package iasa.sc.site.Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clothes_bases_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClothesBaseImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "photo_url", unique = false, nullable = false)
    private String photoUrl;
    @ManyToOne
    @JoinColumn(name = "base_owner", unique = false, nullable = false)
    private ClothesBaseInfo baseOwner;
}
