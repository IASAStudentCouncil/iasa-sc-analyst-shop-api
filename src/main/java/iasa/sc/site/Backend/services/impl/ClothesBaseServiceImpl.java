package iasa.sc.site.Backend.services.impl;

import iasa.sc.site.Backend.dtos.ClothesBaseDTO;
import iasa.sc.site.Backend.dtos.ClothesBaseInfoDTO;
import iasa.sc.site.Backend.dtos.mappers.ClothesBaseMapper;
import iasa.sc.site.Backend.entities.ClothesBase;
import iasa.sc.site.Backend.entities.ClothesBaseInfo;
import iasa.sc.site.Backend.exceptions.UnknownIdException;
import iasa.sc.site.Backend.exceptions.ValidationException;
import iasa.sc.site.Backend.repositories.ClothesBaseInfoRepository;
import iasa.sc.site.Backend.repositories.ClothesBaseRepository;
import iasa.sc.site.Backend.services.ClothesBaseService;
import iasa.sc.site.Backend.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClothesBaseServiceImpl implements ClothesBaseService {
    private final ClothesBaseRepository clothesBaseRepository;

    private final ClothesBaseInfoRepository clothesBaseInfoRepository;

    private final ImageService imageService;

    @Override
    public List<ClothesBaseDTO> getAllClothesBases() {
        List<ClothesBase> responseBody = clothesBaseRepository.findAll();
        return responseBody.stream()
                .map(ClothesBaseMapper.INSTANCE::clothesBaseToClothesBaseDto)
                .toList();
    }

    @Override
    public ClothesBaseDTO getClothesBaseById(String id) {
        ClothesBase clothesBase = clothesBaseRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new NoSuchElementException("No element with this id in database"));
        return ClothesBaseMapper.INSTANCE.clothesBaseToClothesBaseDto(clothesBase);
    }

    @Override
    @Transactional
    public void createClothesBase(ClothesBaseDTO clothesBaseDTO, ClothesBaseInfoDTO clothesBaseInfoDto, List<MultipartFile> images) {
        try {
            ClothesBase clothesBase = ClothesBaseMapper.INSTANCE.clothesBaseDTOToClothesBase(clothesBaseDTO);
            ClothesBaseInfo clothesBaseInfo = ClothesBaseMapper.INSTANCE.clothesBaseInfoDTOToClothesBaseInfo(clothesBaseInfoDto);
            clothesBase = clothesBaseRepository.save(clothesBase);
            clothesBaseInfo.setUuid(UUID.randomUUID());
            clothesBaseInfo.setBase(clothesBase);
            clothesBaseInfo = clothesBaseInfoRepository.save(clothesBaseInfo);
            if (images != null) {
                imageService.saveAllImages(images, clothesBaseInfo.getUuid());
            }
        } catch (Exception e) {
            throw new ValidationException("Bad fields in request");
        }
    }

    @Override
    @Transactional
    public void deleteClothesBaseById(String id) {
        try {
            clothesBaseInfoRepository.deleteAllByClothesBaseId(id);
            clothesBaseRepository.deleteById(Integer.parseInt(id));
        } catch (Exception e) {
            throw new UnknownIdException("No item with this id in database");
        }
    }

    @Override
    public void deleteClothesBaseInfoById(String id) {
        try {
            clothesBaseInfoRepository.deleteById(Integer.parseInt(id));
        } catch (Exception e) {
            throw new UnknownIdException("No item with this id in database");
        }
    }

    @Override
    public List<ClothesBaseInfoDTO> getAllClothesBasesInfo() {
        return clothesBaseInfoRepository.findAll()
                .stream()
                .map(ClothesBaseMapper.INSTANCE::clothesBaseInfoToClothesBaseInfoDto)
                .toList();
    }

    @Override
    public List<ClothesBaseDTO> getAllClothesBasesByType(String type, String page, String limit) {
        int pageNumber = Integer.parseInt(page);
        int pageSize = Integer.parseInt(limit);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clothesBaseRepository
                .findByType(type, pageable)
                .stream()
                .map(ClothesBaseMapper.INSTANCE::clothesBaseToClothesBaseDto)
                .toList();
    }
}
