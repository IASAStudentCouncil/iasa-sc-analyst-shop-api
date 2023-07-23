package iasa.sc.site.Backend.controllers;

import iasa.sc.site.Backend.dtos.PhotocardDTO;
import iasa.sc.site.Backend.services.PhotocardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photocards")
@RequiredArgsConstructor
@CrossOrigin
public class PhotocardController {
    private final PhotocardService photocardService;

    @GetMapping
    public ResponseEntity<Page<PhotocardDTO>> getAllPhotocards(@RequestParam(name = "type", defaultValue = "OUR_CLIENTS") String type,
                                                               @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                               @RequestParam(name = "limit", defaultValue = "50") Integer limit) {
        Page<PhotocardDTO> photocards = photocardService.getAllPhotocards(type, page, limit);
        return new ResponseEntity<>(photocards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotocardDTO> getItemById(@PathVariable int id) {
        PhotocardDTO item = photocardService.getPhotocardById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addItem(@RequestPart("photocard") @Valid PhotocardDTO photocardDTO,
                                        @RequestPart(value = "image", required = false) MultipartFile image) {
        photocardService.addNewPhotocard(photocardDTO, image);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> editItem(@PathVariable int id,
                                         @RequestPart("photocard") @Valid PhotocardDTO photocardDTO,
                                         @RequestPart(value = "image", required = false) MultipartFile image) {
        photocardService.updatePhotocardById(id, photocardDTO, image);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllItems() {
        photocardService.deleteAllPhotocards();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable int id) {
        photocardService.deletePhotocardById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
