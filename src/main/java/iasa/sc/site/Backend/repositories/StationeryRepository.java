package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.StationeryItem;
import iasa.sc.site.Backend.entities.enums.StationeryItemType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationeryRepository extends JpaRepository<StationeryItem, Integer> {
    @EntityGraph(attributePaths = "images")
    List<StationeryItem> findAll();

    @EntityGraph(attributePaths = "images")
    Page<StationeryItem> findAllByType(StationeryItemType stationeryItemType, Pageable pageable);

    @EntityGraph(attributePaths = "images")
    Page<StationeryItem> findAll(Pageable pageable);
}
