package iasa.sc.site.Backend.services.impl;

import iasa.sc.site.Backend.dtos.ClothesBaseDTO;
import iasa.sc.site.Backend.dtos.mappers.ClothesBaseMapper;
import iasa.sc.site.Backend.entities.ClothesBase;
import iasa.sc.site.Backend.repositories.ClothesBaseRepository;
import iasa.sc.site.Backend.services.ClothesBaseService;
import iasa.sc.site.Backend.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClothesBaseServiceImpl implements ClothesBaseService {
    private final ClothesBaseRepository clothesBaseRepository;

    private final ImageService imageService;

    @Override
    public ResponseEntity<List<ClothesBaseDto>> getAllClothesBases() {
        List<ClothesBase> responseBody = clothesBaseRepository
                .findAll();
        List<ClothesBaseDto> dtos = responseBody
                .stream()
                .map(clothesBase -> ClothesBaseMapper.INSTANCE.clothesBaseToClothesBaseDto(clothesBase, imageService))
                .toList();
        return ResponseEntity.ok(dtos);
    }
//    public ResponseEntity<>
}
