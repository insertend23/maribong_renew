package com.navangs.maribong.service.impl;

import com.navangs.maribong.config.ImagePathProperty;
import com.navangs.maribong.exception.FileTransferFailedException;
import com.navangs.maribong.exception.IllegalImageUrlException;
import com.navangs.maribong.service.ImageService;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Qualifier(PostImageServiceImpl.BEAN_NAME)
@RequiredArgsConstructor
public class PostImageServiceImpl implements ImageService {
    public static final String BEAN_NAME = "postImageService";
    private final ImagePathProperty imagePathProperty;

    @Override
    public UrlResource getImage(String imageName) {
        Path imagePath = getPostImageUploadPath(imageName);
        try {
            return new UrlResource(imagePath.toUri());
        } catch (MalformedURLException e) {
            throw new IllegalImageUrlException();
        }
    }

    @Override
    public void uploadImage(MultipartFile image, String imageName) {
        Path uploadPath = getPostImageUploadPath(imageName).toAbsolutePath();
        try {
            image.transferTo(uploadPath);
        } catch (IOException e) {
            throw new FileTransferFailedException();
        }
    }

    @Override
    public void deleteImage(String savedImageName) {
        if (savedImageName == null || savedImageName.isEmpty()) {
            return;
        }
        Path savedPath = getPostImageUploadPath(savedImageName);
        File savedProfileFile = savedPath.toFile();
        if (savedProfileFile.exists()) {
            savedProfileFile.delete();
        }
    }

    private Path getPostImageUploadPath(String imageName) {
        return Path.of(imagePathProperty.getPostImageUploadPath(), imageName);
    }
}
