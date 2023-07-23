package iasa.sc.site.Backend.services.impl;

import iasa.sc.site.Backend.dtos.PrintDTO;
import iasa.sc.site.Backend.dtos.mappers.PrintMapper;
import iasa.sc.site.Backend.entities.Print;
import iasa.sc.site.Backend.entities.enums.PrintType;
import iasa.sc.site.Backend.exceptions.UnexistingTypeException;
import iasa.sc.site.Backend.exceptions.UnknownIdException;
import iasa.sc.site.Backend.exceptions.ValidationException;
import iasa.sc.site.Backend.repositories.PrintRepository;
import iasa.sc.site.Backend.services.ImageService;
import iasa.sc.site.Backend.services.PrintService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrintServiceImpl implements PrintService {
    private final PrintRepository printRepository;

    private final ImageService imageService;

    @Override
    public Page<PrintDTO> getAllPrints(String type, int page, int limit) {
        return printRepository
                .findAllByPrintType(parsePrintType(type), PageRequest.of(page, limit))
                .map(PrintMapper.INSTANCE::printToDto);
    }

    private PrintType parsePrintType(String type) {
        try {
            return PrintType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new UnexistingTypeException(type);
        }
    }

    @Override
    public List<PrintDTO> getAllPrints() {
        return printRepository
                .findAll()
                .stream()
                .map(PrintMapper.INSTANCE::printToDto)
                .toList();
    }

    @Override
    public void deletePrintById(String id) {
        try {
            UUID uuid = printRepository.findById(Integer.parseInt(id)).orElseThrow(RuntimeException::new).getUuid();
            printRepository.deleteById(Integer.parseInt(id));
            imageService.deleteAllImagesByUUID(uuid);
        } catch (Exception e) {
            throw new UnknownIdException("Id doesn't exist in the database");
        }
    }

    @Override
    @Transactional
    public void addNewPrint(PrintDTO printDto, List<MultipartFile> images) {
        try {
            Print inputPrint = PrintMapper.INSTANCE.printDtoToPrint(printDto);
            inputPrint.setUuid(UUID.randomUUID());
            inputPrint = printRepository.save(inputPrint);
            if (images != null) {
                imageService.saveAllImages(images, inputPrint.getUuid());
            }
        } catch (Exception e) {
            throw new ValidationException("Something wrong in object's fields");
        }
    }

    @Override
    public void updatePrint(PrintDTO printDto, List<MultipartFile> images) {
        try {
            Print print = PrintMapper.INSTANCE.printDtoToPrint(printDto);
            print = printRepository.save(print);
            if (images != null) {
                imageService.saveAllImages(images, print.getUuid());
            }
        } catch (Exception e) {
            throw new ValidationException();
        }
    }

    @Override
    public PrintDTO getPrintById(String id) {
        try {
            return PrintMapper.INSTANCE
                    .printToDto(printRepository
                            .findById(Integer.parseInt(id))
                            .orElseThrow(RuntimeException::new));
        } catch (Exception e) {
            throw new UnknownIdException();
        }
    }

    @Override
    @Transactional
    public void deleteAllPrints() {
        List<Print> prints = printRepository.findAll();
        prints.forEach(print -> imageService.deleteAllImagesByUUID(print.getUuid()));
        printRepository.deleteAll();
    }
}
