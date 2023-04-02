package iasa.sc.site.Backend.dto.mappers;

import iasa.sc.site.Backend.dto.PrintDto;
import iasa.sc.site.Backend.entity.Print;
import iasa.sc.site.Backend.service.ImageService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PrintMapper {
    PrintMapper INSTANCE = Mappers.getMapper(PrintMapper.class);

    default PrintDto printToDto(Print print, ImageService imagesService) {
        return new PrintDto(print.getId()
                , print.getPrintType().toString()
                , print.getText()
                , imagesService.getAllImagesByUUID(print.getUuid()));
    }

    Print printDtoToPrint(PrintDto printDto);
}
