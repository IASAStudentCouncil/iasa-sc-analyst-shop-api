package iasa.sc.site.Backend.dtos.mappers;

import iasa.sc.site.Backend.dtos.StationeryItemDTO;
import iasa.sc.site.Backend.entities.StationeryItem;
import iasa.sc.site.Backend.services.ImageService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StationeryItemMapper {
    StationeryItemMapper INSTANCE = Mappers.getMapper(StationeryItemMapper.class);

    default StationeryItemDTO stationeryItemToDTO(StationeryItem stationeryItem, ImageService imageService) {
        return new StationeryItemDTO(
                stationeryItem.getId(),
                stationeryItem.getType().toString(),
                stationeryItem.getName(),
                stationeryItem.getPrice(),
                imageService.getAllImagesByUUID(stationeryItem.getUuid())
        );
    }

    StationeryItem stationeryItemDTOToStationeryItem(StationeryItemDTO stationeryItemDto);

}

