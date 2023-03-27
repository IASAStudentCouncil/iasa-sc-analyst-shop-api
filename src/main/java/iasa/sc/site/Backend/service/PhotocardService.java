package iasa.sc.site.Backend.service;

import iasa.sc.site.Backend.dto.PhotocardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotocardService {
    ResponseEntity<List<PhotocardDTO>> getAll();

    ResponseEntity<PhotocardDTO> get(int id);

    ResponseEntity<Void> add(PhotocardDTO photocardDTO, MultipartFile image);

    ResponseEntity<Void> deleteAllItems();

    ResponseEntity<Void> deleteById(int itemId);

    ResponseEntity<Void> editById(int itemId, PhotocardDTO photocardDTO);
}
