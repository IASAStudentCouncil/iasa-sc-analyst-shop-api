package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.ClothesBase;
import iasa.sc.site.Backend.entities.enums.ClothesBaseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClothesBaseRepository extends JpaRepository<ClothesBase, Integer> {
    @EntityGraph(attributePaths = {"clothesBaseInfo", "clothesBaseInfo.images"})
    List<ClothesBase> findAll();

    @EntityGraph(attributePaths = {"clothesBaseInfo", "clothesBaseInfo.images"})
    Page<ClothesBase> findAllByType(ClothesBaseType type, Pageable pageable);

    @EntityGraph(attributePaths = {"clothesBaseInfo", "clothesBaseInfo.images"})
    Page<ClothesBase> findAll(Pageable pageable);
}
