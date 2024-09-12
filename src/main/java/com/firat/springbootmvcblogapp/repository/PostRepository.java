package com.firat.springbootmvcblogapp.repository;

import com.firat.springbootmvcblogapp.entity.Category;
import com.firat.springbootmvcblogapp.entity.Post;
import com.firat.springbootmvcblogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByUser(User user);
    List<Post> findByCategory(Category category);
    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key") String title);

}
