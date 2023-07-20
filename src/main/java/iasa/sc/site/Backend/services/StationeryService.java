package iasa.sc.site.Backend.services;

import iasa.sc.site.Backend.dtos.StationeryItemDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StationeryService {
    List<StationeryItemDTO> getAllStationeryItems();

    List<StationeryItemDTO> getAllStationeryItems(String page, String limit);

    StationeryItemDTO getStationeryItemById(int id);

    void addStationeryItem(StationeryItemDTO stationeryItemDto, List<MultipartFile> images);

    void deleteAllStationeryItems();

    void deleteStationeryItemById(int itemId);

    void updateStationeryItemById(int itemId, StationeryItemDTO stationeryItemDto, List<MultipartFile> images);
}
