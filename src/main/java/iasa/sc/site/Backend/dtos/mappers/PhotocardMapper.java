package iasa.sc.site.Backend.dtos.mappers;

import iasa.sc.site.Backend.dtos.PhotocardDTO;
import iasa.sc.site.Backend.entities.Photocard;
import iasa.sc.site.Backend.services.ImageService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PhotocardMapper {
    PhotocardMapper INSTANCE = Mappers.getMapper(PhotocardMapper.class);

    default PhotocardDTO photocardToDto(Photocard photocard, ImageService imageService) {
        return new PhotocardDTO(
                photocard.getId(),
                photocard.getType().toString(),
                imageService.getImageByUUID(photocard.getUuid())
        );
    }

    Photocard DTOToPhotocard(PhotocardDTO photocardDTO);
}
