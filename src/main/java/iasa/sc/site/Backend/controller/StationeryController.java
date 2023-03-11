package iasa.sc.site.Backend.controller;

import iasa.sc.site.Backend.entity.StationeryItem;
import iasa.sc.site.Backend.service.StationeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stationery")
@RequiredArgsConstructor
public class StationeryController {

    private final StationeryService stationeryService;

    @GetMapping
    public List<StationeryItem> getAllStationeryItems() {
        return stationeryService.getAll();
    }

    @GetMapping("/{id}")
    public StationeryItem getItemById(@PathVariable int id) {
        return stationeryService.get(id);
    }

    @PostMapping
    public StationeryItem addItem(@RequestBody StationeryItem item) {
        return stationeryService.add(item);
    }

    @PutMapping("/{id}")
    public StationeryItem editItem(@PathVariable int id,
                                   @RequestBody StationeryItem item) {
        return stationeryService.editById(id, item);
    }

    @DeleteMapping
    public void deleteAllItems() {
        stationeryService.deleteAllItems();
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable int id) {
        stationeryService.deleteById(id);
    }
}
