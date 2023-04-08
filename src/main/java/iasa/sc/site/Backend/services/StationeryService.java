package iasa.sc.site.Backend.services;

import iasa.sc.site.Backend.dtos.StationeryItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StationeryService {
    ResponseEntity<List<StationeryItemDTO>> getAllStationeryItems();

    ResponseEntity<StationeryItemDTO> getStationeryItemById(int id);

    ResponseEntity<Void> addStationeryItem(StationeryItemDTO stationeryItemDto, List<MultipartFile> images);

    ResponseEntity<Void> deleteAllStationeryItems();

    ResponseEntity<Void> deleteStationeryItemById(int itemId);

    ResponseEntity<Void> updateStationeryItemById(int itemId, StationeryItemDTO stationeryItemDto, List<MultipartFile> images);
}
