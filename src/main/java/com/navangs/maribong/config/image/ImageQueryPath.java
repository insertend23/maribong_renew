package com.navangs.maribong.config.image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Qualifier(ImageQueryPath.BEAN_NAME)
public class ImageQueryPath implements ImagePath {
    public static final String BEAN_NAME = "imageQueryPath";
    private final ImagePathProperty imagePathProperty;
    private static final String PATH_SEPARATOR = "/";

    @Override
    public String getPostImagePath() {
        return getQueryPath(imagePathProperty.getPostImagePath());
    }

    @Override
    public String getProfilePath() {
        return getQueryPath(imagePathProperty.getProfilePath());
    }

    private String getQueryPath(String destPath) {
        return String.join(PATH_SEPARATOR, imagePathProperty.getUri(), imagePathProperty.getQueryPath(), destPath);
    }
}
