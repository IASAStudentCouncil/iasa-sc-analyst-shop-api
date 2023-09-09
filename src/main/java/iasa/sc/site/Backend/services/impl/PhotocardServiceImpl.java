package iasa.sc.site.Backend.services.impl;

import iasa.sc.site.Backend.dtos.PhotocardDTO;
import iasa.sc.site.Backend.dtos.mappers.PhotocardMapper;
import iasa.sc.site.Backend.entities.Photocard;
import iasa.sc.site.Backend.entities.enums.PhotocardType;
import iasa.sc.site.Backend.exceptions.UnexistingTypeException;
import iasa.sc.site.Backend.exceptions.UnknownIdException;
import iasa.sc.site.Backend.exceptions.ValidationException;
import iasa.sc.site.Backend.repositories.PhotocardRepository;
import iasa.sc.site.Backend.services.ImageService;
import iasa.sc.site.Backend.services.PhotocardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotocardServiceImpl implements PhotocardService {
    private final PhotocardRepository photocardRepository;

    private final ImageService imageService;

    @Override
    public List<PhotocardDTO> getAllPhotocards() {
        return photocardRepository
                .findAll()
                .stream()
                .map(photocard -> new PhotocardDTO(
                        photocard.getId(),
                        photocard.getType().toString(),
                        imageService.getImageByUUID(photocard.getUuid())))
                .toList();
    }

    @Override
    public Page<PhotocardDTO> getAllPhotocards(String type, int page, int limit) {
        if (type == null) {
            return photocardRepository.findAll(PageRequest.of(page, limit))
                    .map(PhotocardMapper.INSTANCE::photocardToDto);
        }
        return photocardRepository
                .findAllByType(parsePhotocardType(type), PageRequest.of(page, limit))
                .map(PhotocardMapper.INSTANCE::photocardToDto);
    }

    private PhotocardType parsePhotocardType(String type) {
        try {
            return PhotocardType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new UnexistingTypeException(type);
        }
    }

    @Override
    public PhotocardDTO getPhotocardById(int id) {
        return photocardRepository
                .findById(id)
                .map(PhotocardMapper.INSTANCE::photocardToDto)
                .orElseThrow(UnknownIdException::new);
    }

    @Override
    public void addNewPhotocard(PhotocardDTO photocardDTO, MultipartFile image) {
        try {
            Photocard photocard = PhotocardMapper.INSTANCE.DTOToPhotocard(photocardDTO);
            photocard.setUuid(UUID.randomUUID());

            photocard = photocardRepository.save(photocard);
            if (image != null) {
                imageService.saveImage(image, photocard.getUuid());
            }
        } catch (Exception e) {
            throw new ValidationException();
        }
    }

    @Override
    @Transactional
    public void deleteAllPhotocards() {
        photocardRepository
                .findAll()
                .forEach(image -> imageService.deleteAllImagesByUUID(image.getUuid()));
        photocardRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deletePhotocardById(int itemId) {
        Photocard photocard = photocardRepository.findById(itemId).orElseThrow(UnknownIdException::new);
        photocardRepository.delete(photocard);
        imageService.deleteAllImagesByUUID(photocard.getUuid());
    }

    @Override
    @Transactional
    public void updatePhotocardById(int photocardId, PhotocardDTO photocardDTO, MultipartFile image) {
        Photocard photocardEntity = photocardRepository.findById(photocardId).orElseThrow(UnknownIdException::new);
        try {
            Photocard photocard = PhotocardMapper.INSTANCE.DTOToPhotocard(photocardDTO);
            photocardEntity.setType(photocard.getType());
        } catch (Exception e) {
            throw new ValidationException();
        }
        if (image != null) {
            imageService.deleteAllImagesByUUID(photocardEntity.getUuid());
            imageService.saveImage(image, photocardEntity.getUuid());
        }
        photocardRepository.save(photocardEntity);
    }
}