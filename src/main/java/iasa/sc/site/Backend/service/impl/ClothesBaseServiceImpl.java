package iasa.sc.site.Backend.service.impl;

import iasa.sc.site.Backend.dto.ClothesBaseDto;
import iasa.sc.site.Backend.dto.mappers.ClothesBaseMapper;
import iasa.sc.site.Backend.entity.ClothesBase;
import iasa.sc.site.Backend.repository.ClothesBaseRepository;
import iasa.sc.site.Backend.service.ClothesBaseService;
import iasa.sc.site.Backend.service.ImageService;
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
}
