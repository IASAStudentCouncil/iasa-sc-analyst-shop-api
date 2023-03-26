package iasa.sc.site.Backend.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column(name = "image_url", unique = false, nullable = false)
    @JsonProperty("image_url")
    private String imageURL;

    @Column(name = "uuid", unique = false, nullable = false)
    @JsonAlias("uuid")
    private UUID uuid;
}