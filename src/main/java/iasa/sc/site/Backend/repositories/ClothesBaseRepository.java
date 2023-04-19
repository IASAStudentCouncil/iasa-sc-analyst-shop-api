package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.ClothesBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClothesBaseRepository extends JpaRepository<ClothesBase, Integer> {
    @Query("select b from ClothesBase b left join fetch b.clothesBaseInfo")
    List<ClothesBase> findAll();

}
