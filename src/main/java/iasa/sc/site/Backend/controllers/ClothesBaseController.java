package iasa.sc.site.Backend.controllers;

import iasa.sc.site.Backend.dtos.ClothesBaseDTO;
import iasa.sc.site.Backend.dtos.ClothesBaseInfoDTO;
import iasa.sc.site.Backend.services.ClothesBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/clothes-bases")
@RequiredArgsConstructor
@CrossOrigin
public class ClothesBaseController {
    private final ClothesBaseService clothesBaseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ClothesBaseDTO>> getAllClothesBases() {
        return clothesBaseService.getAllClothesBases();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClothesBaseDTO> getClothesBaseById(@PathVariable("id") String id) {
        return clothesBaseService.getClothesBaseById(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createClothesBase(@RequestPart("clothes_base") ClothesBaseDTO clothesBaseDto,
                                                  @RequestPart("clothes_base_info") ClothesBaseInfoDTO clothesBaseInfoDto,
                                                  @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        return clothesBaseService.createClothesBase(clothesBaseDto, clothesBaseInfoDto, images);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteClothesBase(@PathVariable("id") String id) {
        return clothesBaseService.deleteClothesBaseById(id);
    }

    @DeleteMapping("/info/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteClothesBaseInfo(@PathVariable("id") String id) {
        return clothesBaseService.deleteClothesBaseInfoById(id);
    }

    @GetMapping("/info")
    public ResponseEntity<List<ClothesBaseInfoDTO>> getAllClothesBasesInfo() {
        return clothesBaseService.getAllClothesBasesInfo();
    }
}
