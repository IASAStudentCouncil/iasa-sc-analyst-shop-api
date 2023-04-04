package iasa.sc.site.Backend.controller;

import iasa.sc.site.Backend.dto.StationeryItemDTO;
import iasa.sc.site.Backend.service.StationeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addItem(@RequestBody StationeryItemDTO stationeryItemDto) {
        return stationeryService.add(stationeryItemDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> editItem(@PathVariable int id,
                                         @RequestBody StationeryItemDTO stationeryItemDto) {
        return stationeryService.editById(id, stationeryItemDto);
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
