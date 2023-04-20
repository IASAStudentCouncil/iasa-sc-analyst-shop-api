package iasa.sc.site.Backend.dtos.mappers;

import iasa.sc.site.Backend.dtos.PhotocardDTO;
import iasa.sc.site.Backend.entities.Image;
import iasa.sc.site.Backend.entities.Photocard;
import iasa.sc.site.Backend.services.ImageService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhotocardMapper {
    PhotocardMapper INSTANCE = Mappers.getMapper(PhotocardMapper.class);

    PhotocardDTO photocardToDto(Photocard photocard);

    Photocard DTOToPhotocard(PhotocardDTO photocardDTO);
}
