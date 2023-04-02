package iasa.sc.site.Backend.service;

import iasa.sc.site.Backend.dto.PrintDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PrintService {
    ResponseEntity<List<PrintDto>> getAllPrints();

    ResponseEntity<Void> deletePrint(String id);

    ResponseEntity<Void> addNewPrint(PrintDto printDto);
}
