package iasa.sc.site.Backend.services;

import iasa.sc.site.Backend.dtos.PrintDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PrintService {
    List<PrintDTO> getAllPrints();

    void deletePrintById(String id);

    void addNewPrint(PrintDTO printDto, List<MultipartFile> images);

    void updatePrint(PrintDTO printDto, List<MultipartFile> images);

    void deleteAllPrints();

    PrintDTO getPrintById(String id);
}
