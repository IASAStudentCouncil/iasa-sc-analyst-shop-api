package iasa.sc.site.Backend.service.impl;

import iasa.sc.site.Backend.dto.PrintDto;
import iasa.sc.site.Backend.dto.mappers.PrintMapper;
import iasa.sc.site.Backend.entity.Print;
import iasa.sc.site.Backend.exceptions.UnknownIdException;
import iasa.sc.site.Backend.exceptions.ValidationException;
import iasa.sc.site.Backend.repository.PrintRepository;
import iasa.sc.site.Backend.service.ImagesService;
import iasa.sc.site.Backend.service.PrintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrintServiceImpl implements PrintService {
    private final PrintRepository printRepository;

    private final ImagesService imagesService;

    @Override
    public ResponseEntity<List<PrintDto>> getAllPrints() {
        List<PrintDto> responseBody = printRepository
                .findAll()
                .stream()
                .map(a -> PrintMapper.INSTANCE.printToDto(a, imagesService))
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
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> addNewPrint(PrintDto printDto) {
        Print inputPrint = PrintMapper.INSTANCE.printDtoToPrint(printDto);
        try {
            Print print = printRepository.save(inputPrint);
            if (printDto.getPhotosUrl() != null) {
                imagesService.saveAll(printDto.getPhotosUrl(), print.getUuid());
            }
        } catch (Exception e) {
            throw new ValidationException("Something wrong in object`s fields");
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }
}
