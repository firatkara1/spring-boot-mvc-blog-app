package com.firat.springbootmvcblogapp.service;

import com.firat.springbootmvcblogapp.dto.PostDto;
import com.firat.springbootmvcblogapp.dto.PostResponse;
import com.firat.springbootmvcblogapp.entity.Post;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update
    PostDto updatePost(PostDto  postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //get single post
    PostDto getPostById(Integer postId);

    //get all post
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get all post by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all posts by userId
    List<PostDto> getPostsByUser(Integer userId);

    //search post
    List<PostDto> searcPost(String keyword);

}
