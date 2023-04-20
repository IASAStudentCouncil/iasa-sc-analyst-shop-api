package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.StationeryItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationeryRepository extends JpaRepository<StationeryItem, Integer> {
    @EntityGraph(attributePaths = "images")
    List<StationeryItem> findAll();
}
