package iasa.sc.site.Backend.services;

import iasa.sc.site.Backend.dtos.PrintDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PrintService {
    ResponseEntity<List<PrintDto>> getAllPrints();

    ResponseEntity<Void> deletePrintById(String id);

    ResponseEntity<Void> addNewPrint(PrintDto printDto, List<MultipartFile> images);

    ResponseEntity<Void> updatePrint(PrintDto printDto, List<MultipartFile> images);

    ResponseEntity<PrintDto> getPrintById(String id);
}
