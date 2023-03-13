package iasa.sc.site.Backend.controller;

import iasa.sc.site.Backend.dto.PrintDto;
import iasa.sc.site.Backend.service.PrintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prints")
@RequiredArgsConstructor
public class PrintController {
    private final PrintService printService;
    @GetMapping
    public ResponseEntity<List<PrintDto>> getAllPrints(){
        return printService.getAllPrints();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrint(@PathVariable("id") String id){
        return printService.deletePrint(id);
    }

    @PostMapping
    public ResponseEntity<Void> addPrint(@RequestBody PrintDto printDto){
        return  printService.addNewPrint(printDto);
    }
}
