package iasa.sc.site.Backend.repositories;

import iasa.sc.site.Backend.entities.ClothesBaseInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClothesBaseInfoRepository extends JpaRepository<ClothesBaseInfo, Integer> {
    @Modifying
    @Query("delete from ClothesBaseInfo i where i.base.id=:id")
    void deleteAllByClothesBaseId(@Param("id") String id);

    @EntityGraph(attributePaths = "images")
    List<ClothesBaseInfo> findAll();
}
