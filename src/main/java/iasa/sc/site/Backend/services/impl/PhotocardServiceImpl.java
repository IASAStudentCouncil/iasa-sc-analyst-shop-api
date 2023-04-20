package iasa.sc.site.Backend.services.impl;

import iasa.sc.site.Backend.dtos.PhotocardDTO;
import iasa.sc.site.Backend.dtos.mappers.PhotocardMapper;
import iasa.sc.site.Backend.entities.Image;
import iasa.sc.site.Backend.entities.Photocard;
import iasa.sc.site.Backend.exceptions.UnknownIdException;
import iasa.sc.site.Backend.exceptions.ValidationException;
import iasa.sc.site.Backend.repositories.PhotocardRepository;
import iasa.sc.site.Backend.services.ImageService;
import iasa.sc.site.Backend.services.PhotocardService;
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
    public ResponseEntity<List<PhotocardDTO>> getAllPhotocards() {
        List<PhotocardDTO> responseBody = photocardRepository
                .findAll()
                .stream()
                .map(PhotocardMapper.INSTANCE::photocardToDto)
                .toList();

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PhotocardDTO> getPhotocardById(int id) {
        PhotocardDTO responseBody = photocardRepository
                .findById(id)
                .map(PhotocardMapper.INSTANCE::photocardToDto)
                .orElseThrow(UnknownIdException::new);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addNewPhotocard(PhotocardDTO photocardDTO, MultipartFile image) {

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
    @Transactional
    public ResponseEntity<Void> deleteAllPhotocards() {
        photocardRepository.deleteAll();

        imageService.deleteAllImages();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deletePhotocardById(int itemId) {
        Photocard photocard = photocardRepository.findById(itemId).orElseThrow(UnknownIdException::new);

        photocardRepository.delete(photocard);

        imageService.deleteAllImagesByUUID(photocard.getUuid());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> updatePhotocardById(int photocardId, PhotocardDTO photocardDTO, MultipartFile image) {
        Photocard photocardEntity = photocardRepository.findById(photocardId).orElseThrow(UnknownIdException::new);

        try {
            Photocard photocard = PhotocardMapper.INSTANCE.DTOToPhotocard(photocardDTO);

            photocardEntity.setType(photocard.getType());
        } catch (Exception e) {
            throw new ValidationException();
        }

        imageService.deleteAllImagesByUUID(photocardEntity.getUuid());
        imageService.saveImage(image, photocardEntity.getUuid());

        photocardRepository.save(photocardEntity);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
