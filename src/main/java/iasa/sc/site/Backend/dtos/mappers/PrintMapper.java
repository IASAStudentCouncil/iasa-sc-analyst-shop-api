package iasa.sc.site.Backend.dtos.mappers;

import iasa.sc.site.Backend.dtos.PrintDTO;
import iasa.sc.site.Backend.entities.Print;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PrintMapper {
    PrintMapper INSTANCE = Mappers.getMapper(PrintMapper.class);

    PrintDTO printToDto(Print print);

    Print printDtoToPrint(PrintDTO printDto);
}
