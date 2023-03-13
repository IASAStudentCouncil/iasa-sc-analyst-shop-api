package iasa.sc.site.Backend.dto.mappers;

import iasa.sc.site.Backend.dto.PrintDto;
import iasa.sc.site.Backend.entity.Print;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PrintMapper {
    PrintMapper INSTANCE = Mappers.getMapper(PrintMapper.class);

    PrintDto printToDto(Print print);

    Print printDtoToPrint(PrintDto printDto);
}
