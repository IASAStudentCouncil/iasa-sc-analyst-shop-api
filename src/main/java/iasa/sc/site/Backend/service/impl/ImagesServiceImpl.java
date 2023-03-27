package iasa.sc.site.Backend.service.impl;

import iasa.sc.site.Backend.entity.Image;
import iasa.sc.site.Backend.repository.ImagesRepository;
import iasa.sc.site.Backend.service.ImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImagesServiceImpl implements ImagesService {
    private final ImagesRepository imagesRepository;

    @Override
    public List<Image> getAllPhotosByUUID(UUID uuid) {
        return imagesRepository.findByUUID(uuid);
    }

    @Override
    public List<Image> saveAll(List<Image> images, UUID uuid) {
        images.stream().forEach(i -> i.setUuid(uuid));
        return imagesRepository.saveAll(images);
    }
}
