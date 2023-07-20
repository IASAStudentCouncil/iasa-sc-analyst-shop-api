package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.Photocard;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotocardRepository extends JpaRepository<Photocard, Integer> {
    @EntityGraph(attributePaths = "image")
    List<Photocard> findAll();
}
