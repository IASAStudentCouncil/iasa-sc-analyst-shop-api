package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.StationeryItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationeryRepository extends JpaRepository<StationeryItem, Integer> {
    @EntityGraph(attributePaths = "images")
    List<StationeryItem> findAll();
}
