package iasa.sc.site.Backend.service.impl;

import iasa.sc.site.Backend.dto.PrintDto;
import iasa.sc.site.Backend.dto.mappers.PrintMapper;
import iasa.sc.site.Backend.entity.Print;
import iasa.sc.site.Backend.exceptions.UnknownIdException;
import iasa.sc.site.Backend.exceptions.ValidationException;
import iasa.sc.site.Backend.repository.PrintRepository;
import iasa.sc.site.Backend.service.PrintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrintServiceImpl implements PrintService {
    private final PrintRepository printRepository;

    @Override
    public ResponseEntity<List<PrintDto>> getAllPrints() {
        List<PrintDto> responseBody = printRepository
                .findAll()
                .stream()
                .map(PrintMapper.INSTANCE::printToDto)
                .toList();
        return new ResponseEntity<>(responseBody, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<Void> deletePrint(String id) {
        try {
            printRepository.deleteById(Integer.parseInt(id));
        } catch (Exception e) {
            throw new UnknownIdException("Id doesn`t exist in the database");
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> addNewPrint(PrintDto printDto) {
        Print inputPrint = PrintMapper.INSTANCE.printDtoToPrint(printDto);
        try {
            printRepository.save(inputPrint);
        } catch (Exception e) {
            throw new ValidationException("Something wrong in object`s fields");
        }
        return ResponseEntity.ok().build();
    }
}
