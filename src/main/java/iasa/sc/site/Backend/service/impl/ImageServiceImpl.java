package iasa.sc.site.Backend.service.impl;

import iasa.sc.site.Backend.entity.Image;
import iasa.sc.site.Backend.repository.ImageRepository;
import iasa.sc.site.Backend.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public List<Image> getAllImagesByUUID(UUID uuid) {
        return imageRepository.findByUUID(uuid);
    }

    @Override
    public List<Image> saveAll(List<Image> images, UUID uuid) {
        images.forEach(i -> i.setUuid(uuid));
        return imageRepository.saveAll(images);
    }
}