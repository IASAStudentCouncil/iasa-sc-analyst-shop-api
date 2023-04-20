package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.ClothesBase;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClothesBaseRepository extends JpaRepository<ClothesBase, Integer> {
    @EntityGraph(attributePaths = {"clothesBaseInfo", "clothesBaseInfo.images"})
    List<ClothesBase> findAll();

}
