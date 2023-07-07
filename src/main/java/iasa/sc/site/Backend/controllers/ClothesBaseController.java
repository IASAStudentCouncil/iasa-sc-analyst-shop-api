package iasa.sc.site.Backend.controllers;

import iasa.sc.site.Backend.dtos.ClothesBaseDTO;
import iasa.sc.site.Backend.dtos.ClothesBaseInfoDTO;
import iasa.sc.site.Backend.services.ClothesBaseService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<ClothesBaseDTO>> getAllClothesBases(
            @RequestParam(defaultValue = "0", name = "offset") String offset,
            @RequestParam(defaultValue = "10", name = "limit") String limit,
            @RequestParam(defaultValue = "TSHIRTS", name = "type") String type) {
        return new ResponseEntity<>(clothesBaseService.getAllClothesBasesByType(type, offset, limit), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClothesBaseDTO> getClothesBaseById(@PathVariable("id") String id) {
        return new ResponseEntity<>(clothesBaseService.getClothesBaseById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createClothesBase(@RequestPart("clothes_base") @Valid ClothesBaseDTO clothesBaseDto,
                                                  @RequestPart("clothes_base_info") @Valid ClothesBaseInfoDTO clothesBaseInfoDto,
                                                  @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        clothesBaseService.createClothesBase(clothesBaseDto, clothesBaseInfoDto, images);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClothesBase(@PathVariable("id") String id) {
        clothesBaseService.deleteClothesBaseById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/info/{id}")
    public ResponseEntity<Void> deleteClothesBaseInfo(@PathVariable("id") String id) {
        clothesBaseService.deleteClothesBaseInfoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/info")
    public ResponseEntity<List<ClothesBaseInfoDTO>> getAllClothesBasesInfo() {
        return new ResponseEntity<>(clothesBaseService.getAllClothesBasesInfo(), HttpStatus.OK);
    }
}