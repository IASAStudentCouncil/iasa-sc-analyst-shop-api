package iasa.sc.site.Backend.controllers;

import iasa.sc.site.Backend.dtos.StationeryItemDTO;
import iasa.sc.site.Backend.services.StationeryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StationeryItemDTO>> getAllStationeryItems() {
        return stationeryService.getAllStationeryItems();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StationeryItemDTO> getItemById(@PathVariable int id) {
        return stationeryService.getStationeryItemById(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addItem(@RequestPart("stationery_item") @Valid StationeryItemDTO stationeryItemDTO,
                                        @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        return stationeryService.addStationeryItem(stationeryItemDTO, images);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> editItem(@PathVariable int id,
                                         @RequestPart("stationery_item") @Valid StationeryItemDTO stationeryItemDTO,
                                         @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        return stationeryService.updateStationeryItemById(id, stationeryItemDTO, images);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAllItems() {
        return stationeryService.deleteAllStationeryItems();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteItemById(@PathVariable int id) {
        return stationeryService.deleteStationeryItemById(id);
    }
}
