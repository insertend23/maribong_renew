package com.navangs.maribong.service.impl;

import com.navangs.maribong.config.image.ImagePath;
import com.navangs.maribong.config.image.ImageQueryPath;
import com.navangs.maribong.dto.post.PostDTO;
import com.navangs.maribong.dto.post.PostDeleteDTO;
import com.navangs.maribong.dto.post.PostLikeRequestDTO;
import com.navangs.maribong.dto.post.PostModifyDTO;
import com.navangs.maribong.dto.post.PostOverviewDTO;
import com.navangs.maribong.dto.post.PostPhotoModifyDTO;
import com.navangs.maribong.dto.post.PostPhotosDTO;
import com.navangs.maribong.dto.post.PostRequestDTO;
import com.navangs.maribong.dto.post.PostWriteDTO;
import com.navangs.maribong.dto.post.ReplyDTO;
import com.navangs.maribong.dto.post.ReplyDeleteDTO;
import com.navangs.maribong.dto.post.ReplyInsertDTO;
import com.navangs.maribong.dto.post.ReplyModifyDTO;
import com.navangs.maribong.dto.post.ReplyRequestDTO;
import com.navangs.maribong.entity.post.Post;
import com.navangs.maribong.entity.post.PostLike;
import com.navangs.maribong.entity.post.PostLikeId;
import com.navangs.maribong.entity.post.PostPhoto;
import com.navangs.maribong.entity.post.Reply;
import com.navangs.maribong.exception.InvalidPostIdException;
import com.navangs.maribong.exception.InvalidPostLikeIdException;
import com.navangs.maribong.exception.InvalidReplyIdException;
import com.navangs.maribong.repository.post.PostLikeRepository;
import com.navangs.maribong.repository.post.PostPhotoRepository;
import com.navangs.maribong.repository.post.PostRepository;
import com.navangs.maribong.repository.post.PostSpecification;
import com.navangs.maribong.repository.post.ReplyRepository;
import com.navangs.maribong.service.ImageService;
import com.navangs.maribong.service.PostService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.domain.Specification;
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

    @Qualifier(PostImageServiceImpl.BEAN_NAME)
    private final ImageService imageService;

    @Qualifier(ImageQueryPath.BEAN_NAME)
    private final ImagePath imagePath;

    @Override
    public List<PostOverviewDTO> getPosts(PostRequestDTO postRequestDTO) {
        Specification<Post> spec = PostSpecification.getSpec(DEFAULT_EXCLUDE_USER_ID, postRequestDTO.getCountry(),
            postRequestDTO.getGroup(), postRequestDTO.getReaction());
        List<Post> posts = postRepository.findAll(spec);

        return convertPostsToPostDTOs(posts, postRequestDTO.getUserId());
    }

    @Override
    public List<PostOverviewDTO> getMyPosts(String userId) {
        List<Post> posts = postRepository.findByUser_IdOrderByRegTimestampDesc(userId);

        return convertPostsToPostDTOs(posts, userId);
    }

    @Override
    public void addPost(List<MultipartFile> images, PostWriteDTO postWriteDTO, String reaction) {
        Post post = Post.fromWriteDTO(postWriteDTO, reaction);
        Post savedPost = postRepository.save(post);
        images.forEach(image -> addPostImage(image, savedPost));
    }

    @Override
    public void updatePost(PostModifyDTO postModifyDTO, String reaction) {
        Post post = validatePostIdAndGetPost(postModifyDTO.getCommunityNo());
        post.modify(postModifyDTO.getContent(), postModifyDTO.getCountry(), postModifyDTO.getGroupName(),
            postModifyDTO.getAreaName(), reaction);
        postRepository.save(post);
    }

    @Override
    public void deletePost(PostDeleteDTO postDeleteDTO) {
        Post post = validatePostIdAndGetPost(postDeleteDTO.getCommunityNo());
        postRepository.delete(post);
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
    public void updatePostImage(List<MultipartFile> images, PostPhotoModifyDTO postPhotoModifyDTO) {
        Post post = validatePostIdAndGetPost(postPhotoModifyDTO.getCommunityNo());
        images.forEach(image -> addPostImage(image, post));
    }

    @Override
    public List<List<ReplyDTO>> getOverviewReplies(List<Long> postIds) {
        return postIds.stream()
            .map(postId -> replyRepository.findByPost_Id(postId, Limit.of(3)))
            .map(replies -> replies.stream().map(ReplyDTO::fromEntity).toList())
            .toList();
    }

    @Override
    public List<ReplyDTO> getReplies(ReplyRequestDTO replyRequestDTO) {
        List<Reply> replies = replyRepository.findByPost_Id(replyRequestDTO.getCommunityNo());

        return replies.stream()
            .map(ReplyDTO::fromEntity)
            .toList();
    }

    @Override
    public ReplyDTO addReply(ReplyInsertDTO replyInsertDTO) {
        Reply reply = Reply.fromInsertDTO(replyInsertDTO);
        Reply savedReply = replyRepository.save(reply);

        return ReplyDTO.fromEntity(savedReply);
    }

    @Override
    public void updateReply(ReplyModifyDTO replyModifyDTO) {
        Reply reply = validateReplyIdAndGetReply(replyModifyDTO.getReplyNo());
        reply.modify(replyModifyDTO.getContent());
        replyRepository.save(reply);
    }

    @Override
    public void deleteReply(ReplyDeleteDTO replyDeleteDTO) {
        Reply reply = validateReplyIdAndGetReply(replyDeleteDTO.getReplyNo());
        replyRepository.delete(reply);
    }

    @Override
    public String getMyPostLikeInPost(PostLikeRequestDTO postLikeRequestDTO) {
        PostLikeId postLikeId = PostLikeId.of(postLikeRequestDTO.getCommunityNo(), postLikeRequestDTO.getUserId());
        PostLike postLike = postLikeRepository.findById(postLikeId).orElse(null);

        return postLike != null ? postLike.getPost().getUser().getId() : null;
    }

    @Override
    public void addPostLike(PostLikeRequestDTO postLikeRequestDTO) {
        PostLike postLike = PostLike.fromInsertDTO(postLikeRequestDTO);
        postLikeRepository.save(postLike);
    }

    @Override
    public void deletePostLike(PostLikeRequestDTO postLikeRequestDTO) {
        PostLikeId postLikeId = PostLikeId.of(postLikeRequestDTO.getCommunityNo(), postLikeRequestDTO.getUserId());
        PostLike postLike = postLikeRepository.findById(postLikeId).orElse(null);
        if (postLike == null) {
            throw new InvalidPostLikeIdException();
        }
        postLikeRepository.delete(postLike);
    }

    private List<PostOverviewDTO> convertPostsToPostDTOs(List<Post> posts, String userId) {
        return posts.stream()
            .map(post -> {
                PostDTO postDTO = PostDTO.fromEntity(post, imagePath.getProfilePath());
                List<PostPhoto> postPhotos = postPhotoRepository.findByPostId(post.getId());
                PostPhotosDTO postPhotosDTO = PostPhotosDTO.fromEntities(postPhotos, imagePath.getPostImagePath());
                Integer replyCount = replyRepository.countByPost_Id(post.getId()).intValue();
                Integer postLikes = postLikeRepository.countById_PostId(post.getId()).intValue();
                Boolean myPostLike = postLikeRepository.existsById_PostIdAndId_UserId(post.getId(), userId);
                return PostOverviewDTO.create2(postDTO, postPhotosDTO, replyCount, postLikes, myPostLike);
            })
            .toList();
    }

    private Post validatePostIdAndGetPost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            throw new InvalidPostIdException();
        }

        return post;
    }

    private Reply validateReplyIdAndGetReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElse(null);
        if (reply == null) {
            throw new InvalidReplyIdException();
        }

        return reply;
    }
}
