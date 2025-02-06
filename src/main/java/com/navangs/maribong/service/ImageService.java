package com.navangs.maribong.service;

import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    UrlResource getImage(String imageName);

    void uploadImage(MultipartFile image, String imageName);

    void deleteImage(String savedImageName);
}
