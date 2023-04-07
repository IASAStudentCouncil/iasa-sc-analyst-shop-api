package iasa.sc.site.Backend.controller;

import iasa.sc.site.Backend.dto.StationeryItemDTO;
import iasa.sc.site.Backend.service.StationeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/stationery")
@RequiredArgsConstructor
public class StationeryController {

    private final StationeryService stationeryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StationeryItemDTO>> getAllStationeryItems() {
        return stationeryService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StationeryItemDTO> getItemById(@PathVariable int id) {
        return stationeryService.get(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addItem(@RequestPart("stationeryItemDTO") StationeryItemDTO stationeryItemDTO,
                                        @RequestPart("images") List<MultipartFile> images) {
        return stationeryService.add(stationeryItemDTO, images);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> editItem(@PathVariable int id,
                                         @RequestPart("stationeryItemDTO") StationeryItemDTO stationeryItemDTO,
                                         @RequestPart("images") List<MultipartFile> images) {
        return stationeryService.updateById(id, stationeryItemDTO, images);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAllItems() {
        return stationeryService.deleteAllItems();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteItemById(@PathVariable int id) {
        return stationeryService.deleteById(id);
    }
}
