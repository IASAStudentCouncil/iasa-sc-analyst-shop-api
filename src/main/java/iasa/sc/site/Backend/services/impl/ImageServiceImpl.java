package iasa.sc.site.Backend.services.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import iasa.sc.site.Backend.entities.Image;
import iasa.sc.site.Backend.exceptions.UnexistingImageException;
import iasa.sc.site.Backend.repositories.ImageRepository;
import iasa.sc.site.Backend.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private static final int saltLength = 7;

    private final ImageRepository imageRepository;

    private final BlobContainerClient imageContainerClient;

    @Override
    public List<Image> getAllImagesByUUID(UUID uuid) {
        return imageRepository.findImagesByUuid(uuid);
    }

    @Override
    public Image getImageByUUID(UUID uuid) {
        return imageRepository.findImageByUuid(uuid);
    }

    @Override
    public void saveAllImages(List<MultipartFile> images, UUID uuid) {
        images.forEach(image -> saveImage(image, uuid));
    }

    @Override
    public void saveImage(MultipartFile image, UUID uuid) {
        BlobClient blobClient = imageContainerClient.getBlobClient(image.getName() + "-" + uuid.toString() + "-" + generateRandomStringSalt() + ".png");
        uploadImage(blobClient, image);
        imageRepository.save(new Image(0, blobClient.getBlobUrl(), uuid));
    }

    private void uploadImage(BlobClient blobClient, MultipartFile image) {
        try {
            blobClient.upload(image.getInputStream(), image.getSize(), true);
        } catch (IOException e) {
            throw new RuntimeException("Something is wrong with image");
        }
    }

    private String generateRandomStringSalt() {
        Random random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < saltLength; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

    @Override
    public void deleteAllImagesByUUID(UUID uuid) {
        imageContainerClient
                .listBlobs()
                .stream()
                .filter(blobItem -> blobItem.getName().contains(uuid.toString()))
                .forEach(blobItem -> imageContainerClient.getBlobClient(blobItem.getName()).delete());
        
        imageRepository.deleteAllByUuid(uuid);
    }

    @Override
    public void deleteAllImages() {
        imageContainerClient
                .listBlobs()
                .forEach(blobItem -> imageContainerClient.getBlobClient(blobItem.getName()).delete());

        imageRepository.deleteAll();
    }

    @Override
    public void deleteImageByName(String imageName) {
        BlobClient blobClient = imageContainerClient.getBlobClient(imageName);
        if (!blobClient.deleteIfExists()) throw new UnexistingImageException();
    }
}