package iasa.sc.site.Backend.dtos.mappers;

import iasa.sc.site.Backend.dtos.PrintDto;
import iasa.sc.site.Backend.entities.Print;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PrintMapper {
    PrintMapper INSTANCE = Mappers.getMapper(PrintMapper.class);

    default PrintDto printToDto(Print print) {
        return new PrintDto(print.getId()
                , print.getPrintType().toString()
                , print.getText()
                , print.getImages()
                , print.getUuid());
    }

    Print printDtoToPrint(PrintDto printDto);
}
