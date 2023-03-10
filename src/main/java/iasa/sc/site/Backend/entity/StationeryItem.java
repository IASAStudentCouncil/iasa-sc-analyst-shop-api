package iasa.sc.site.Backend.entity;

import iasa.sc.site.Backend.entity.enums.StationeryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "stationery")
public class StationeryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private StationeryType type;
    private String name;
    private String photoUrl;
    private int price;
}
