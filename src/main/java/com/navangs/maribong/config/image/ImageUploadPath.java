package com.navangs.maribong.config.image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Qualifier(ImageUploadPath.BEAN_NAME)
public class ImageUploadPath implements ImagePath {
    public static final String BEAN_NAME = "imageUploadPath";
    private final ImagePathProperty imagePathProperty;
    private static final String PATH_SEPARATOR = "/";

    public String getPostImagePath() {
        return getUploadPath(imagePathProperty.getPostImagePath());
    }


    public String getProfilePath() {
        return getUploadPath(imagePathProperty.getProfilePath());
    }

    private String getUploadPath(String destPath) {
        return String.join(PATH_SEPARATOR, imagePathProperty.getUploadPath(), destPath);
    }
}
