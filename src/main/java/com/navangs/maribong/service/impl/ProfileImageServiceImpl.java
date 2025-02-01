package com.navangs.maribong.service.impl;

import com.navangs.maribong.exception.FileTransferFailedException;
import com.navangs.maribong.service.ImageService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileImageServiceImpl implements ImageService {
    private static final String PROFILE_UPLOAD_PATH = "./src/main/uploads/profile/";

    @Override
    public void uploadImage(MultipartFile image, String imageName) {
        Path uploadPath = Path.of(PROFILE_UPLOAD_PATH + imageName).toAbsolutePath();
        try (OutputStream profileOs = new FileOutputStream(uploadPath.toFile())) {
            resizeProfileImage(image, profileOs);
        } catch (IOException e) {
            throw new FileTransferFailedException();
        }
    }

    @Override
    public void deleteImage(String savedImageName) {
        if (savedImageName == null) {
            return;
        }
        Path savedPath = Path.of(PROFILE_UPLOAD_PATH + savedImageName);
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
}
