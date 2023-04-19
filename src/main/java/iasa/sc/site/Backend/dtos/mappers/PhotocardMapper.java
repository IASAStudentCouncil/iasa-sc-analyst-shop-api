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

    default PhotocardDTO photocardToDto(Photocard photocard, List<Image> images) {
        return new PhotocardDTO(
                photocard.getId(),
                photocard.getType().toString(),
                images.stream().filter(item -> item.getUuid().equals(photocard.getUuid())).findAny().get()
        );
    }

    Photocard DTOToPhotocard(PhotocardDTO photocardDTO);
}
