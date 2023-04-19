package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.Photocard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotocardRepository extends JpaRepository<Photocard, Integer> {
}
