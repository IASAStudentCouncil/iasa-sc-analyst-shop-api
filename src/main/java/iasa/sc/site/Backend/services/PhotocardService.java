package iasa.sc.site.Backend.services;

import iasa.sc.site.Backend.dtos.PhotocardDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotocardService {
    List<PhotocardDTO> getAllPhotocards();

    Page<PhotocardDTO> getAllPhotocards(String type, int page, int limit);

    PhotocardDTO getPhotocardById(int id);

    void addNewPhotocard(PhotocardDTO photocardDTO, MultipartFile image);

    void deleteAllPhotocards();

    void deletePhotocardById(int itemId);

    void updatePhotocardById(int itemId, PhotocardDTO photocardDTO, MultipartFile image);
}
