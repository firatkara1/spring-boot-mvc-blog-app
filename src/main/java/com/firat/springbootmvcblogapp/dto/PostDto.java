package com.firat.springbootmvcblogapp.dto;

import com.firat.springbootmvcblogapp.entity.Category;
import com.firat.springbootmvcblogapp.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

@NoArgsConstructor
public class PostDto {

    private Integer postId;

    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;


}
