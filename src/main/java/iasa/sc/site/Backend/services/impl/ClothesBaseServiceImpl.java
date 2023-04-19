package iasa.sc.site.Backend.services.impl;

import iasa.sc.site.Backend.dtos.ClothesBaseDTO;
import iasa.sc.site.Backend.dtos.ClothesBaseInfoDto;
import iasa.sc.site.Backend.dtos.mappers.ClothesBaseMapper;
import iasa.sc.site.Backend.entities.ClothesBase;
import iasa.sc.site.Backend.entities.ClothesBaseInfo;
import iasa.sc.site.Backend.entities.Image;
import iasa.sc.site.Backend.exceptions.UnknownIdException;
import iasa.sc.site.Backend.exceptions.ValidationException;
import iasa.sc.site.Backend.repositories.ClothesBaseInfoRepository;
import iasa.sc.site.Backend.repositories.ClothesBaseRepository;
import iasa.sc.site.Backend.services.ClothesBaseService;
import iasa.sc.site.Backend.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ClothesBaseServiceImpl implements ClothesBaseService {
    private final ClothesBaseRepository clothesBaseRepository;

    private final ClothesBaseInfoRepository clothesBaseInfoRepository;

    private final ImageService imageService;

    @Override
    public ResponseEntity<List<ClothesBaseDto>> getAllClothesBases() {
        List<ClothesBase> responseBody = clothesBaseRepository
                .findAll();
        List<ClothesBaseDto> dtos = responseBody
                .stream()
                .map(clothesBase -> ClothesBaseMapper.INSTANCE.clothesBaseToClothesBaseDto(clothesBase, images))
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<ClothesBaseDTO> getClothesBaseById(String id) {
        ClothesBase clothesBase = clothesBaseRepository
                .findById(Integer.parseInt(id))
                .orElseThrow(() -> new NoSuchElementException("No element with this id in database"));
        List<Image> images = imageService.getAllImages();
        ClothesBaseDTO clothesBaseDTO = ClothesBaseMapper.INSTANCE.clothesBaseToClothesBaseDto(clothesBase, images);
        return new ResponseEntity<>(clothesBaseDTO, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> createClothesBase(ClothesBaseDTO clothesBaseDTO, ClothesBaseInfoDto clothesBaseInfoDto, List<MultipartFile> images) {
        try {
            ClothesBase clothesBase = ClothesBaseMapper.INSTANCE.clothesBaseDTOToClothesBase(clothesBaseDTO);
            ClothesBaseInfo clothesBaseInfo = ClothesBaseMapper.INSTANCE.clothesBaseInfoDTOToClothesBaseInfo(clothesBaseInfoDto);
            clothesBase = clothesBaseRepository.save(clothesBase);
            clothesBaseInfo.setBase(clothesBase);
            clothesBaseInfo = clothesBaseInfoRepository.save(clothesBaseInfo);
            if (images != null) {
                imageService.saveAllImages(images, clothesBaseInfo.getUuid());
            }
        } catch (Exception e) {
            throw new ValidationException("Bad fields in request");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteClothesBaseById(String id) {
        try {
            clothesBaseInfoRepository.deleteAllByClothesBaseId(id);
            clothesBaseRepository.deleteById(Integer.parseInt(id));
        } catch (Exception e) {
            throw new UnknownIdException("No item with this id in database");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteClothesBaseInfoById(String id) {
        try {
            clothesBaseInfoRepository.deleteById(Integer.parseInt(id));
        } catch (Exception e) {
            throw new UnknownIdException("No item with this id in database");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
