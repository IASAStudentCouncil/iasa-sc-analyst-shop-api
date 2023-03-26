package iasa.sc.site.Backend.service;

import iasa.sc.site.Backend.dto.StationeryItemDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StationeryService {

    ResponseEntity<List<StationeryItemDTO>> getAll();

    ResponseEntity<StationeryItemDTO> get(int id);

    ResponseEntity<Void> add(StationeryItemDTO stationeryItemDto);

    ResponseEntity<Void> deleteAllItems();

    ResponseEntity<Void> deleteById(int itemId);

    ResponseEntity<Void> editById(int itemId, StationeryItemDTO stationeryItemDto);
}
