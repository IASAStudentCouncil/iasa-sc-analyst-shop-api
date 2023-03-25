package iasa.sc.site.Backend.service;

import iasa.sc.site.Backend.dto.ClothesBaseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClothesBaseService {
    ResponseEntity<List<ClothesBaseDto>> getAllClothesBases();
}
