package iasa.sc.site.Backend.services;

import iasa.sc.site.Backend.dtos.PrintDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PrintService {
    List<PrintDTO> getAllPrints();

    Page<PrintDTO> getAllPrints(String type, int page, int limit);

    void deletePrintById(String id);

    void addNewPrint(PrintDTO printDto, List<MultipartFile> images);

    void updatePrint(int printId, PrintDTO printDto, List<MultipartFile> images);

    void deleteAllPrints();

    PrintDTO getPrintById(String id);
}
