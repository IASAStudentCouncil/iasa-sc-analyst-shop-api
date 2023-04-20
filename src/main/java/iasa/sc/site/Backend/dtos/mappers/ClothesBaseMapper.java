package iasa.sc.site.Backend.dtos.mappers;

import iasa.sc.site.Backend.dtos.ClothesBaseDTO;
import iasa.sc.site.Backend.dtos.ClothesBaseInfoDto;
import iasa.sc.site.Backend.entities.ClothesBase;
import iasa.sc.site.Backend.entities.ClothesBaseInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ClothesBaseMapper {
    ClothesBaseMapper INSTANCE = Mappers.getMapper(ClothesBaseMapper.class);

    ClothesBase clothesBaseDTOToClothesBase(ClothesBaseDTO dto);

    default ClothesBaseDTO clothesBaseToClothesBaseDto(ClothesBase clothesBase) {
        return new ClothesBaseDTO(clothesBase.getId(),
                clothesBase.getType(),
                clothesBase.getPrice(),
                clothesBase.getClothesBaseInfo().stream().map(this::clothesBaseInfoToClothesBaseInfoDto).collect(Collectors.toList())
                , clothesBase.getText());
    }

    ClothesBaseInfo clothesBaseInfoDTOToClothesBaseInfo(ClothesBaseInfoDto clothesBaseInfoDto);

    default ClothesBaseInfoDto clothesBaseInfoToClothesBaseInfoDto(ClothesBaseInfo clothesBaseInfo) {
        return new ClothesBaseInfoDto(clothesBaseInfo.getId(),
                clothesBaseInfo.getCountOnStorage(),
                clothesBaseInfo.getColor(),
                clothesBaseInfo.getClothesBaseSize(),
                clothesBaseInfo.getImages(),
                clothesBaseInfo.getUuid());
    }
}
