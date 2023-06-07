package iasa.sc.site.Backend.services.impl;

import iasa.sc.site.Backend.dtos.StationeryItemDTO;
import iasa.sc.site.Backend.dtos.mappers.StationeryItemMapper;
import iasa.sc.site.Backend.entities.StationeryItem;
import iasa.sc.site.Backend.exceptions.UnknownIdException;
import iasa.sc.site.Backend.exceptions.ValidationException;
import iasa.sc.site.Backend.repositories.StationeryRepository;
import iasa.sc.site.Backend.services.ImageService;
import iasa.sc.site.Backend.services.StationeryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationeryServiceImpl implements StationeryService {
    private final StationeryRepository stationeryRepository;

    private final ImageService imageService;

    @Override
    public List<StationeryItemDTO> getAllStationeryItems() {
        return stationeryRepository
                .findAll()
                .stream()
                .map(StationeryItemMapper.INSTANCE::stationeryItemToDTO)
                .toList();
    }

    @Override
    public StationeryItemDTO getStationeryItemById(int id) {
        return stationeryRepository
                .findById(id)
                .map(StationeryItemMapper.INSTANCE::stationeryItemToDTO)
                .orElseThrow(UnknownIdException::new);
    }

    @Override
    @Transactional
    public void addStationeryItem(StationeryItemDTO stationeryItemDto, List<MultipartFile> images) {
        StationeryItem item;

        try {
            item = StationeryItemMapper.INSTANCE.stationeryItemDTOToStationeryItem(stationeryItemDto);
        } catch (Exception e) {
            throw new ValidationException();
        }

        item = stationeryRepository.save(item);

        if (images != null) {
            imageService.saveAllImages(images, item.getUuid());
        }
    }

    @Override
    @Transactional
    public void deleteAllStationeryItems() {
        stationeryRepository
                .findAll()
                .forEach(image -> imageService.deleteAllImagesByUUID(image.getUuid()));

        stationeryRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteStationeryItemById(int itemId) {
        StationeryItem item = stationeryRepository.findById(itemId).orElseThrow(UnknownIdException::new);

        stationeryRepository.delete(item);

        imageService.deleteAllImagesByUUID(item.getUuid());
    }

    @Override
    @Transactional
    public void updateStationeryItemById(int itemId, StationeryItemDTO stationeryItemDto, List<MultipartFile> images) {
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

        if (images != null) {
            imageService.saveAllImages(images, itemEntity.getUuid());
        }
    }
}
