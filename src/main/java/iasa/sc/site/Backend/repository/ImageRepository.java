package iasa.sc.site.Backend.repository;

import iasa.sc.site.Backend.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    @Query("select i from Image i where i.uuid=:uuid")
    List<Image> findByUUID(@Param("uuid") UUID uuid);

    Image findImageByUuid(UUID uuid);

    void deleteAllByUuid(UUID uuid);
}