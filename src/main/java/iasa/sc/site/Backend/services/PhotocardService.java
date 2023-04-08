package iasa.sc.site.Backend.services;

import iasa.sc.site.Backend.dtos.PhotocardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotocardService {
    ResponseEntity<List<PhotocardDTO>> getAllPhotocards();

    ResponseEntity<PhotocardDTO> getPhotocardById(int id);

    ResponseEntity<Void> addNewPhotocard(PhotocardDTO photocardDTO, MultipartFile image);

    ResponseEntity<Void> deleteAllPhotocards();

    ResponseEntity<Void> deletePhotocardById(int itemId);

    ResponseEntity<Void> updatePhotocardById(int itemId, PhotocardDTO photocardDTO, MultipartFile image);
}
