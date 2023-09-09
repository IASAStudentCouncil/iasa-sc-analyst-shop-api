package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.Photocard;
import iasa.sc.site.Backend.entities.enums.PhotocardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotocardRepository extends JpaRepository<Photocard, Integer> {
    @EntityGraph(attributePaths = "image")
    List<Photocard> findAll();

    @EntityGraph(attributePaths = "image")
    Page<Photocard> findAllByType(PhotocardType photocardType, Pageable pageable);

    @EntityGraph(attributePaths = "image")
    Page<Photocard> findAll(Pageable pageable);
}
