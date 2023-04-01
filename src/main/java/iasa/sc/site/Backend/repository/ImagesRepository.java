package iasa.sc.site.Backend.repository;

import iasa.sc.site.Backend.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ImagesRepository extends JpaRepository<Image, Integer> {
    @Query("select i from Image i where i.uuid=:uuid")
    List<Image> findByUUID(@Param("uuid") UUID uuid);
}
