package iasa.sc.site.Backend.controllers;

import iasa.sc.site.Backend.dtos.StationeryItemDTO;
import iasa.sc.site.Backend.services.StationeryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/stationery")
@RequiredArgsConstructor
@CrossOrigin
public class StationeryController {
    private final StationeryService stationeryService;

    @GetMapping
    public ResponseEntity<Page<StationeryItemDTO>> getAllStationeryItems(@RequestParam(value = "type", required = false) String type,
                                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                                         @RequestParam(value = "limit", defaultValue = "50") int limit) {
        Page<StationeryItemDTO> items = stationeryService.getAllStationeryItems(type, page, limit);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationeryItemDTO> getItemById(@PathVariable int id) {
        StationeryItemDTO item = stationeryService.getStationeryItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addItem(@RequestPart("stationery_item") @Valid StationeryItemDTO stationeryItemDTO,
                                        @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        stationeryService.addStationeryItem(stationeryItemDTO, images);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> editItem(@PathVariable int id,
                                         @RequestPart("stationery_item") @Valid StationeryItemDTO stationeryItemDTO,
                                         @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        stationeryService.updateStationeryItemById(id, stationeryItemDTO, images);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllItems() {
        stationeryService.deleteAllStationeryItems();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable int id) {
        stationeryService.deleteStationeryItemById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
