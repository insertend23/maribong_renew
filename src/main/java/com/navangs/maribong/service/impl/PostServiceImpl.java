package com.navangs.maribong.service.impl;

import com.navangs.maribong.dto.post.PostDTO;
import com.navangs.maribong.dto.post.PostRequestDTO;
import com.navangs.maribong.entity.post.Post;
import com.navangs.maribong.entity.post.PostPhoto;
import com.navangs.maribong.repository.post.PostLikeRepository;
import com.navangs.maribong.repository.post.PostPhotoRepository;
import com.navangs.maribong.repository.post.PostRepository;
import com.navangs.maribong.repository.post.ReplyRepository;
import com.navangs.maribong.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private static final String DEFAULT_EXCLUDE_USER_ID = "admin";
    private final PostRepository postRepository;
    private final PostPhotoRepository postPhotoRepository;
    private final ReplyRepository replyRepository;
    private final PostLikeRepository postLikeRepository;

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
