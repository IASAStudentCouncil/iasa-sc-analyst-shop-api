package iasa.sc.site.Backend.repository;

import iasa.sc.site.Backend.entity.StationeryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationeryRepository extends JpaRepository<StationeryItem, Integer> {

}
