package com.navangs.maribong.controller;

import com.navangs.maribong.service.ImageService;
import com.navangs.maribong.service.impl.PostImageServiceImpl;
import com.navangs.maribong.service.impl.ProfileImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/img")
public class ImageController {
    @Qualifier(PostImageServiceImpl.BEAN_NAME)
    private final ImageService postImageService;

    @Qualifier(ProfileImageServiceImpl.BEAN_NAME)
    private final ImageService profileImageService;

    @GetMapping(value = "profile/{imageName}")
    public ResponseEntity<UrlResource> getProfileImage(@PathVariable String imageName) {
        UrlResource imageUrl = profileImageService.getImage(imageName);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageName + "\"")
            .body(imageUrl);
    }

    @GetMapping(value = "postimg/{imageName}")
    public ResponseEntity<UrlResource> getPostImage(@PathVariable String imageName) {
        UrlResource imageUrl = postImageService.getImage(imageName);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageName + "\"")
            .body(imageUrl);
    }
}
