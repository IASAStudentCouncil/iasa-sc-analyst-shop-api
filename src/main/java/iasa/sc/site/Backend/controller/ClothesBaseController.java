package iasa.sc.site.Backend.controller;

import iasa.sc.site.Backend.dto.ClothesBaseDto;
import iasa.sc.site.Backend.service.ClothesBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clothes-bases")
@RequiredArgsConstructor
public class ClothesBaseController {
    private final ClothesBaseService clothesBaseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ClothesBaseDto>> getAllClothesBases() {
        return clothesBaseService.getAllClothesBases();
    }

}
