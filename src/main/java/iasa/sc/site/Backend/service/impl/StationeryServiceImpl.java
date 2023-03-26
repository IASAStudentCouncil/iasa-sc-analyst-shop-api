package iasa.sc.site.Backend.service.impl;

import iasa.sc.site.Backend.dto.StationeryItemDTO;
import iasa.sc.site.Backend.dto.mappers.StationeryItemMapper;
import iasa.sc.site.Backend.entity.StationeryItem;
import iasa.sc.site.Backend.exceptions.UnknownIdException;
import iasa.sc.site.Backend.exceptions.ValidationException;
import iasa.sc.site.Backend.repository.StationeryRepository;
import iasa.sc.site.Backend.service.ImageService;
import iasa.sc.site.Backend.service.StationeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationeryServiceImpl implements StationeryService {

    private final StationeryRepository stationeryRepository;

    private final ImageService imageService;

    @Override
    public ResponseEntity<List<StationeryItemDTO>> getAll() {
        List<StationeryItemDTO> responseBody = stationeryRepository
                .findAll()
                .stream()
                .map(item -> StationeryItemMapper.INSTANCE.stationeryItemToDTO(item, imageService))
                .toList();

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StationeryItemDTO> get(int id) {
        StationeryItemDTO responseBody = stationeryRepository
                .findById(id)
                .map(item -> StationeryItemMapper.INSTANCE.stationeryItemToDTO(item, imageService))
                .orElseThrow(UnknownIdException::new);


        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> add(StationeryItemDTO stationeryItemDto) {
        StationeryItem item;

        try {
            item = StationeryItemMapper.INSTANCE.stationeryItemDTOToStationeryItem(stationeryItemDto);
        } catch (Exception e) {
            throw new ValidationException();
        }

        stationeryRepository.save(item);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> deleteAllItems() {
        stationeryRepository.deleteAll();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> deleteById(int itemId) {
        StationeryItem item = stationeryRepository.findById(itemId).orElseThrow(UnknownIdException::new);
        stationeryRepository.delete(item);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> editById(int itemId, StationeryItemDTO stationeryItemDto) {
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

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}