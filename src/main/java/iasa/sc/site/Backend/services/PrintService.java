package iasa.sc.site.Backend.services;

import iasa.sc.site.Backend.dtos.PrintDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PrintService {
    ResponseEntity<List<PrintDTO>> getAllPrints();

    ResponseEntity<Void> deletePrintById(String id);

    ResponseEntity<Void> addNewPrint(PrintDTO printDto, List<MultipartFile> images);

    ResponseEntity<Void> updatePrint(PrintDTO printDto, List<MultipartFile> images);

    ResponseEntity<Void> deleteAllPrints();

    ResponseEntity<PrintDTO> getPrintById(String id);
}
