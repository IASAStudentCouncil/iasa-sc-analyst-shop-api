package iasa.sc.site.Backend.controllers;

import iasa.sc.site.Backend.dtos.PrintDto;
import iasa.sc.site.Backend.services.PrintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/prints")
@RequiredArgsConstructor
public class PrintController {
    private final PrintService printService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PrintDto>> getAllPrints() {
        return printService.getAllPrints();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletePrint(@PathVariable("id") String id) {
        return printService.deletePrintById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addPrint(@RequestParam("print") PrintDto printDto,
                                         @RequestParam("images") List<MultipartFile> images) {
        return printService.addNewPrint(printDto, images);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> updatePrint(@RequestParam("print") PrintDto printDto,
                                            @RequestParam("images") List<MultipartFile> images) {
        return printService.updatePrint(printDto, images);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PrintDto> getPrintById(@PathVariable("id") String printId){
        return printService.getPrintById(printId);
    }
}
