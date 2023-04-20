package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.Print;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrintRepository extends JpaRepository<Print, Integer> {
    @Query("select p from Print p left join fetch p.images")
    List<Print> findAll();
}
