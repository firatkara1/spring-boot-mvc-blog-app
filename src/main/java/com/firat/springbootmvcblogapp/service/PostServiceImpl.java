package com.firat.springbootmvcblogapp.service;

import com.firat.springbootmvcblogapp.dto.PostDto;
import com.firat.springbootmvcblogapp.dto.PostResponse;
import com.firat.springbootmvcblogapp.entity.Category;
import com.firat.springbootmvcblogapp.entity.Post;
import com.firat.springbootmvcblogapp.entity.User;
import com.firat.springbootmvcblogapp.exception.ResourceNotFoundException;
import com.firat.springbootmvcblogapp.repository.CategoryRepository;
import com.firat.springbootmvcblogapp.repository.PostRepository;
import com.firat.springbootmvcblogapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category ", "Category Id", categoryId));

        Post post = modelMapper.map(postDto, Post.class);



        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);



        Post savedPost = postRepository.save(post);

        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException(
                "Post ", "postId", postId
        ));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = postRepository.save(post);

        return modelMapper.map(updatedPost, PostDto.class);

    }

    @Override
    public void deletePost(Integer postId) {

        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException(
                "Post ", "postId", postId
        ));

        postRepository.delete(post);
    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException(
                "Post ", "postId", postId
        ));


        return modelMapper.map(post,PostDto.class);
    }



    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending());

        Pageable p  = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = postRepository.findAll(p);

        List<Post> allPosts = pagePost.getContent();

        List<PostDto> postDtos = allPosts.stream().map((post -> modelMapper.map(post, PostDto.class))).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());


        return postResponse;
    }



    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException(
                "Category", "category id", categoryId
        ));

        List<Post> posts = postRepository.findByCategory(category);

        return posts.stream().map((post -> modelMapper.map(post, PostDto.class))).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "User ", "userId",userId
                ));

        List<Post> posts = postRepository.findAllByUser(user);

        return posts.stream().map((post -> modelMapper.map(post, PostDto.class))).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searcPost(String keyword) {

        List<Post> postList = postRepository.searchByTitle("%" + keyword + "%");

        List<PostDto> postDtos = postList.stream().map((post -> modelMapper.map(post, PostDto.class))).collect(Collectors.toList());

        return postDtos;
    }
}














