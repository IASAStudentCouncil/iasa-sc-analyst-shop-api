package iasa.sc.site.Backend.services;

import iasa.sc.site.Backend.dtos.ClothesBaseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClothesBaseService {
    ResponseEntity<List<ClothesBaseDTO>> getAllClothesBases();
}
