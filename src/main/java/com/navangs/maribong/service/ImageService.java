package com.navangs.maribong.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void uploadImage(MultipartFile image, String imageName);

    void deleteImage(String savedProfileName);
}
