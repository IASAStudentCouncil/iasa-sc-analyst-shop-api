package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.ClothesBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClothesBaseRepository extends JpaRepository<ClothesBase, Integer> {
    @EntityGraph(attributePaths = {"clothesBaseInfo", "clothesBaseInfo.images"})
    List<ClothesBase> findAll();

    @Query("SELECT c FROM ClothesBase c LEFT JOIN ClothesBaseInfo ci WHERE c.type=:type")
    Page<ClothesBase> findByType(@Param("type") String type, Pageable pageable);
}
