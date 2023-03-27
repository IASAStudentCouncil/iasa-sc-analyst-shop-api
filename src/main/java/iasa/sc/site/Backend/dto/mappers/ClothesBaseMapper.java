package iasa.sc.site.Backend.dto.mappers;

import iasa.sc.site.Backend.dto.ClothesBaseDto;
import iasa.sc.site.Backend.dto.ClothesBaseInfoDto;
import iasa.sc.site.Backend.entity.ClothesBase;
import iasa.sc.site.Backend.entity.ClothesBaseInfo;
import iasa.sc.site.Backend.service.ImagesService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ClothesBaseMapper {
    ClothesBaseMapper INSTANCE = Mappers.getMapper(ClothesBaseMapper.class);

    ClothesBase clothesBaseDtoToClothesBase(ClothesBaseDto dto);

    default ClothesBaseDto clothesBaseToClothesBaseDto(ClothesBase clothesBase, ImagesService imagesService){
        return new ClothesBaseDto(clothesBase.getId(),
                clothesBase.getType(),
                clothesBase.getPrice(),
                clothesBase.getClothesBaseInfo().stream().map(info -> clothesBaseInfoToClothesBaseInfoDto(info,imagesService)).collect(Collectors.toList())
                , clothesBase.getText());
    }

    ClothesBaseInfo clothesBaseInfoDtoToClothesBaseInfo(ClothesBaseInfoDto clothesBaseInfoDto);

    default ClothesBaseInfoDto clothesBaseInfoToClothesBaseInfoDto(ClothesBaseInfo clothesBaseInfo
            , ImagesService imagesService) {
        return new ClothesBaseInfoDto(clothesBaseInfo.getId(),
                clothesBaseInfo.getCountOnStorage(),
                clothesBaseInfo.getColor(),
                clothesBaseInfo.getClothesBaseSize(),
                imagesService.getAllPhotosByUUID(clothesBaseInfo.getUuid()));
    }
}
