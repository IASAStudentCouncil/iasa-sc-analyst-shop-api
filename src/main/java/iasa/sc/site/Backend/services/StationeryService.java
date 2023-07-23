package iasa.sc.site.Backend.services;

import iasa.sc.site.Backend.dtos.StationeryItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StationeryService {
    List<StationeryItemDTO> getAllStationeryItems();

    Page<StationeryItemDTO> getAllStationeryItems(String type, int page, int limit);

    StationeryItemDTO getStationeryItemById(int id);

    void addStationeryItem(StationeryItemDTO stationeryItemDto, List<MultipartFile> images);

    void deleteAllStationeryItems();

    void deleteStationeryItemById(int itemId);

    void updateStationeryItemById(int itemId, StationeryItemDTO stationeryItemDto, List<MultipartFile> images);
}
