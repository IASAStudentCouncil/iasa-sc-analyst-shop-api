package iasa.sc.site.Backend.repository;

import iasa.sc.site.Backend.entity.Photocard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotocardRepository extends JpaRepository<Photocard, Integer> {
}
