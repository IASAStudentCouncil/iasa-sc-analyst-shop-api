package iasa.sc.site.Backend.controllers;

import iasa.sc.site.Backend.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> postImage(@RequestParam("image") MultipartFile image,
                                          @RequestParam("uuid") UUID uuid) {
        imageService.saveImage(image, uuid);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{image_name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteImage(@PathVariable("image_name") String imageName) {
        imageService.deleteImageByName(imageName);
        return ResponseEntity.ok().build();
    }
}
