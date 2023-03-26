package iasa.sc.site.Backend.dto.mappers;

import iasa.sc.site.Backend.dto.PhotocardDTO;
import iasa.sc.site.Backend.entity.Photocard;
import iasa.sc.site.Backend.service.ImageService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PhotocardMapper {
    PhotocardMapper INSTANCE = Mappers.getMapper(PhotocardMapper.class);

    default PhotocardDTO photocardToDto(Photocard photocard, ImageService imageService) {
        return new PhotocardDTO(
                photocard.getId(),
                photocard.getType(),
                imageService.getAllImagesByUUID(photocard.getUuid())
        );
    }

    Photocard DTOToPhotocard(PhotocardDTO photocardDTO);
}
