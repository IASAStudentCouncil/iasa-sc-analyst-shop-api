package iasa.sc.site.Backend.controllers;

import iasa.sc.site.Backend.dtos.PrintDTO;
import iasa.sc.site.Backend.services.PrintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/prints")
@RequiredArgsConstructor
@CrossOrigin
public class PrintController {
    private final PrintService printService;

    @GetMapping
    public ResponseEntity<List<PrintDTO>> getAllPrints(@RequestParam(name = "page", defaultValue = "0") String page,
                                                       @RequestParam(name = "limit", defaultValue = "50") String limit) {
        List<PrintDTO> prints = printService.getAllPrints(page, limit);
        return new ResponseEntity<>(prints, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrint(@PathVariable("id") String id) {
        printService.deletePrintById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addPrint(@RequestPart("print") @Valid PrintDTO printDto,
                                         @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        printService.addNewPrint(printDto, images);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updatePrint(@RequestPart("print") @Valid PrintDTO printDto,
                                            @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        printService.updatePrint(printDto, images);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrintDTO> getPrintById(@PathVariable("id") String printId) {
        PrintDTO item = printService.getPrintById(printId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllPrints() {
        printService.deleteAllPrints();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}