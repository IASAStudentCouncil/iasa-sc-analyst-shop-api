package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.Print;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrintRepository extends JpaRepository<Print, Integer> {
    @EntityGraph(attributePaths = "images")
    List<Print> findAll();
    @EntityGraph(attributePaths = "images")
    Optional<Print> findById(Integer id);
}
