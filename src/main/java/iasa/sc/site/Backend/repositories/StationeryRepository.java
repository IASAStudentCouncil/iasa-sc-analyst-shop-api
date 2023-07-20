package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.StationeryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StationeryRepository extends JpaRepository<StationeryItem, Integer> {
    @EntityGraph(attributePaths = "images")
    List<StationeryItem> findAll();

    @Query("SELECT s FROM StationeryItem s LEFT JOIN Image i")
    Page<StationeryItem> findAll(Pageable pageable);
}
