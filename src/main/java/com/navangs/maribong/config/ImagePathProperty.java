package com.navangs.maribong.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "image.url")
public class ImagePathProperty {
    private static final String SCHEME = "http://";
    private static final String PATH_SEPARATOR = "/";
    private final String host;
    private final Integer port;
    private final String uploadPath;
    private final String queryPath;
    private final String postImagePath;
    private final String profilePath;

    public String getPostImageUploadPath() {
        return String.join(PATH_SEPARATOR, uploadPath, postImagePath);
    }

    public String getPostImageQueryPath() {
        return String.join(PATH_SEPARATOR, getUri(), queryPath, postImagePath);
    }

    public String getProfileUploadPath() {
        return String.join(PATH_SEPARATOR, uploadPath, profilePath);
    }

    public String getProfileQueryPath() {
        return String.join(PATH_SEPARATOR, getUri(), queryPath, profilePath);
    }

    private String getUri() {
        return SCHEME + host + ":" + port.toString();
    }
}
