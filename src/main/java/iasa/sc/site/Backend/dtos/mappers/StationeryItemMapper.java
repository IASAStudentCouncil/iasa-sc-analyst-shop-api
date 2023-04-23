package iasa.sc.site.Backend.dtos.mappers;

import iasa.sc.site.Backend.dtos.StationeryItemDTO;
import iasa.sc.site.Backend.entities.StationeryItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StationeryItemMapper {
    StationeryItemMapper INSTANCE = Mappers.getMapper(StationeryItemMapper.class);

    StationeryItemDTO stationeryItemToDTO(StationeryItem stationeryItem);

    StationeryItem stationeryItemDTOToStationeryItem(StationeryItemDTO stationeryItemDto);

}

