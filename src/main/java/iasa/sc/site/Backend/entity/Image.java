package iasa.sc.site.Backend.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @JsonAlias("id")
    private Integer id;

    @Column(name = "photo_url", unique = false, nullable = false)
    @JsonAlias("photo_url")
    private String photoUrl;

    @Column(name = "uuid", unique = false, nullable = false)
    @JsonAlias("uuid")
    private UUID uuid;
}
