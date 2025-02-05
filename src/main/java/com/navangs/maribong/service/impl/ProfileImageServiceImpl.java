package com.navangs.maribong.service.impl;

import com.navangs.maribong.config.image.ImagePath;
import com.navangs.maribong.config.image.ImageUploadPath;
import com.navangs.maribong.exception.FileTransferFailedException;
import com.navangs.maribong.exception.IllegalImageUrlException;
import com.navangs.maribong.service.ImageService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Qualifier(ProfileImageServiceImpl.BEAN_NAME)
@RequiredArgsConstructor
public class ProfileImageServiceImpl implements ImageService {
    public static final String BEAN_NAME = "profileImageService";

    @Qualifier(ImageUploadPath.BEAN_NAME)
    private final ImagePath imagePath;

    @Override
    public UrlResource getImage(String imageName) {
        Path imagePath = getProfileUploadPath(imageName);
        try {
            return new UrlResource(imagePath.toUri());
        } catch (MalformedURLException e) {
            throw new IllegalImageUrlException();
        }
    }

    @Override
    public void uploadImage(MultipartFile image, String imageName) {
        Path uploadPath = getProfileUploadPath(imageName).toAbsolutePath();
        try (OutputStream profileOs = new FileOutputStream(uploadPath.toFile())) {
            resizeProfileImage(image, profileOs);
        } catch (IOException e) {
            throw new FileTransferFailedException();
        }
    }

    @Override
    public void deleteImage(String savedImageName) {
        if (savedImageName == null || savedImageName.isEmpty()) {
            return;
        }
        Path savedPath = getProfileUploadPath(savedImageName);
        File savedProfileFile = savedPath.toFile();
        if (savedProfileFile.exists()) {
            savedProfileFile.delete();
        }
    }


    private void resizeProfileImage(MultipartFile profile, OutputStream profileOs) {
        try {
            Thumbnails.of(profile.getInputStream())
                .size(200, 200)
                .toOutputStream(profileOs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getProfileUploadPath(String imageName) {
        return Path.of(imagePath.getProfilePath(), imageName);
    }
}
