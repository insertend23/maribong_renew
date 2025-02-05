package com.navangs.maribong.service.impl;

import com.navangs.maribong.dto.post.PostDTO;
import com.navangs.maribong.dto.post.PostRequestDTO;
import com.navangs.maribong.dto.post.PostWriteDTO;
import com.navangs.maribong.dto.post.ReplyDTO;
import com.navangs.maribong.entity.post.Post;
import com.navangs.maribong.entity.post.PostPhoto;
import com.navangs.maribong.repository.post.PostLikeRepository;
import com.navangs.maribong.repository.post.PostPhotoRepository;
import com.navangs.maribong.repository.post.PostRepository;
import com.navangs.maribong.repository.post.ReplyRepository;
import com.navangs.maribong.service.ImageService;
import com.navangs.maribong.service.PostService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private static final String DEFAULT_EXCLUDE_USER_ID = "admin";
    private final PostRepository postRepository;
    private final PostPhotoRepository postPhotoRepository;
    private final ReplyRepository replyRepository;
    private final PostLikeRepository postLikeRepository;
    @Qualifier("postImageServiceImpl")
    private final ImageService imageService;

    @Override
    public List<PostDTO> getPosts(PostRequestDTO postRequestDTO) {
        List<Post> posts;
        if (postRequestDTO.hasNoSearchOptions()) {
            posts = postRepository.findAll();
        } else {
            posts = postRepository.findBySearchOptions(DEFAULT_EXCLUDE_USER_ID,
                postRequestDTO.getCountry(), postRequestDTO.getGroup(), postRequestDTO.getReaction());
        }
        return convertPostsToPostDTOs(posts, postRequestDTO.getUserId());
    }

    @Override
    public List<PostDTO> getMyPosts(String userId) {
        List<Post> posts = postRepository.findByUser_IdNotOrderByRegTimestampDesc(userId);

        return convertPostsToPostDTOs(posts, userId);
    }

    @Override
    public void addPost(List<MultipartFile> images, PostWriteDTO postWriteDTO, String reaction) {
        Post post = Post.fromWriteDTO(postWriteDTO, reaction);
        Post savedPost = postRepository.save(post);
        images.forEach(image -> addPostImage(image, savedPost));
    }

    @Override
    public void addPostImage(MultipartFile image, Post post) {
        String imageExtension = StringUtils.getFilenameExtension(image.getOriginalFilename());
        String randomImageName = String.join(".", UUID.randomUUID().toString(), imageExtension);
        PostPhoto photo = PostPhoto.builder()
            .post(post)
            .originName(image.getOriginalFilename())
            .imgName(randomImageName)
            .build();
        postPhotoRepository.save(photo);
        imageService.uploadImage(image, randomImageName);
    }

    @Override
    public List<List<ReplyDTO>> getReplies(List<Integer> postIds) {
        return postIds.stream()
            .map(Integer::longValue)
            .map(replyRepository::findByPost_Id)
            .map(replies -> replies.stream().map(ReplyDTO::fromEntity).toList())
            .toList();
    }

    private List<PostDTO> convertPostsToPostDTOs(List<Post> posts, String userId) {
        return posts.stream()
            .map(post -> {
                List<PostPhoto> postPhotos = postPhotoRepository.findByPostId(post.getId());
                Integer replyCount = replyRepository.countByPost_Id(post.getId()).intValue();
                Integer postLikes = postLikeRepository.countById_PostId(post.getId()).intValue();
                Boolean myPostLike = postLikeRepository.existsById_PostIdAndId_UserId(post.getId(), userId);
                return PostDTO.create(post, postPhotos, replyCount, postLikes, myPostLike);
            })
            .toList();
    }
}
