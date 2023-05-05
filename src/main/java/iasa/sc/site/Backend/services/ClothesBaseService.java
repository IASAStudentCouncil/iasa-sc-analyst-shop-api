package iasa.sc.site.Backend.services;

import iasa.sc.site.Backend.dtos.ClothesBaseDTO;
import iasa.sc.site.Backend.dtos.ClothesBaseInfoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClothesBaseService {
    ResponseEntity<List<ClothesBaseDTO>> getAllClothesBases();

    ResponseEntity<ClothesBaseDTO> getClothesBaseById(String id);

    ResponseEntity<Void> createClothesBase(ClothesBaseDTO clothesBaseDTO, ClothesBaseInfoDTO clothesBaseInfoDto, List<MultipartFile> images);

    ResponseEntity<Void> deleteClothesBaseInfoById(String id);

    ResponseEntity<Void> deleteClothesBaseById(String id);

    ResponseEntity<List<ClothesBaseInfoDTO>> getAllClothesBasesInfo();
}
