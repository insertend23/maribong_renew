package com.navangs.maribong.controller;

import com.navangs.maribong.service.ImageService;
import com.navangs.maribong.service.impl.PostImageServiceImpl;
import com.navangs.maribong.service.impl.ProfileImageServiceImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/${image.url.query-path}")
public class ImageController {
    @Qualifier(PostImageServiceImpl.BEAN_NAME)
    private final ImageService postImageService;

    @Qualifier(ProfileImageServiceImpl.BEAN_NAME)
    private final ImageService profileImageService;

    @GetMapping(value = "profile/{imageName}")
    public ResponseEntity<UrlResource> getProfileImage(@PathVariable String imageName) throws IOException {
        UrlResource imageUrl = profileImageService.getImage(imageName);

        // 파일의 MIME 타입을 자동으로 감지
        String contentType = Files.probeContentType(Path.of(imageUrl.getURI()));

        if (contentType == null) {
            contentType = "application/octet-stream"; // 기본값 설정
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType)) // 올바른 Content-Type 설정
            .contentLength(imageUrl.contentLength()) // Content-Length 설정
            .body(imageUrl);
    }

    @GetMapping(value = "postimg/{imageName}")
    public ResponseEntity<UrlResource> getPostImage(@PathVariable String imageName) throws IOException {
        UrlResource imageUrl = postImageService.getImage(imageName);

        // 파일의 MIME 타입을 자동으로 감지
        String contentType = Files.probeContentType(Path.of(imageUrl.getURI()));

        if (contentType == null) {
            contentType = "application/octet-stream"; // 기본값 설정
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType)) // 올바른 Content-Type 설정
            .contentLength(imageUrl.contentLength()) // Content-Length 설정
            .body(imageUrl);
    }
}
