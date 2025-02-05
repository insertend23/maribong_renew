package com.navangs.maribong.dto.post;

import com.navangs.maribong.entity.post.PostPhoto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostPhotosDTO {
    private String thumbnail;
    private String paths;
    private Integer count;

    public static PostPhotosDTO fromEntities(List<PostPhoto> postPhotos, String imageQueryPath) {
        if (postPhotos == null || postPhotos.isEmpty()) {
            return PostPhotosDTO.builder()
                .thumbnail("")
                .paths("")
                .count(0)
                .build();
        }

        return PostPhotosDTO.builder()
            .thumbnail(getImagePath(postPhotos.getFirst(), imageQueryPath))
            .paths(postPhotos.stream()
                .map(postPhoto -> getImagePath(postPhoto, imageQueryPath))
                .collect(Collectors.joining(",")))
            .count(postPhotos.size())
            .build();
    }

    private static String getImagePath(PostPhoto photo, String imageQueryPath) {
        return java.lang.String.join("/", imageQueryPath, photo.getImgName());
    }
}
