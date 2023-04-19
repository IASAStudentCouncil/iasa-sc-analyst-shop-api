package iasa.sc.site.Backend.services.impl;

import iasa.sc.site.Backend.dtos.StationeryItemDTO;
import iasa.sc.site.Backend.dtos.mappers.StationeryItemMapper;
import iasa.sc.site.Backend.entities.Image;
import iasa.sc.site.Backend.entities.StationeryItem;
import iasa.sc.site.Backend.exceptions.UnknownIdException;
import iasa.sc.site.Backend.exceptions.ValidationException;
import iasa.sc.site.Backend.repositories.StationeryRepository;
import iasa.sc.site.Backend.services.ImageService;
import iasa.sc.site.Backend.services.StationeryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationeryServiceImpl implements StationeryService {
    private final StationeryRepository stationeryRepository;

    private final ImageService imageService;

    @Override
    public ResponseEntity<List<StationeryItemDTO>> getAllStationeryItems() {
        List<Image> images = imageService.getAllImages();

        List<StationeryItemDTO> responseBody = stationeryRepository
                .findAll()
                .stream()
                .map(item -> StationeryItemMapper.INSTANCE.stationeryItemToDTO(item, images))
                .toList();

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StationeryItemDTO> getStationeryItemById(int id) {
        List<Image> images = imageService.getAllImages();

        StationeryItemDTO responseBody = stationeryRepository
                .findById(id)
                .map(item -> StationeryItemMapper.INSTANCE.stationeryItemToDTO(item, images))
                .orElseThrow(UnknownIdException::new);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addStationeryItem(StationeryItemDTO stationeryItemDto, List<MultipartFile> images) {
        StationeryItem item;

        try {
            item = StationeryItemMapper.INSTANCE.stationeryItemDTOToStationeryItem(stationeryItemDto);
        } catch (Exception e) {
            throw new ValidationException();
        }

        stationeryRepository.save(item);

        imageService.saveAllImages(images, item.getUuid());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteAllStationeryItems() {
        stationeryRepository
                .findAll()
                .forEach(image -> imageService.deleteAllImagesByUUID(image.getUuid()));

        stationeryRepository.deleteAll();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteStationeryItemById(int itemId) {
        StationeryItem item = stationeryRepository.findById(itemId).orElseThrow(UnknownIdException::new);

        stationeryRepository.delete(item);

        imageService.deleteAllImagesByUUID(item.getUuid());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> updateStationeryItemById(int itemId, StationeryItemDTO stationeryItemDto, List<MultipartFile> images) {
        StationeryItem item;

        try {
            item = StationeryItemMapper.INSTANCE.stationeryItemDTOToStationeryItem(stationeryItemDto);
        } catch (Exception e) {
            throw new ValidationException();
        }

        StationeryItem itemEntity = stationeryRepository.findById(itemId).orElseThrow(UnknownIdException::new);

        itemEntity.setName(item.getName());
        itemEntity.setType(item.getType());
        itemEntity.setPrice(item.getPrice());

        stationeryRepository.save(itemEntity);

        imageService.saveAllImages(images, itemEntity.getUuid());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}