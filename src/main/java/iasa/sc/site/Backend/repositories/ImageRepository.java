package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findImagesByUuid(UUID uuid);

    Image findImageByUuid(UUID uuid);

    void deleteAllByUuid(UUID uuid);
}