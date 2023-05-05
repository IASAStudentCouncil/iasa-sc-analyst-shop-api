package iasa.sc.site.Backend.controllers;

import iasa.sc.site.Backend.dtos.PhotocardDTO;
import iasa.sc.site.Backend.services.PhotocardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/photocards")
@RequiredArgsConstructor
public class PhotocardController {
    private final PhotocardService photocardService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PhotocardDTO>> getAllPhotocards() {
        return photocardService.getAllPhotocards();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PhotocardDTO> getItemById(@PathVariable int id) {
        return photocardService.getPhotocardById(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addItem(@RequestPart("photocard") PhotocardDTO photocardDTO,
                                        @RequestPart(value = "image", required = false) MultipartFile image) {
        return photocardService.addNewPhotocard(photocardDTO, image);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> editItem(@PathVariable int id,
                                         @RequestPart("photocard") PhotocardDTO photocardDTO,
                                         @RequestPart(value = "image", required = false) MultipartFile image) {
        return photocardService.updatePhotocardById(id, photocardDTO, image);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAllItems() {
        return photocardService.deleteAllPhotocards();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteItemById(@PathVariable int id) {
        return photocardService.deletePhotocardById(id);
    }
}
