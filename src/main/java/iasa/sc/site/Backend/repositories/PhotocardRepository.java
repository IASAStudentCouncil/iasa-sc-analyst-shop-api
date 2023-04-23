package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.Photocard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotocardRepository extends JpaRepository<Photocard, Integer> {
    @Query("select p from Photocard p left join fetch p.image")
    List<Photocard> findAll();
}
