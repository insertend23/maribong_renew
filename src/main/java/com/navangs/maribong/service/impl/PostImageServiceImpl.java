package com.navangs.maribong.service.impl;

import com.navangs.maribong.exception.FileTransferFailedException;
import com.navangs.maribong.service.ImageService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostImageServiceImpl implements ImageService {
    public static final String POST_IMG_UPLOAD_PATH = "./src/main/uploads/postimg";

    @Override
    public void uploadImage(MultipartFile image, String imageName) {
        Path uploadPath = Path.of(POST_IMG_UPLOAD_PATH + imageName).toAbsolutePath();
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
        Path savedPath = Path.of(POST_IMG_UPLOAD_PATH + savedImageName);
        File savedProfileFile = savedPath.toFile();
        if (savedProfileFile.exists()) {
            savedProfileFile.delete();
        }

    }
}
