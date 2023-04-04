package iasa.sc.site.Backend.service;

import iasa.sc.site.Backend.dto.StationeryItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StationeryService {

    ResponseEntity<List<StationeryItemDTO>> getAll();

    ResponseEntity<StationeryItemDTO> get(int id);

    ResponseEntity<Void> add(StationeryItemDTO stationeryItemDto, List<MultipartFile> images);

    ResponseEntity<Void> deleteAllItems();

    ResponseEntity<Void> deleteById(int itemId);

    ResponseEntity<Void> updateById(int itemId, StationeryItemDTO stationeryItemDto, List<MultipartFile> images);
}
