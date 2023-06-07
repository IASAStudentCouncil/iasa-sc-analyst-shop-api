package iasa.sc.site.Backend.services;

import iasa.sc.site.Backend.dtos.ClothesBaseDTO;
import iasa.sc.site.Backend.dtos.ClothesBaseInfoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClothesBaseService {
    List<ClothesBaseDTO> getAllClothesBases();

    ClothesBaseDTO getClothesBaseById(String id);

    void createClothesBase(ClothesBaseDTO clothesBaseDTO, ClothesBaseInfoDTO clothesBaseInfoDto, List<MultipartFile> images);

    void deleteClothesBaseInfoById(String id);

    void deleteClothesBaseById(String id);

    List<ClothesBaseInfoDTO> getAllClothesBasesInfo();
}
