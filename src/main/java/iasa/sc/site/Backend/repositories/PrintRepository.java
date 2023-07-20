package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.Print;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PrintRepository extends JpaRepository<Print, Integer> {
    @EntityGraph(attributePaths = "images")
    List<Print> findAll();
    @EntityGraph(attributePaths = "images")
    Optional<Print> findById(Integer id);
    @Query("SELECT p FROM Print p LEFT JOIN Image i")
    Page<Print> findAll(Pageable pageable);
}
