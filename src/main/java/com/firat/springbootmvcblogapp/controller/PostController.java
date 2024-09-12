package com.firat.springbootmvcblogapp.controller;

import com.firat.springbootmvcblogapp.config.AppConst;
import com.firat.springbootmvcblogapp.dto.ApiResponse;
import com.firat.springbootmvcblogapp.dto.ImageResponse;
import com.firat.springbootmvcblogapp.dto.PostDto;
import com.firat.springbootmvcblogapp.dto.PostResponse;
import com.firat.springbootmvcblogapp.entity.Post;
import com.firat.springbootmvcblogapp.service.FileService;
import com.firat.springbootmvcblogapp.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId){

        PostDto createPost = postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }


    //get by user posts

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>>  getPostByUser(
            @PathVariable Integer userId
    ){
        List<PostDto> postsByUser = postService.getPostsByUser(userId);

        return  new ResponseEntity<List<PostDto>>(postsByUser, HttpStatus.OK);
    }


    //get posts by category

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>>  getPostByCategory(
            @PathVariable Integer categoryId
    ){
        List<PostDto> postsByCategory = postService.getPostsByCategory(categoryId);

        return  new ResponseEntity<List<PostDto>>(postsByCategory, HttpStatus.OK);
    }

    //get all post

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber",defaultValue = AppConst.PAGE_NUMBER,required = false)Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConst.PAGE_SIZE, required = false)Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConst.SORT_BY, required = false)String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConst.SORT_DIR ,required = false)String sortDir
            ){

        PostResponse postResponse = postService.getAllPost(pageNumber,pageSize, sortBy, sortDir);

        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    //get single post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){

        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

    //delete post by id
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){

        postService.deletePost(postId);
        return new ApiResponse("Post deleted successfully",true);
    }

    //update post, by postId post

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){

        PostDto updatedPost =  postService.updatePost(postDto, postId);

        return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);

    }


    //search post
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(
            @PathVariable("keyword") String keyword
    ){
      List<PostDto> res = postService.searcPost(keyword);

      return new ResponseEntity<List<PostDto>>(res, HttpStatus.OK);
    }


    //post image upload

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
            ) throws IOException {

        PostDto postDto =  postService.getPostById(postId);

        String fileName =  fileService.uploadImage(path, image);

        postDto.setImageName(fileName);
        PostDto updatedPost = postService.updatePost(postDto, postId);

        return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }

    //get image post

    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }






}
