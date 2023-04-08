package iasa.sc.site.Backend.controllers;

import iasa.sc.site.Backend.dtos.ClothesBaseDTO;
import iasa.sc.site.Backend.services.ClothesBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clothes-bases")
@RequiredArgsConstructor
public class ClothesBaseController {
    private final ClothesBaseService clothesBaseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ClothesBaseDTO>> getAllClothesBases() {
        return clothesBaseService.getAllClothesBases();
    }

//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<ClothesBaseDTO> getClothesBaseById(@PathVariable("id") String id) {
//
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Void> createClothesBase(@RequestParam("clothes_base") ClothesBaseDTO clothesBaseDto,
//                                                  @RequestParam("images") List<MultipartFile> images) {
//
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public ResponseEntity<Void> deleteClothesBaseById
}
