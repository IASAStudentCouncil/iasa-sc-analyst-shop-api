package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.StationeryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StationeryRepository extends JpaRepository<StationeryItem, Integer> {
    @Query("select item from StationeryItem item left join fetch item.images")
    List<StationeryItem> findAll();
}
