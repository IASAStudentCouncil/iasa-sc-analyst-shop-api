package iasa.sc.site.Backend.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import iasa.sc.site.Backend.entity.Image;
import iasa.sc.site.Backend.repository.ImageRepository;
import iasa.sc.site.Backend.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.container-name}")
    private String containerName;

    private final ImageRepository imageRepository;

    private BlobContainerClient getImageContainerClient() {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();

        return blobServiceClient.getBlobContainerClient(containerName);
    }

    @Override
    public List<Image> getAllImagesByUUID(UUID uuid) {
        return imageRepository.findByUUID(uuid);
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
        BlobContainerClient imageContainerClient = getImageContainerClient();

        BlobClient blobClient = imageContainerClient.getBlobClient(image.getName() + "-" + uuid.toString() + ".png");

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

    @Override
    public void deleteAllImagesByUUID(UUID uuid) {
        BlobContainerClient imageContainerClient = getImageContainerClient();

        imageContainerClient
                .listBlobs()
                .stream()
                .filter(blobItem -> blobItem.getName().contains(uuid.toString()))
                .forEach(blobItem -> imageContainerClient.getBlobClient(blobItem.getName()).delete());

        imageRepository.deleteAllByUuid(uuid);
    }

    @Override
    public void deleteAllImages() {
        BlobContainerClient imageContainerClient = getImageContainerClient();

        imageContainerClient
                .listBlobs()
                .forEach(blobItem -> imageContainerClient.getBlobClient(blobItem.getName()).delete());

        imageRepository.deleteAll();
    }
}