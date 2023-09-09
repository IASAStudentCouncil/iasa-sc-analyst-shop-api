package iasa.sc.site.Backend.services.impl;

import iasa.sc.site.Backend.dtos.StationeryItemDTO;
import iasa.sc.site.Backend.dtos.mappers.StationeryItemMapper;
import iasa.sc.site.Backend.entities.StationeryItem;
import iasa.sc.site.Backend.entities.enums.StationeryItemType;
import iasa.sc.site.Backend.exceptions.UnexistingTypeException;
import iasa.sc.site.Backend.exceptions.UnknownIdException;
import iasa.sc.site.Backend.exceptions.ValidationException;
import iasa.sc.site.Backend.repositories.StationeryRepository;
import iasa.sc.site.Backend.services.ImageService;
import iasa.sc.site.Backend.services.StationeryService;
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
    public Page<StationeryItemDTO> getAllStationeryItems(String type, int page, int limit) {
        if (type == null) {
            return stationeryRepository.findAll(PageRequest.of(page, limit))
                    .map(StationeryItemMapper.INSTANCE::stationeryItemToDTO);
        }
        return stationeryRepository.findAllByType(parseStationeryItemType(type), PageRequest.of(page, limit))
                .map(StationeryItemMapper.INSTANCE::stationeryItemToDTO);
    }

    private StationeryItemType parseStationeryItemType(String type) {
        try {
            return StationeryItemType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new UnexistingTypeException(type);
        }
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

        item.setUuid(UUID.randomUUID());
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
