package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.Photocard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotocardRepository extends JpaRepository<Photocard, Integer> {
    @EntityGraph(attributePaths = "image")
    List<Photocard> findAll();
    @Query("SELECT p FROM Photocard p LEFT JOIN Image i")
    Page<Photocard> findAll(Pageable pageable);
}
