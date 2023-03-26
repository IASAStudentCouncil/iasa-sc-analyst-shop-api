package iasa.sc.site.Backend.controller;

import iasa.sc.site.Backend.dto.PhotocardDTO;
import iasa.sc.site.Backend.service.PhotocardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photocards")
@RequiredArgsConstructor
public class PhotocardController {

    private final PhotocardService photocardService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PhotocardDTO>> getAllPhotocards() {
        return photocardService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PhotocardDTO> getItemById(@PathVariable int id) {
        return photocardService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addItem(@RequestBody PhotocardDTO photocardDTO) {
        return photocardService.add(photocardDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> editItem(@PathVariable int id,
                                         @RequestBody PhotocardDTO photocardDTO) {
        return photocardService.editById(id, photocardDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAllItems() {
        return photocardService.deleteAllItems();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteItemById(@PathVariable int id) {
        return photocardService.deleteById(id);
    }
}
