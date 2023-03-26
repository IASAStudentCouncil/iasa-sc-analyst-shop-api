package iasa.sc.site.Backend.service;

import iasa.sc.site.Backend.entity.Image;

import java.util.List;
import java.util.UUID;

public interface ImageService {
    List<Image> getAllImagesByUUID(UUID uuid);

    List<Image> saveAll(List<Image> images, UUID uuid);
}
