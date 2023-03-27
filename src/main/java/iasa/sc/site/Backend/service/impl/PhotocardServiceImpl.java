package iasa.sc.site.Backend.service.impl;

import iasa.sc.site.Backend.dto.PhotocardDTO;
import iasa.sc.site.Backend.dto.mappers.PhotocardMapper;
import iasa.sc.site.Backend.entity.Photocard;
import iasa.sc.site.Backend.exceptions.UnknownIdException;
import iasa.sc.site.Backend.exceptions.ValidationException;
import iasa.sc.site.Backend.repository.PhotocardRepository;
import iasa.sc.site.Backend.service.ImageService;
import iasa.sc.site.Backend.service.PhotocardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotocardServiceImpl implements PhotocardService {

    private final PhotocardRepository photocardRepository;
    private final ImageService imageService;

    @Override
    public ResponseEntity<List<PhotocardDTO>> getAll() {
        List<PhotocardDTO> responseBody = photocardRepository
                .findAll()
                .stream()
                .map(photocard -> PhotocardMapper.INSTANCE.photocardToDto(photocard, imageService))
                .toList();

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PhotocardDTO> get(int id) {
        PhotocardDTO responseBody = photocardRepository
                .findById(id)
                .map(photocard -> PhotocardMapper.INSTANCE.photocardToDto(photocard, imageService))
                .orElseThrow(UnknownIdException::new);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> add(PhotocardDTO photocardDTO, MultipartFile image) {

        try {
            Photocard photocard = PhotocardMapper.INSTANCE.DTOToPhotocard(photocardDTO);

            photocardRepository.save(photocard);

            imageService.saveImage(image, photocard.getUuid());
        } catch (Exception e) {
            throw new ValidationException();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> deleteAllItems() {
        photocardRepository.deleteAll();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteById(int itemId) {
        Photocard photocard = photocardRepository.findById(itemId).orElseThrow(UnknownIdException::new);

        photocardRepository.delete(photocard);

        imageService.deleteAllImagesByUUID(photocard.getUuid());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> editById(int photocardId, PhotocardDTO photocardDTO) {
        Photocard photocard = PhotocardMapper.INSTANCE.DTOToPhotocard(photocardDTO);

        Photocard photocardEntity = photocardRepository.findById(photocardId).orElseThrow(UnknownIdException::new);

        try {
            photocardEntity.setType(photocard.getType());
        } catch (Exception e) {
            throw new ValidationException();
        }

        photocardRepository.save(photocardEntity);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
